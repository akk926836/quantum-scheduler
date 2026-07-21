import networkx as nx
from scheduler.base import baseScheduler

def compute_asap(dag: nx.DiGraph) -> dict:
    
    asap = {}
    
    for n in nx.topological_sort(dag):
        gate = dag.nodes[n]['gate']
        predecessor = list(dag.predecessors(n))
        
        if not predecessor:
            asap[n] = 0.0
        else:
            asap[n] = max(asap[p] + dag.nodes[p]['gate'].duration for p in predecessor)
    return asap


def compute_alap(dag:nx.DiGraph, makespan:float) -> dict:

    alap = {}
    for n in reversed(list(nx.topological_sort(dag))):
        gate = dag.nodes[n]['gate']
        successor = list(dag.successors(n))

        if not successor:
            alap[n] = makespan - gate.duration
        else:
            alap[n] = min(alap[s] for s in successor) - gate.duration

    return alap


class lookaheadScheduler(baseScheduler):
    def __init__(self, network_graph, circuit_dag, window = 3):
        super().__init__(network_graph, circuit_dag)
        self.window = window
        self.finish_time = {}
        self.link_free_at = {}
        self.entangle_ready = {}
        self.schedule = []

    def earliest_start(self, gate):
        dep_ready = max((self.finish_time[p] for p in self.dag.predecessors(gate.id)), default = 0)

        if gate.is_remote:
            link = (min(gate.module_a, gate.module_b),
                    max(gate.module_a, gate.module_b))

            t_entangle = self.entangle_ready.get(gate.id, None)
            link_free = self.link_free_at.get(link, 0)
            
            if t_entangle is not None:
                link_ready = max(t_entangle, link_free)
                return max(link_ready, dep_ready)
            else:
                if self.G.has_edge(*link):
                    link_free = self.link_free_at.get(link, 0)
                else:
                    # indirect link — no direct fiber, just wait for dependencies
                    link_free = 0
                return max(dep_ready, link_free)
        return dep_ready
    
    def update_resources(
            self,
            gate,
            start_time,
    ):
        finish = start_time +  gate.duration
        self.finish_time[gate.id] = finish

        if gate.is_remote:
            link = (min(gate.module_a, gate.module_b),
                    max(gate.module_a, gate.module_b))
            self.link_free_at[link] = finish

    def pre_generate_entanglement(self, current_time, asap, alap):
        """
        Look ahead window units into the future.
        For any remote gate whose ALAP entanglement start <= current_time,
        pre-generate its entanglement now if the link is free.
        """
        for node in self.dag.nodes:
            gate = self.dag.nodes[node]['gate']
            if not gate.is_remote:
                continue
            if gate.id in self.entangle_ready:
                continue  # already pre-generated

            link = (min(gate.module_a, gate.module_b),
                    max(gate.module_a, gate.module_b))
            
            if not self.G.has_edge(*link):
                continue
            
            link_data = self.G.edges[link]
            t_ent = link_data['t_expected']

            # ALAP start for entanglement = ALAP start of gate
            alap_ent_start = alap[node]

            other_gates_need_link_sooner = any(
            self.dag.nodes[other]['gate'].is_remote and
            other != node and
            (min(self.dag.nodes[other]['gate'].module_a,
                 self.dag.nodes[other]['gate'].module_b),
             max(self.dag.nodes[other]['gate'].module_a,
                 self.dag.nodes[other]['gate'].module_b)) == link and
            asap[other] < asap[node]
            for other in self.dag.nodes
        )
            
            # if this gate is coming up within the window and link is free
            link_free = self.link_free_at.get(link, 0)
            if (asap[node] <= current_time + self.window and
                    alap_ent_start >= current_time and
                    link_free <= current_time):

                # pre-generate: entanglement ready at current_time + t_ent
                self.entangle_ready[gate.id] = current_time + t_ent
                self.link_free_at[link] = current_time + t_ent
                
    def run(self):
        gates = [self.dag.nodes[n]['gate'] for n in self.dag.nodes]

        # compute critical path makespan for ALAP calculation
        from analysis.metrics import critical_length
        cp = critical_length(self.dag)

        asap = compute_asap(self.dag)
        alap = compute_alap(self.dag, cp)

        for node in nx.topological_sort(self.dag):
            gate = self.dag.nodes[node]['gate']

            # current time = when this gate's dependencies clear
            dep_ready = max(
                (self.finish_time[p] for p in self.dag.predecessors(gate.id)),
                default=0
            )

            # look ahead from current time and pre-generate entanglement
            self.pre_generate_entanglement(dep_ready, asap, alap)

            t = self.earliest_start(gate)
            self.schedule.append((gate, t))
            self.update_resources(gate, t)

        return self.schedule