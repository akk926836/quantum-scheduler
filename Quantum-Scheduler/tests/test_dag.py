
import sys
import os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

from circuit.dag import buildCircuitDAG, count_remote_gates
from circuit.gate import Gate
from network.network import buildRing
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np



# Hand-crafted test: 4 qubits across 2 modules (qubits 0,1 → module 0; 2,3 → module 1)
test_gates = [
    Gate(0, 'H',    [0]),
    Gate(1, 'H',    [2]),
    Gate(2, 'CNOT', [0, 1]),       # local
    Gate(3, 'CNOT', [1, 2]),       # REMOTE — crosses modules
    Gate(4, 'T',    [2]),
    Gate(5, 'CNOT', [2, 3]),       # local
    Gate(6, 'CNOT', [0, 2]),       # REMOTE
]

qubit_map = {0: 0, 1: 0, 2: 1, 3: 1}
G_net = buildRing(2)
dag = buildCircuitDAG(test_gates, qubit_map, G_net)

count_remote_gates(dag)

# Visualize the DAG
pos = nx.spring_layout(dag)
colors = ['red' if dag.nodes[n]['gate'].is_remote else 'steelblue'
          for n in dag.nodes]
nx.draw(dag, pos, with_labels=True, node_color=colors,
        node_size=700, font_color='white', arrows=True)
plt.title("Circuit DAG (red = remote gates)")
plt.show()