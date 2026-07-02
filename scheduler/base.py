from abc import ABC, abstractmethod
import networkx as nx

class baseScheduler(ABC):
    def __init__(self, network_graph, circuit_dag):
        self.G = network_graph
        self.dag = circuit_dag

    @abstractmethod
    def run(self) -> list:
        #return a list of (gate, start time) tuples
        pass
        
