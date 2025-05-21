package floydalgorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class FloydTest {

    @Test
    public void addNodeTest() {
        Graph g = new Graph();

        for (int j = 0; j < 4; j++) {
            ArrayList<Double> weights = new ArrayList<Double>();
            for (Double i = 0d; i < 4; i++) {
                weights.add(i);
            }
            g.addNode(Integer.toString(j), weights); // Actualiza la matriz de adyacencia a una matriz de adyacencia 4x4 con valores fijos
        }

        ArrayList<ArrayList<Double>> matrixComparar = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (double j = 0; j < 4; j++) {
                row.add(j);
            }
            matrixComparar.add(row);
        }

        assertEquals(matrixComparar, g.getAdyacencia());
    }

    @Test
    public void removeNodeTest() {
        Graph g = new Graph();

        for (int j = 0; j < 4; j++) {
            ArrayList<Double> weights = new ArrayList<Double>();
            for (Double i = 0d; i < 4; i++) {
                weights.add(i);
            }
            g.addNode(Integer.toString(j), weights);
        }
        g.removeNode("2"); // Elimina la 3ra fila y columna de adyacencia.

        ArrayList<ArrayList<Double>> matrixComparar = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (double j = 0; j < 4; j++) {
                row.add(j);
            }
            matrixComparar.add(row);
        }

        matrixComparar.remove(2); // Elimina 3era fila
        for (int i = 0; i < matrixComparar.size(); i++){ // Elimina 3era columna
            matrixComparar.get(i).remove(2); 
        }

        assertEquals(matrixComparar, g.getAdyacencia());
    }


    @Test
    public void floydAlgorithmTest() {
        // Probar con un grafo simple de 4 nodos:
        /*
            A -> B con 10
            A -> C con 2
            C -> D con 1
            D -> B con 2
        */
        // Para A -> B, la distancia más corta sería 5:

        ArrayList<String> nodos = new ArrayList<String>(Arrays.asList("A", "B", "C", "D"));
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        ArrayList<Double> weightsA = new ArrayList<Double>(Arrays.asList(0d, 10d, 2d, Double.POSITIVE_INFINITY));
        ArrayList<Double> weightsB = new ArrayList<Double>(Arrays.asList(Double.POSITIVE_INFINITY, 0d,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY));
        ArrayList<Double> weightsC = new ArrayList<Double>(Arrays.asList(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0d,1d));
        ArrayList<Double> weightsD = new ArrayList<Double>(Arrays.asList(Double.POSITIVE_INFINITY,2d,Double.POSITIVE_INFINITY,0d));
        
        matrix.add(weightsA);
        matrix.add(weightsB);
        matrix.add(weightsC);
        matrix.add(weightsD);

        Graph g = new Graph(nodos, matrix);
        Resultado r = g.floydAlgorithm(g);
        
        // Únicamente es necesario revisar la primera fila, pues en esa se vería que A -> B = 5.
        ArrayList<Double> expected = new ArrayList<Double>(Arrays.asList(0d,5d,2d,3d));
        
        assertEquals(expected, r.getDistMatrix().get(0));
    }
}
