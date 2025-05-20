package floydalgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Programa principal
 * Los grafos se ingresan en el .txt 'matriz.txt', colocando primero el nombre
 * del nodo seguido por ';' y luego su correspondiente fila completa de la
 * matriz de adyacencia.
 */
public class Driver {

    public static void main(String[] args) {
        HashMap<String, ArrayList<Integer>> data;
        try {
            data = (HashMap<String, ArrayList<Integer>>) readFile("matriz.txt");
        } catch (IOException e) {
            throw new RuntimeException("El archivo no se pudo leer exitosamente, pruebe de nuevo.");
        }

        ArrayList<String> nodos = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adyacencia = new ArrayList<>();

        for (String n : data.keySet()) {
            nodos.add(n);
            adyacencia.add(data.get(n));
        }

        Graph g = new Graph(nodos, adyacencia);

        HashMap<ArrayList<ArrayList<String>>, ArrayList<ArrayList<Integer>>> shortest = (HashMap) floydAlgorithm(g);

    }

    public static Map<ArrayList<ArrayList<Integer>>, ArrayList<ArrayList<String>>> floydAlgorithm(Graph g) {
        ArrayList<String> graphNodes = g.getNodos();
        ArrayList<ArrayList<Integer>> graphMatrix = g.getAdyacencia();

        // Crear matriz de letras
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

        for (ArrayList<Integer> a : graphMatrix) {
            System.out.println(a);
        }

        // Crear matriz con los datos
        ArrayList<ArrayList<Integer>> distMatrix = g.getAdyacencia();

        Map<ArrayList<ArrayList<String>>, ArrayList<ArrayList<Integer>>> result = new HashMap<>();
        result.put(letterMatrix, distMatrix);
        return (HashMap) result;
    }

    public static Map<String, ArrayList<Integer>> readFile(String fileName) throws IOException {
        Map<String, ArrayList<Integer>> node = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(";");
            String name = split[0];
            String[] weightsString = split[1].split(",");
            ArrayList<Integer> weights = new ArrayList<>();
            for (String s : weightsString) {
                weights.add(Integer.parseInt(s));
            }
            node.put(name, weights);
        }
        br.close();
        return node;
    }
}
