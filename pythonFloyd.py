import networkx as nx
import math

def read_graph_from_file(filename):
    with open(filename, 'r') as f:
        lines = f.readlines()

    nodes = []
    matrix = []

    for line in lines:
        name, weights_str = line.strip().split(";")
        nodes.append(name)
        weights = [float(w) if float(w) != -1 else math.inf for w in weights_str.split(",")]
        matrix.append(weights)

    return nodes, matrix


def build_graph(nodes, matrix):
    G = nx.DiGraph() # Grafo direccionado
    for i, src in enumerate(nodes):
        for j, weight in enumerate(matrix[i]):
            if weight != math.inf and i != j:
                dst = nodes[j]
                G.add_edge(src, dst, weight=weight)
    return G


def analisisFloyd(filename):
    nodes, matrix = read_graph_from_file(filename)
    G = build_graph(nodes, matrix)

    # Algorítmo ya implementado de networkX, retorna un doble diccionario con las distancias mínimas tal que dist[source][target] = distancia mínima.
    dist = nx.floyd_warshall(G, weight="weight")

    print("\n- Matriz de distancias mínimas:")
    for src in nodes:
        fila = []
        for dst in nodes:
            d = dist[src].get(dst, math.inf)
            fila.append(f"{d:.1f}" if d != math.inf else "∞")
        print("\t", fila)

    eccentricities = {}
    for node in nodes:
        max_dist = max(d for d in dist[node].values() if d != math.inf)
        eccentricities[node] = max_dist

    center = min(eccentricities, key=eccentricities.get)
    print("\n- Centro del grafo:", center)


analisisFloyd("floydalgorithm/matriz.txt")
