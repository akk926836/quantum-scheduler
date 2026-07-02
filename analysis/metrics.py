import networkx as nx

def makespan(schedule):
    return max(t + gate.duration for gate, t in schedule)

def approximation_ratio(schedule, dag):
    # performance of the scheduler compared to critical path.

    actual = makespan(schedule)
    lower_bound = critical_length(dag)
    return actual/lower_bound

def critical_length(dag):
    path = nx.dag_longest_path(dag , weight = 'weight')
    length = sum(dag.nodes[n]['gate'].duration for n in path)
    return length

def print_critical_path(dag):
    path = nx.dag_longest_path(dag, weight = 'weight')
    print(f"Critical Path Gate IDs: " , path)
    print(f"Critical Path Length: {critical_length(dag):.2f}")

def compare_schedulers(schedules: dict, dag) -> None:
     
     length_crit = critical_length(dag)
     print(f"\n{'Scheduler':<15} {'Makespan':>10} {'Ratio':>10}")
     print("-" * 37)
     for name, schedule in schedules.items():
         ms = makespan(schedule)
         ratio = ms/length_crit

         print(f"{name:<15} {ms:>10.2f} {ratio:>10.3f}")
     print(f"\nCritical path lower bound: {length_crit:.2f}")

def tune_window(G_net, dag, qubit_map, windows=range(1, 10)):
    """
    Run lookahead scheduler with different window sizes.
    Print makespan for each to find the sweet spot.
    """
    from scheduler.lookahead import lookaheadScheduler

    print(f"\n{'Window':<10} {'Makespan':>10} {'Ratio':>10}")
    print("-" * 32)
    cp = critical_length(dag)
    for w in windows:
        sched = lookaheadScheduler(G_net, dag, window=w)
        schedule = sched.run()
        ms = makespan(schedule)
        print(f"{w:<10} {ms:>10.2f} {ms/cp:>10.3f}")