import argparse
import sys
import os

sys.path.insert(0, os.path.abspath(os.path.dirname(__file__)))

from circuit.benchmarks import make_qft_circuit, make_random_circuit
from circuit.dag import buildCircuitDAG
from network.network import buildRing
from network.qubit_map import assign_round_robin, assign_graph_partition
from scheduler.greedy import greedyScheduler
from scheduler.milp import milpScheduler
from scheduler.lookahead import lookaheadScheduler
from analysis.metrics import makespan, critical_length, compare_schedulers
from analysis.visualize import plot_gantt
from simulation.stochastic_sim import stochastic_run, report_stochastic, \
                                   plot_makespan_distribution

def parse_args():
    parser = argparse.ArgumentParser(
        description='Quantum Network Routing & Entanglement Scheduling'
    )
    parser.add_argument('--circuit', type=str, default='qft',
                        choices=['qft', 'random'],
                        help='Circuit type to benchmark')
    parser.add_argument('--qubits', type=int, default=8,
                        help='Number of qubits')
    parser.add_argument('--modules', type=int, default=4,
                        help='Number of quantum modules')
    parser.add_argument('--depth', type=int, default=10,
                        help='Circuit depth (random circuits only)')
    parser.add_argument('--scheduler', type=str, default='all',
                        choices=['greedy', 'milp', 'lookahead', 'all'],
                        help='Which scheduler to run')
    parser.add_argument('--window', type=int, default=3,
                        help='Lookahead window size')
    parser.add_argument('--partition', type=str, default='graph',
                        choices=['roundrobin', 'graph'],
                        help='Qubit partitioning strategy')
    parser.add_argument('--stochastic', action='store_true',
                        help='Run Monte Carlo stochastic simulation')
    parser.add_argument('--trials', type=int, default=500,
                        help='Number of stochastic trials')
    parser.add_argument('--p', type=float, default=0.5,
                        help='Link entanglement success probability')
    parser.add_argument('--gantt', action='store_true',
                        help='Show Gantt chart')
    parser.add_argument('--milp-limit', type=int, default=30,
                        help='MILP solver time limit in seconds')
    return parser.parse_args()


def main():
    args = parse_args()

    # --- Build circuit ---
    print(f"\nBuilding {args.circuit.upper()} circuit "
          f"({args.qubits} qubits, {args.modules} modules)...")

    if args.circuit == 'qft':
        gates = make_qft_circuit(args.qubits)
    else:
        gates = make_random_circuit(args.qubits, args.depth)

    # --- Partition qubits ---
    if args.partition == 'graph':
        qubit_map = assign_graph_partition(gates, args.qubits, args.modules)
    else:
        qubit_map = assign_round_robin(args.qubits, args.modules)

    # --- Build network and DAG ---
    G_net = buildRing(args.modules, p_entangle=args.p)
    dag   = buildCircuitDAG(gates, qubit_map, G_net)
    cp    = critical_length(dag)

    print(f"Total gates:    {len(gates)}")
    print(f"Critical path:  {cp:.2f}")

    # --- Run schedulers ---
    schedules = {}

    if args.scheduler in ('greedy', 'all'):
        schedules['greedy'] = greedyScheduler(G_net, dag).run()

    if args.scheduler in ('milp', 'all'):
        schedules['milp'] = milpScheduler(
            G_net, dag, time_limit=args.milp_limit
        ).run()

    if args.scheduler in ('lookahead', 'all'):
        schedules['lookahead'] = lookaheadScheduler(
            G_net, dag, window=args.window
        ).run()

    # --- Print results ---
    compare_schedulers(schedules, dag)

    # --- Gantt charts ---
    if args.gantt:
        for name, sched in schedules.items():
            plot_gantt(sched, n_modules=args.modules,
                       qubit_map=qubit_map,
                       title=f"{name.capitalize()} — "
                             f"{args.circuit.upper()} {args.qubits}q")

    # --- Stochastic ---
    if args.stochastic:
        print(f"\nRunning stochastic simulation "
              f"({args.trials} trials)...")

        greedy_ms = stochastic_run(
            greedyScheduler, G_net, dag,
            qubit_map, n_trials=args.trials
        )
        lookahead_ms = stochastic_run(
            lookaheadScheduler, G_net, dag,
            qubit_map, n_trials=args.trials, window=args.window
        )

        report_stochastic(greedy_ms,    "Greedy")
        report_stochastic(lookahead_ms, "Lookahead")
        plot_makespan_distribution(greedy_ms, lookahead_ms)


if __name__ == '__main__':
    main()