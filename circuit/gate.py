from dataclasses import dataclass, field
from typing import List, Optional

@dataclass
class Gate:
    id: int
    name: str
    qubits: List[int]
    duration: float = 1.0
    is_remote: bool = False
    module_a: int = None
    module_b: int = None