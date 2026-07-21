import sys, os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from analysis.benchmark_runner import run_all_benchmarks, run_benchmark
from circuit.benchmarks import make_qft_circuit
from analysis.visualize import plot_gantt
from simulation.stochastic_sim import stochastic_run, report_stochastic, \
                                   plot_makespan_distribution
from scheduler.greedy import greedyScheduler
from scheduler.lookahead import lookaheadScheduler

# --- Run full benchmark suite ---
all_results = run_all_benchmarks()

# --- Summary table across all circuits ---
print(f"\n\n{'='*65}")
print("  FULL RESULTS SUMMARY")
print(f"{'='*65}")
print(f"  {'Circuit':<20} {'Greedy':>10} {'MILP':>10} "
      f"{'Lookahead':>12} {'Gap closed':>12}")
print(f"  {'-'*60}")

for circuit_name, result_tuple in all_results.items():
    results = result_tuple[0]
    g  = results['greedy']['ratio']
    m  = results['milp']['ratio']
    l = results['lookahead']['ratio']
    # gap closed = how much of greedy-milp gap the lookahead closes
    if g != m:
        gap_closed = (g - l) / (g - m) * 100
    else:
        gap_closed = 100.0
    print(f"  {circuit_name:<20} {g:>10.3f} {m:>10.3f} "
          f"{l:>12.3f} {gap_closed:>11.1f}%")

# --- Gantt chart for QFT-8 ---
print("\nShowing Gantt charts for QFT-8...")
results, dag, G_net, qubit_map, g_sched, m_sched, l_sched = \
    all_results['QFT-8']

plot_gantt(g_sched,  n_modules=4, qubit_map=qubit_map,
           title="Greedy — QFT 8 qubits")
plot_gantt(m_sched,  n_modules=4, qubit_map=qubit_map,
           title="MILP — QFT 8 qubits")
plot_gantt(l_sched, n_modules=4, qubit_map=qubit_map,
           title="Lookahead — QFT 8 qubits")

# --- Stochastic on QFT-8 ---
print("\nRunning stochastic simulation on QFT-8...")
from circuit.benchmarks import make_qft_circuit
from circuit.dag import buildCircuitDAG
from network.network import buildRing
from network.qubit_map import assign_graph_partition

gates = make_qft_circuit(8)
G_net = buildRing(4)
qubit_map = assign_graph_partition(gates, 8, 4)
dag = buildCircuitDAG(gates, qubit_map, G_net)

greedy_makespans = stochastic_run(
    greedyScheduler, G_net, dag, qubit_map, n_trials=500
)
lookahead_makespans = stochastic_run(
    lookaheadScheduler, G_net, dag, qubit_map,
    n_trials=500, window=3
)

report_stochastic(greedy_makespans,    "Greedy — QFT 8")
report_stochastic(lookahead_makespans, "Lookahead — QFT 8")
plot_makespan_distribution(greedy_makespans, lookahead_makespans)