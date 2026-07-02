import networkx as nx
from scheduler.base import baseScheduler

class greedyScheduler(baseScheduler):
    def __init__(self, network_graph, circuit_dag):
        super().__init__(network_graph, circuit_dag)
        self.finish_time = {}
        self.link_free_at = {}
        self.schedule = []

    
    def earliest_start(self, gate):
        dep_ready = max((self.finish_time[p] for p in self.dag.predecessors(gate.id)), default= 0)

        if gate.is_remote:
            link = (min(gate.module_a, gate.module_b), max(gate.module_a, gate.module_b))
            link_ready = self.link_free_at.get(link,0)
            return max(dep_ready, link_ready)
        return dep_ready
    
    def update_resources(self, gate, start_time):
        finish = start_time + gate.duration
        self.finish_time[gate.id] = finish
        if gate.is_remote:
            link = (min(gate.module_a, gate.module_b), max(gate.module_a, gate.module_b))
            self.link_free_at[link] = finish

    def run(self):
        for id in nx.topological_sort(self.dag):
            gate = self.dag.nodes[id]['gate']
            t = self.earliest_start(gate)
            self.schedule.append((gate, t))
            self.update_resources(gate, t)
        return self.schedule