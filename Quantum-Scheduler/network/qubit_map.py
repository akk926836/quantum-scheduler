def assignQubits(n_qubits, n_modules):
    return{i: i % n_modules for i in range(n_qubits)}