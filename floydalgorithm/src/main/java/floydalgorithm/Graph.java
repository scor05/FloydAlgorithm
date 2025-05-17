package floydalgorithm;

import java.util.ArrayList;

public class Graph {

    private ArrayList<ArrayList<Integer>> adyacencia;
    private ArrayList<String> nodos;

    public Graph() {
        this.adyacencia = new ArrayList<ArrayList<Integer>>();
        this.nodos = new ArrayList<String>();
    }

    /**
     * @param name
     * @param weights Lista de pesos con todos los otros nodos. Infinito se
     * representa por null.
     */
    public void addNode(String name, ArrayList<Integer> weights) throws RuntimeException {
        if (nodos.contains(name)) {
            throw new RuntimeException("El nodo a crear ya existe en el grafo.");
        }
        nodos.add(name);
        adyacencia.add(weights);
    }

    public void removeNode(String name) throws RuntimeException {
        int index = nodos.indexOf(name);
        if (index < 0) {
            throw new RuntimeException("El nodo a borrar no se encuentra en el grafo.");
        }

        adyacencia.remove(index);
        for (ArrayList<Integer> column : adyacencia) {
            column.remove(index);
        }
    }

    public ArrayList<ArrayList<Integer>> getAdyacencia() {
        return this.adyacencia;
    }

    public void setAdyacencia(ArrayList<ArrayList<Integer>> adyacencia) {
        this.adyacencia = adyacencia;
    }

    public ArrayList<String> getNodos() {
        return this.nodos;
    }

    public void setNodos(ArrayList<String> nodos) {
        this.nodos = nodos;
    }
}
