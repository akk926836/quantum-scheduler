import sys, os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from circuit.dag import buildCircuitDAG
from circuit.gate import Gate
from network.network import buildRing
from scheduler.greedy import greedyScheduler
from analysis.metrics import makespan, approximation_ratio, print_critical_path
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
print("Nodes:", G_net.nodes(data=True))
print("Edges:", G_net.edges(data=True))
dag = buildCircuitDAG(test_gates, qubit_map, G_net)

scheduler = greedyScheduler(G_net, dag)
schedule = scheduler.run()
print("\n--- Finish Times ---")
for gate, t in schedule:
    print(f"  Gate {gate.id} finish={t + gate.duration:.2f} remote={gate.is_remote}")

print("\n--- Gate 6 predecessors ---")
print(list(dag.predecessors(6)))

print("\n--- Schedule ---")
for gate, t in schedule:
    print(f"  Gate {gate.id} ({gate.name}) start={t:.2f} "
          f"finish={t + gate.duration:.2f} "
          f"{'REMOTE' if gate.is_remote else 'local'}")

print(f"\nMakespan:            {makespan(schedule):.2f}")
print(f"Approximation ratio: {approximation_ratio(schedule, dag):.3f}")
print_critical_path(dag)

plot_gantt(schedule, n_modules=2, qubit_map = qubit_map)