package floydalgorithm;

import java.util.ArrayList;

public class Resultado {
    private ArrayList<ArrayList<String>> letterMatrix;
    private ArrayList<ArrayList<Double>> distMatrix;

    public Resultado(ArrayList<ArrayList<String>> letterMatrix, ArrayList<ArrayList<Double>> distMatrix) {
        this.letterMatrix = letterMatrix;
        this.distMatrix = distMatrix;
    }

    public ArrayList<ArrayList<String>> getLetterMatrix() {
        return this.letterMatrix;
    }

    public void setLetterMatrix(ArrayList<ArrayList<String>> letterMatrix) {
        this.letterMatrix = letterMatrix;
    }

    public ArrayList<ArrayList<Double>> getDistMatrix() {
        return this.distMatrix;
    }

    public void setDistMatrix(ArrayList<ArrayList<Double>> distMatrix) {
        this.distMatrix = distMatrix;
    }

}
