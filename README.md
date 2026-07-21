# Quantum Network Routing & Entanglement Scheduling

A Python scheduler for distributed quantum computers, targeting
architectures like Photonic Inc.'s silicon T-centre qubit platform
where qubits in separate modules are connected by telecom fiber links.

## Problem

On distributed quantum hardware, two-qubit gates between modules
require entanglement generation over a fiber link — a slow,
probabilistic process. Scheduling these remote gates efficiently
is the primary bottleneck for circuit execution time.

## Approach

Three schedulers of increasing sophistication:

| Scheduler | Approach | Complexity |
|-----------|----------|------------|
| Greedy | Schedule each gate as soon as dependencies clear | O(n log n) |
| MILP | Exact optimization via OR-Tools CP-SAT | NP-hard |
| Lookahead | Pre-generate entanglement during idle periods | O(n·w) |

## Results

On QFT (8 qubits, 4 modules), the lookahead heuristic closes ~64%
of the gap between greedy and MILP optimal, while running 10,000x
faster than the exact solver.

## Quick Start

```bash
# install dependencies
pip install networkx numpy matplotlib qiskit ortools

# run all schedulers on QFT-8
python simulate.py --circuit qft --qubits 8 --modules 4

# show gantt chart
python simulate.py --circuit qft --qubits 8 --modules 4 --gantt

# stochastic simulation with probabilistic links
python simulate.py --circuit qft --qubits 8 --modules 4 --stochastic

# vary link success probability
python simulate.py --circuit qft --qubits 8 --modules 4 --p 0.2 --stochastic
```

## Project Structure

```
quantum-scheduler/
├── simulate.py          # CLI entry point
├── circuit/
│   ├── gate.py          # Gate dataclass
│   ├── dag.py           # Circuit DAG construction
│   └── benchmarks.py    # QFT, QAOA, random circuit generators
├── network/
│   ├── network.py       # Network topology (ring, linear chain)
│   └── qubit_map.py     # Qubit-to-module assignment
├── scheduler/
│   ├── base.py          # Abstract base scheduler
│   ├── greedy.py        # Greedy baseline
│   ├── milp.py          # OR-Tools CP-SAT exact solver
│   └── lookahead.py     # Entanglement pre-scheduling heuristic
├── simulation/
│   └── stochastic.py    # Monte Carlo simulation
├── analysis/
│   ├── metrics.py       # Makespan, approximation ratio, benchmarking
│   ├── visualize.py     # Gantt charts, DAG plots
│   └── benchmark.py     # Full benchmark suite runner
└── results.md           # Written analysis
```

## Key Concepts

**Remote gate** — a two-qubit gate whose qubits live in different
modules. Requires entanglement generation over a fiber link before
it can execute. Duration = t_expected + 1.0 where
t_expected = t_attempt / p_link (geometric distribution mean).

**Critical path** — longest dependency chain through the circuit DAG.
Lower bound on makespan — no scheduler can do better.

**Approximation ratio** — scheduler makespan / critical path length.
1.0 = optimal. The lookahead scheduler achieves 1.15 on QFT-8
vs greedy's 1.35.

**Entanglement pre-scheduling** — starting entanglement generation
for an upcoming remote gate during idle periods on that link,
hiding fiber latency behind local computation.

## Relevance to Photonic Inc.

Photonic's Entanglement First™ architecture uses silicon T-centre
spin qubits connected by telecom-wavelength (1326nm) fiber links.
This scheduler directly models that architecture — fiber links as
probabilistic resources, entanglement generation as the dominant
latency, and distributed modules as the unit of scale.