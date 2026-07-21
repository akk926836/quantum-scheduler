from qiskit import QuantumCircuit
from qiskit.circuit.library import QFT
from qiskit.circuit.random import random_circuit
from circuit.gate import Gate

def qiskit_to_gates(qiskit_circuit: QuantumCircuit) -> list:
    """
    Convert a Qiskit QuantumCircuit into a list of Gate objects.
    Handles single and two qubit gates only — ignores measurement gates.
    """
    gates = []
    gate_id = 0

    for instruction in qiskit_circuit.data:
        op = instruction.operation
        qubits = [qiskit_circuit.find_bit(q).index for q in instruction.qubits]

        # skip measurements and barriers
        if op.name in ('measure', 'barrier', 'reset'):
            continue

        # only handle 1 and 2 qubit gates
        if len(qubits) > 2:
            continue

        gates.append(Gate(
            id=gate_id,
            name=op.name.upper(),
            qubits=qubits,
            duration=1.0   # uniform local duration — updated later for remote gates
        ))
        gate_id += 1

    return gates

def make_qft_circuit(n_qubits: int) -> list:
    """Quantum Fourier Transform on n_qubits."""
    qc = QFT(n_qubits)
    qc = qc.decompose()
    return qiskit_to_gates(qc)

def make_random_circuit(n_qubits: int, depth: int, seed: int = 42) -> list:
    """Random circuit with given qubit count and depth."""
    qc = random_circuit(n_qubits, depth, max_operands=2, seed=seed)
    return qiskit_to_gates(qc)
