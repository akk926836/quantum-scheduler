import networkx as nx

def assign_round_robin(n_qubits: int, n_modules: int) -> dict:
    """
    Simple round-robin assignment.
    qubit i → module i % n_modules
    """
    return {i: i % n_modules for i in range(n_qubits)}

def assign_graph_partition(gates: list, n_qubits: int, n_modules: int) -> dict:
    max_per_module = n_qubits // n_modules  # hard cap per module

    interaction = nx.Graph()
    for i in range(n_qubits):
        interaction.add_node(i)
    for g in gates:
        if len(g.qubits) == 2:
            u, v = g.qubits
            if interaction.has_edge(u, v):
                interaction[u][v]['weight'] += 1
            else:
                interaction.add_edge(u, v, weight=1)

    from networkx.algorithms.community import greedy_modularity_communities
    communities = greedy_modularity_communities(interaction, weight='weight')

    qubit_map = {}
    module_counts = {i: 0 for i in range(n_modules)}

    for module_id, community in enumerate(communities):
        for qubit in community:
            # find module with space that's closest to intended module
            target = module_id % n_modules
            assigned = False
            for offset in range(n_modules):
                candidate = (target + offset) % n_modules
                if module_counts[candidate] < max_per_module:
                    qubit_map[qubit] = candidate
                    module_counts[candidate] += 1
                    assigned = True
                    break
            if not assigned:
                # fallback — put in least full module
                m = min(module_counts, key=module_counts.get)
                qubit_map[qubit] = m
                module_counts[m] += 1

    for i in range(n_qubits):
        if i not in qubit_map:
            m = min(module_counts, key=module_counts.get)
            qubit_map[i] = m
            module_counts[m] += 1

    return qubit_map

def count_remote_gates_for_map(gates: list, qubit_map: dict) -> int:
    """Count how many two-qubit gates are remote under a given qubit map."""
    count = 0
    for g in gates:
        if len(g.qubits) == 2:
            if qubit_map[g.qubits[0]] != qubit_map[g.qubits[1]]:
                count += 1
    return count