import numpy as np
import copy

def sample_link_duration(p_entangle, t_attempt):
    """
    Draw one sample from the geometric distribution.
    Number of attempts until first success follows Geometric(p).
    """
    n_attempts = np.random.geometric(p_entangle)
    return n_attempts * t_attempt

def stochastic_run(scheduler_class, G_net, original_dag,
                   qubit_map, n_trials=1000, **kwargs):
    """
    Monte Carlo simulation.
    Each trial:
      1. Deep copy the dag so gate objects are fresh
      2. Sample random link durations from geometric distribution
      3. Update remote gate durations with sampled values
      4. Run scheduler and record makespan
    Returns list of makespans across all trials.
    """
    from analysis.metrics import makespan as compute_makespan
    import networkx as nx

    makespans = []

    for trial in range(n_trials):
        # deep copy dag so we don't mutate original gate objects
        dag_copy = copy.deepcopy(original_dag)

        # sample fresh link durations for this trial
        for u, v in G_net.edges():
            p = G_net.edges[u, v]['p_entangle']
            t = G_net.edges[u, v]['t_attempt']
            sampled_duration = sample_link_duration(p, t)
            G_net.edges[u, v]['t_expected'] = sampled_duration

        # update remote gate durations in the copied dag
        for node in dag_copy.nodes:
            gate = dag_copy.nodes[node]['gate']
            if gate.is_remote:
                link = (min(gate.module_a, gate.module_b),
                        max(gate.module_a, gate.module_b))
                gate.duration = G_net.edges[link]['t_expected'] + 1.0

        # run scheduler on copied dag
        scheduler = scheduler_class(G_net, dag_copy, **kwargs)
        schedule = scheduler.run()
        makespans.append(compute_makespan(schedule))

    # restore original t_expected on network graph
    for u, v in G_net.edges():
        p = G_net.edges[u, v]['p_entangle']
        t = G_net.edges[u, v]['t_attempt']
        G_net.edges[u, v]['t_expected'] = t / p

    return makespans

def report_stochastic(makespans, scheduler_name):
    arr = np.array(makespans)
    print(f"\n--- Stochastic Results: {scheduler_name} ---")
    print(f"  Trials:              {len(arr)}")
    print(f"  Mean makespan:       {arr.mean():.2f}")
    print(f"  Std dev:             {arr.std():.2f}")
    print(f"  95th percentile:     {np.percentile(arr, 95):.2f}")
    print(f"  Worst case:          {arr.max():.2f}")
    print(f"  Best case:           {arr.min():.2f}")

def plot_makespan_distribution(greedy_makespans, lookahead_makespans):
    """
    Plot histogram of makespan distributions for both schedulers.
    This is your key stochastic result figure.
    """
    import matplotlib.pyplot as plt

    fig, ax = plt.subplots(figsize=(10, 5))

    ax.hist(greedy_makespans, bins=30, alpha=0.6,
            color='steelblue', label='Greedy', edgecolor='white')
    ax.hist(lookahead_makespans, bins=30, alpha=0.6,
            color='tomato', label='Lookahead', edgecolor='white')

    # mark 95th percentiles
    ax.axvline(np.percentile(greedy_makespans, 95),
               color='steelblue', linestyle='--', linewidth=1.5,
               label=f'Greedy 95th pct: {np.percentile(greedy_makespans, 95):.1f}')
    ax.axvline(np.percentile(lookahead_makespans, 95),
               color='tomato', linestyle='--', linewidth=1.5,
               label=f'Lookahead 95th pct: {np.percentile(lookahead_makespans, 95):.1f}')

    ax.set_xlabel("Makespan")
    ax.set_ylabel("Frequency")
    ax.set_title("Makespan Distribution under Probabilistic Link Success")
    ax.legend()
    plt.tight_layout()
    plt.show()