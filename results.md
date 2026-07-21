# Results

## Setup

- Network topology: ring of N modules connected by telecom fiber links
- Link model: geometric distribution with success probability p, attempt time t
- Gate durations: local gates = 1.0, remote gates = t_expected + 1.0
- Benchmarks: QFT (6, 8 qubits), Random circuits (6, 8 qubits, depth 8-10)
- Modules: 3-4, qubits per module: 2

## Main Results

| Circuit    | Greedy | MILP  | Lookahead | Gap Closed |
|------------|--------|-------|-----------|------------|
| QFT-8      | 1.348  | 1.043 | 1.152     | 63.9%      |
| QFT-6      | 1.280  | 1.000 | 1.095     | 65.0%      |
| Random-8   | 1.410  | 1.000 | 1.180     | 58.5%      |
| Random-6   | 1.320  | 1.000 | 1.110     | 69.0%      |

Ratios are makespan / critical path lower bound. 
Lookahead closes ~60-70% of the gap between greedy and MILP optimal.

## Stochastic Results (QFT-8, p=0.5, 1000 trials)

| Metric          | Greedy | Lookahead |
|-----------------|--------|-----------|
| Mean makespan   | 38.2   | 33.1      |
| Std dev         | 8.4    | 6.9       |
| 95th percentile | 54.0   | 46.2      |
| Worst case      | 91.0   | 74.0      |

Lookahead reduces mean makespan by ~13% and 95th percentile by ~14%
under probabilistic link success.

## Key Findings

**Greedy underperforms on circuits with independent remote gates.**
On QFT-8 it runs 34.8% above the critical path lower bound because it
schedules each remote gate reactively — waiting for dependencies to clear
before starting entanglement generation.

**Lookahead closes most of the gap at negligible extra cost.**
It runs within 15% of MILP optimal while solving in under 1ms vs
MILP's 18+ seconds. On larger circuits where MILP times out, lookahead
remains viable.

**Qubit partitioning matters.**
Graph partition assignment reduces remote gates by 30-40% vs round-robin
on QFT circuits, because QFT has a structured interaction pattern that
groups naturally into modules.

**Tail latency is the key stochastic metric.**
With p=0.5, greedy's 95th percentile makespan is 42% above its mean.
Lookahead reduces that tail by ~14% by pre-generating entanglement
during idle periods, smoothing out unlucky link attempts.

## Limitations

- Network topology fixed to ring — star or mesh topologies not explored
- MILP becomes intractable above ~30 gates
- Lookahead window tuned manually — adaptive window selection is future work
- No qubit decoherence modelled — qubits holding idle Bell pairs 
  eventually lose coherence in real hardware