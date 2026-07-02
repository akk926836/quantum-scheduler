import sys, os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from circuit.dag import buildCircuitDAG
from circuit.gate import Gate
from network.network import buildRing
from scheduler.greedy import greedyScheduler
from scheduler.milp import milpScheduler
from analysis.metrics import makespan, approximation_ratio, compare_schedulers
from analysis.visualize import plot_gantt

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

# run both schedulers
greedy  = greedyScheduler(G_net, dag)
greedy_schedule = greedy.run()

milp = milpScheduler(G_net, dag, time_limit=30)
milp_schedule = milp.run()

# compare
compare_schedulers({
    'greedy': greedy_schedule,
    'milp':   milp_schedule
}, dag)

# gantt charts side by side
plot_gantt(greedy_schedule, n_modules=2, qubit_map=qubit_map,
           title="Greedy Scheduler")
plot_gantt(milp_schedule,   n_modules=2, qubit_map=qubit_map,
           title="MILP Scheduler")