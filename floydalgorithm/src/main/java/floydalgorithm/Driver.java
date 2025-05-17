package floydalgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Programa principal
 * Los grafos se ingresan en el .txt 'matriz.txt', colocando primero el nombre del nodo seguido por ';' y luego su correspondiente fila completa de la
 * matriz de adyacencia.
 */
public class Driver {

    public static void main(String[] args) {
        HashMap<String, ArrayList<Integer>> data;
        try{
            data = (HashMap<String, ArrayList<Integer>>) readFile("matriz.txt");
        }catch(IOException e){
            throw new RuntimeException("El archivo no se pudo leer exitosamente, pruebe de nuevo.");
        }
        
        ArrayList<String> names = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adyacencia = new ArrayList<>();

        for (String n : data.keySet()){
            names.add(n);
            adyacencia.add(data.get(n));
        }

        for (int i = 0; i < names.size(); i++){
            System.out.println(names.get(i) + adyacencia.get(i));
        }
    }


    public static Map<String, ArrayList<Integer>> readFile(String fileName) throws IOException {
        Map<String, ArrayList<Integer>> node = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null){
            String[] split = line.split(";");
            String name = split[0];
            String[] weightsString = split[1].split(",");
            ArrayList<Integer> weights = new ArrayList<>();
            for (String s : weightsString){
                weights.add(Integer.parseInt(s));
            }
            node.put(name, weights);
        }
        return node;
    }
}
