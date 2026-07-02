from ortools.sat.python import cp_model
import networkx as nx
from scheduler.base import baseScheduler

class milpScheduler(baseScheduler):
    def __init__(self, network_graph, circuit_dag, time_limit = 30):
        super().__init__(network_graph,circuit_dag)
        self.time_limit = time_limit
        self.schedule = []

    def run(self):
        model = cp_model.CpModel()
        gates = [self.dag.nodes[node]['gate'] for node in self.dag.nodes]

        # CP SAT works with int ONLY
        # we scale gate.duration by 10 to use CP SAT without losing decimal precision

        scale = 10
        upper_bound = sum(int(g.duration * scale) for g in gates)

        # ===== Decision Variables ====

        starts = {}
        ends = {}
        intervals = {}

        for g in gates:
            duration = int(g.duration * scale)

            starts[g.id] = model.new_int_var(0, upper_bound, f's_{g.id}')
            ends [g.id] = model.new_int_var(0,upper_bound, f'e_{g.id}')
            intervals[g.id] = model.new_interval_var(starts[g.id], duration ,ends[g.id],f'i_{g.id}')

        # ==== Precedence constraints =====
        for u, v in self.dag.edges():
            model.add(starts[v] >= ends[u])
            
    
        # ==== Remote Gate Link Constraints ====
        link_gate_map = {}
        for g in gates:
            if g.is_remote:
                link = (min(g.module_a,g.module_b), max(g.module_a,g.module_b))
                if link not in link_gate_map:
                    link_gate_map[link] = []
                    link_gate_map[link].append(g.id)

        # 2 remote gates cannot overlap
        for link, g_ids in link_gate_map.items():
            if len(g_ids) > 1:
                model.add_no_overlap([intervals[id] for id in g_ids])

        # ==== Objective Function : minimise total time (makespan) =====
        X_makespan = model.new_int_var(0, upper_bound, 'makespan')
        model.add_max_equality(X_makespan, [ends[g.id] for g in gates])
        model.minimize(X_makespan)

        # ==== CP Solver ====

        solver = cp_model.CpSolver()
        solver.parameters.max_time_in_seconds = self.time_limit
        solve_status = solver.Solve(model)

        if solve_status in (cp_model.OPTIMAL, cp_model.FEASIBLE):
            for g in gates:
                t = solver.Value(starts[g.id]) / scale
                self.schedule.append((g,t))
            self.schedule.sort(key = lambda x: x[1])
        else: 
            print("no solution found within the time limit.")
                  
        
        return self.schedule
