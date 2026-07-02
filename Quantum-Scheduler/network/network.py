import networkx as nx
import matplotlib.pyplot as plt
import numpy as np

def addModule(G, module_id, n_qubits = 4, n_comm = 1):
    G.add_node( module_id,
                n_qubits = n_qubits,
                comm_qubits = list(range(n_comm)),
                compute_qubits = list(range(n_comm,n_qubits)))
    
def addLink(G,mod_a,mod_b,p_entangle = 0.5, t_attempt = 1.0):
    # Until first successful entanglement, probability follows a geometric distribution
    #   i.e. Probability the  kth attempt succeeds is P(K = k) = (1-p_entangle)^(k-1)*p_entangle
    #   Therefore, E(K) = 1/p_entangle 
    t_expected = t_attempt/p_entangle
    
    G.add_edge(mod_a,
               mod_b,
               p_entangle = p_entangle,
               t_attempt = t_attempt,
               t_expected = t_expected,
               busy_until = 0.0)
    
def buildLinearChain(n_modules,p_entangle = 0.5, t_attempt = 1.0):
    G = nx.Graph()
    for i in range(n_modules):
        addModule(G,i)
    
    for i in range(n_modules - 1):
        addLink(G,i,i+1,p_entangle,t_attempt)
    return G

def buildRing(n_modules, p_entangle = 0.5, t_attempt = 1.0):
    G = buildLinearChain(n_modules,p_entangle,t_attempt)
    addLink(G,0,n_modules-1,p_entangle,t_attempt)
    return G
