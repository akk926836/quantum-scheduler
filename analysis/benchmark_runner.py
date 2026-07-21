import time
import networkx as nx
from circuit.benchmarks import make_qft_circuit, make_random_circuit
from circuit.dag import buildCircuitDAG
from network.network import buildRing
from network.qubit_map import (assign_round_robin,
                                assign_graph_partition,
                                count_remote_gates_for_map)
from scheduler.greedy import greedyScheduler
from scheduler.milp import milpScheduler
from scheduler.lookahead import lookaheadScheduler
from analysis.metrics import makespan, critical_length

def run_benchmark(circuit_name: str,
                  gates: list,
                  n_qubits: int,
                  n_modules: int,
                  p_entangle: float = 0.5,
                  milp_time_limit: int = 30,
                  lookahead_window: int = 3):
    """
    Run all three schedulers on a circuit and return results dict.
    """
    print(f"\n{'='*55}")
    print(f"  Circuit:  {circuit_name}")
    print(f"  Qubits:   {n_qubits}    Modules: {n_modules}")
    print(f"{'='*55}")

    # build network
    G_net = buildRing(n_modules, p_entangle=p_entangle)

    # compare partitioning strategies
    rr_map  = assign_round_robin(n_qubits, n_modules)
    gp_map  = assign_graph_partition(gates, n_qubits, n_modules)

    rr_remote = count_remote_gates_for_map(gates, rr_map)
    gp_remote = count_remote_gates_for_map(gates, gp_map)

    print(f"\n  Partitioning comparison:")
    print(f"    Round-robin:      {rr_remote} remote gates")
    print(f"    Graph partition:  {gp_remote} remote gates")

    # use whichever partition has fewer remote gates
    qubit_map = gp_map if gp_remote <= rr_remote else rr_map
    partition_name = 'graph' if gp_remote <= rr_remote else 'round-robin'
    print(f"    Using: {partition_name} partition")

    # build dag
    dag = buildCircuitDAG(gates, qubit_map, G_net)
    cp = critical_length(dag)
    print(f"\n  Total gates:      {len(gates)}")
    print(f"  Remote gates:     {count_remote_gates_for_map(gates, qubit_map)}")
    print(f"  Critical path:    {cp:.2f}")

    results = {}

    # greedy
    t0 = time.time()
    greedy_sched = greedyScheduler(G_net, dag).run()
    greedy_time = time.time() - t0
    greedy_ms = makespan(greedy_sched)
    results['greedy'] = {
        'makespan': greedy_ms,
        'ratio': greedy_ms / cp,
        'solve_time': greedy_time
    }

    # milp
    t0 = time.time()
    milp_sched = milpScheduler(G_net, dag,
                                time_limit=milp_time_limit).run()
    milp_time = time.time() - t0
    milp_ms = makespan(milp_sched)
    results['milp'] = {
        'makespan': milp_ms,
        'ratio': milp_ms / cp,
        'solve_time': milp_time
    }

    # lookahead
    t0 = time.time()
    la_sched = lookaheadScheduler(G_net, dag,
                                   window=lookahead_window).run()
    la_time = time.time() - t0
    la_ms = makespan(la_sched)
    results['lookahead'] = {
        'makespan': la_ms,
        'ratio': la_ms / cp,
        'solve_time': la_time
    }

    # print results table
    print(f"\n  {'Scheduler':<15} {'Makespan':>10} "
          f"{'Ratio':>8} {'Solve Time':>12}")
    print(f"  {'-'*47}")
    for name, r in results.items():
        print(f"  {name:<15} {r['makespan']:>10.2f} "
              f"{r['ratio']:>8.3f} {r['solve_time']:>11.3f}s")

    return results, dag, G_net, qubit_map, greedy_sched, milp_sched, la_sched


def run_all_benchmarks():
    """Run the full benchmark suite."""
    all_results = {}

    # QFT 8 qubits, 4 modules
    gates = make_qft_circuit(8)
    all_results['QFT-8'] = run_benchmark(
        'QFT (8 qubits)', gates,
        n_qubits=8, n_modules=4
    )

    # QFT 6 qubits, 3 modules
    gates = make_qft_circuit(6)
    all_results['QFT-6'] = run_benchmark(
        'QFT (6 qubits)', gates,
        n_qubits=6, n_modules=3
    )

    # Random circuit 8 qubits depth 10
    gates = make_random_circuit(8, depth=10)
    all_results['Random-8'] = run_benchmark(
        'Random (8 qubits, depth 10)', gates,
        n_qubits=8, n_modules=4
    )

    # Random circuit 6 qubits depth 8
    gates = make_random_circuit(6, depth=8)
    all_results['Random-6'] = run_benchmark(
        'Random (6 qubits, depth 8)', gates,
        n_qubits=6, n_modules=3
    )

    return all_results