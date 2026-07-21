import matplotlib.pyplot as plt
import matplotlib.patches as mpatches

def plot_gantt(schedule, n_modules, qubit_map, title="Schedule Gantt Chart"):
    fig, ax = plt.subplots(figsize=(12, 4))

    for gate, start in schedule:
        color = 'tomato' if gate.is_remote else 'steelblue'

        if gate.is_remote:
            rows = [gate.module_a, gate.module_b]
        else:
            rows = [qubit_map[gate.qubits[0]]]

        for row in rows:
            ax.broken_barh(
                [(start, gate.duration)],
                (row - 0.4, 0.8),
                facecolors=color,
                edgecolor='white',
                linewidth=0.5,
                alpha=0.7
            )

        # label on first row only
        ax.text(
            start + gate.duration / 2, rows[0],
            f"G{gate.id}", ha='center', va='center',
            color='white', fontsize=7
        )

    ax.set_xlim(0, max(t + gate.duration for gate, t in schedule) + 1)
    ax.set_yticks(range(n_modules))
    ax.set_yticklabels([f"Module {i}" for i in range(n_modules)])
    ax.set_xlabel("Time")
    ax.set_title(title)

    local_patch = mpatches.Patch(color='steelblue', label='Local gate')
    remote_patch = mpatches.Patch(color='tomato', label='Remote gate')
    ax.legend(handles=[local_patch, remote_patch])

    plt.tight_layout()
    plt.show()