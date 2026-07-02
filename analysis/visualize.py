import matplotlib.pyplot as plt
import matplotlib.patches as mpatches

def plot_gantt(schedule, n_modules, qubit_map, title = "Schedule Gantt Chart"):
    fig, ax = plt.subplots(figsize = (12,4))

    for gate, start in schedule:
        color = 'tomato' if gate.is_remote else 'steelblue'
    # row => which module to draw on
    if gate.is_remote:
        row = gate.module_b
    else:
        row = qubit_map[gate.qubits[0]]

    for gate, start in schedule:
        print(f"  Drawing Gate {gate.id} start={start} duration={gate.duration} row={row}")

    ax.broken_barh(
        [(start, gate.duration)],
        (row - 0.4, 0.8),
        facecolors = color,
        edgecolor = 'white',
        linewidth = 0.5
    )

    ax.text (
            start + gate.duration / 2, row,
            f"G{gate.id}", ha='center', va='center',
            color='white', fontsize=8
        )
    
    ax.set_yticks(range(n_modules))
    ax.set_yticklabels([f"Module {i}" for i in range(n_modules)])
    ax.set_xlabel("Time")
    ax.set_title(title)

    local_patch = mpatches.Patch(color='steelblue', label='Local gate')
    remote_patch = mpatches.Patch(color='tomato', label='Remote gate')
    ax.legend(handles=[local_patch, remote_patch])

    ax.set_xlim(0, max(t + gate.duration for gate, t in schedule) + 1)

    plt.tight_layout()
    plt.show()
