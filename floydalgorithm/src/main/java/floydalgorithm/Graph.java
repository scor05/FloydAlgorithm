package floydalgorithm;

import java.util.ArrayList;

public class Graph {

    private ArrayList<ArrayList<Integer>> adyacencia;
    private ArrayList<String> nodos;

    public Graph(ArrayList<String> nodos, ArrayList<ArrayList<Integer>> adyacencia) {
        this.adyacencia = adyacencia;
        this.nodos = nodos;
    }

    /**
     * @param name
     * @param weights Lista de pesos con todos los otros nodos. Infinito se
     *                representa por -1, el grafo no acepta pesos negativos.
     */
    public void addNode(String name, ArrayList<Integer> weights) throws RuntimeException {
        if (nodos.contains(name)) {
            throw new RuntimeException("El nodo a crear ya existe en el grafo.");
        }
        for (Integer num : weights) {
            if (num < 0 && num != -1) {
                throw new RuntimeException(
                        "El grafo no acepta numeros negativos (exceptuando -1 para representar infinito).");
            }
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
