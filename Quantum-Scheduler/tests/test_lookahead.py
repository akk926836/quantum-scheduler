import sys, os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from circuit.dag import buildCircuitDAG
from circuit.gate import Gate
from network.network import buildRing
from scheduler.greedy import greedyScheduler
from scheduler.milp import milpScheduler
from scheduler.lookahead import lookaheadScheduler
from analysis.metrics import makespan, compare_schedulers, tune_window
from analysis.visualize import plot_gantt
from simulation.stochastic_sim import stochastic_run, report_stochastic

test_gates = [
    Gate(0, 'H',    [0]),
    Gate(1, 'H',    [2]),
    Gate(2, 'CNOT', [0, 1]),
    Gate(3, 'CNOT', [1, 2]),
    Gate(4, 'T',    [2]),
    Gate(6, 'CNOT', [0, 2]),
    Gate(5, 'CNOT', [2, 3]),
]

qubit_map = {0: 0, 1: 0, 2: 1, 3: 1}
G_net = buildRing(2)
dag = buildCircuitDAG(test_gates, qubit_map, G_net)

# --- Run all three schedulers ---
greedy_schedule   = greedyScheduler(G_net, dag).run()
milp_schedule     = milpScheduler(G_net, dag).run()
lookahead_schedule = lookaheadScheduler(G_net, dag, window=3).run()

# --- Compare ---
compare_schedulers({
    'greedy':    greedy_schedule,
    'milp':      milp_schedule,
    'lookahead': lookahead_schedule,
}, dag)

# --- Tune window ---
tune_window(G_net, dag, qubit_map)

# --- Gantt charts ---
plot_gantt(greedy_schedule,    n_modules=2, qubit_map=qubit_map, title="Greedy")
plot_gantt(milp_schedule,      n_modules=2, qubit_map=qubit_map, title="MILP")
plot_gantt(lookahead_schedule, n_modules=2, qubit_map=qubit_map, title="Lookahead")

# --- Stochastic simulation ---
print("\nRunning stochastic simulation...")
greedy_makespans   = stochastic_run(greedyScheduler, G_net, dag, qubit_map, n_trials=1000)
lookahead_makespans = stochastic_run(lookaheadScheduler, G_net, dag, qubit_map,
                                      n_trials=1000, window=3)

report_stochastic(greedy_makespans,    "Greedy")
report_stochastic(lookahead_makespans, "Lookahead")