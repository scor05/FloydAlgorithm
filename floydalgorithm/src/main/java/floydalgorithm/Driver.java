package floydalgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Programa principal
 * Los grafos se ingresan en el .txt 'matriz.txt', colocando primero el nombre
 * del nodo seguido por ';' y luego su correspondiente fila completa de la
 * matriz de adyacencia.
 */
public class Driver {

    public static void main(String[] args) {
        HashMap<String, ArrayList<Double>> data;
        try {
            data = (HashMap<String, ArrayList<Double>>) readFile("matriz.txt");
        } catch (IOException e) {
            throw new RuntimeException("El archivo no se pudo leer exitosamente, pruebe de nuevo.");
        }

        ArrayList<String> nodos = new ArrayList<>();
        ArrayList<ArrayList<Double>> adyacencia = new ArrayList<>();

        for (String n : data.keySet()) {
            nodos.add(n);
            adyacencia.add(data.get(n));
        }

        Graph g = new Graph(nodos, adyacencia);

        Resultado shortest = g.floydAlgorithm(g);

        System.out.println("El resultado de correr el algorítmo de Floyd en el grafo ingresado es el siguiente: ");
        System.out.println("\n- Matriz de caminos: \n");
        for (ArrayList<String> s : shortest.getLetterMatrix()) {
            System.out.println("\t" + s);
        }

        System.out.println("\n- Matriz de distancias: \n");
        for (ArrayList<Double> i : shortest.getDistMatrix()) {
            System.out.println("\t" + i);
        }

        // Centro del grafo -> nodo con menor eccentricidad (magnitud de ruta más corta "más larga")
        HashMap<String, Double> shortestRoutes = new HashMap<>();
        int nodeIndex = 0;
        for (ArrayList<Double> row : shortest.getDistMatrix()){
            Collections.sort(row);
            Double largest = row.get(row.size() - 1);
            shortestRoutes.put(g.getNodos().get(nodeIndex), largest);
            nodeIndex++;
        }

        String center = Collections.min(shortestRoutes.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("\n - Centro del grafo: " + center);
    }

    public static Map<String, ArrayList<Double>> readFile(String fileName) throws IOException {
        Map<String, ArrayList<Double>> node = new LinkedHashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(";");
            String name = split[0];
            String[] weightsString = split[1].split(",");
            ArrayList<Double> weights = new ArrayList<>();
            for (String s : weightsString) {
                weights.add((Double.parseDouble(s) == -1) ? Double.POSITIVE_INFINITY : Double.parseDouble(s));
            }
            node.put(name, weights);
        }
        br.close();
        return node;
    }
}
