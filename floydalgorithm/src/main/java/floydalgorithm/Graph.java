package floydalgorithm;

import java.util.ArrayList;

public class Graph {

    private ArrayList<ArrayList<Double>> adyacencia;
    private ArrayList<String> nodos;

    public Graph() {
        this.adyacencia = new ArrayList<>();
        this.nodos = new ArrayList<>();
    }

    public Graph(ArrayList<String> nodos, ArrayList<ArrayList<Double>> adyacencia) {
        this.adyacencia = adyacencia;
        this.nodos = nodos;
    }

    public Resultado floydAlgorithm(Graph g) {
        ArrayList<String> graphNodes = g.getNodos();
        ArrayList<ArrayList<Double>> graphMatrix = g.getAdyacencia();

        // Crear matriz de letras con guiones en la diagonal
        byte diagonalIndex = 0;
        ArrayList<ArrayList<String>> letterMatrix = new ArrayList<>();
        for (int i = 0; i < graphMatrix.size(); i++) {
            ArrayList<String> column = new ArrayList<>();
            letterMatrix.add(column);
            for (int j = 0; j < graphNodes.size(); j++) {
                if (j != diagonalIndex) {
                    column.add(graphNodes.get(j));
                } else {
                    column.add("-");
                }
            }
            diagonalIndex++;
        }

        /*
         * Algorítmo de Floyd
         * Iterar: agarrando cada fila y columna de cada nodo (Ej. fila A, columna A),
         * iterar por cada elemento
         * de la fila y sumarlo al de la columna (repetir por cada elemento de la
         * columna); si el resultado existe (es != inf)
         * o es menor al elemento de la intersección (i, j), reemplazar el valor
         * Luego actualizar en letterMatrix (i, j) con el nombre del nodo que se evaluó
         * Complejidad algorítmica de n * n^2 = n^3.
         */

        String currentIterationString;
        int size = graphMatrix.size();
        for (int i = 0; i < size; i++) {
            currentIterationString = graphNodes.get(i);
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    double suma = graphMatrix.get(j).get(i) + graphMatrix.get(i).get(k);
                    if (suma < graphMatrix.get(j).get(k)) {
                        graphMatrix.get(j).set(k, suma);
                        letterMatrix.get(j).set(k, currentIterationString);
                    }
                }
            }
        }

        return new Resultado(letterMatrix, graphMatrix);
    }

    /**
     * @param name
     * @param weights Lista de pesos con todos los otros nodos. Infinito se
     *                representa por -1, el grafo no acepta pesos negativos.
     */
    public void addNode(String name, ArrayList<Double> weights) throws RuntimeException {
        if (nodos.contains(name)) {
            throw new RuntimeException("El nodo a crear ya existe en el grafo.");
        }
        for (Double num : weights) {
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
        for (ArrayList<Double> column : adyacencia) {
            column.remove(index);
        }
    }

    public ArrayList<ArrayList<Double>> getAdyacencia() {
        return this.adyacencia;
    }

    public void setAdyacencia(ArrayList<ArrayList<Double>> adyacencia) {
        this.adyacencia = adyacencia;
    }

    public ArrayList<String> getNodos() {
        return this.nodos;
    }

    public void setNodos(ArrayList<String> nodos) {
        this.nodos = nodos;
    }
}
