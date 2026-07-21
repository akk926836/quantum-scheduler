# DAG : Directed Acyclic Graph
from circuit.gate import Gate
import networkx as nx

def buildCircuitDAG(gates: list[Gate], qubit_module_map: dict, G: nx.Graph) -> nx.DiGraph:
    dag = nx.DiGraph()

    for g in gates:
        dag.add_node(g.id, gate=g)

    last_gate = {}

    for g in gates:
        for q in g.qubits:
            if q in last_gate:
                dag.add_edge(last_gate[q], g.id)
            last_gate[q] = g.id

        # Tag gate as local or remote
        if len(g.qubits) == 2:
            m0 = qubit_module_map[g.qubits[0]]
            m1 = qubit_module_map[g.qubits[1]]
            if m0 != m1:
                g.is_remote = True
                g.module_a = min(m0, m1)
                g.module_b = max(m0, m1)
                link_key = (min(m0, m1), max(m0, m1))

                if G.has_edge(*link_key):
                    # direct fiber link exists
                    link_data = G.edges[link_key]
                    g.duration = link_data['t_expected'] + 1.0
                else:
                    # no direct link — route through intermediate modules
                    # estimate latency as number of hops * one link's t_expected
                    n_hops = nx.shortest_path_length(G, m0, m1)
                    sample_edge = list(G.edges(data=True))[0][2]
                    g.duration = n_hops * sample_edge['t_expected'] + 1.0
                    print(f"Gate {g.id} indirect remote "
                          f"({n_hops} hops), duration={g.duration:.2f}")

    assert nx.is_directed_acyclic_graph(dag)
    return dag


def find_critical_length(dag: nx.DiGraph) -> float:
    for node in dag.nodes:
        gate = dag.nodes[node]['gate']
        dag.nodes[node]['weight'] = gate.duration

    path = nx.dag_longest_path(dag, weight='weight')
    length = sum(dag.nodes[n]['gate'].duration for n in path)

    print(f"Critical Path: {path}")
    print(f"Critical Path Length: {length:.2f}")

    return length

def count_remote_gates(dag: nx.DiGraph):
    gates = [dag.nodes[n]['gate'] for n in dag.nodes]
    n_total = len(gates)

    remote_gates = [g for g in gates if g.is_remote]
    n_remote = len(remote_gates)

    print(f"Total Gates: {n_total}")
    print(f"Total Remote Gates: {n_remote} ({100*n_remote/n_total:.1f}%)")
    print(f"Critical Path Length: {find_critical_length(dag):.1f}")