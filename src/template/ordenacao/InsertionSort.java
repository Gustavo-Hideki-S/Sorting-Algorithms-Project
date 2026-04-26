package template.ordenacao;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements Ordenador {

    @Override
    public List<int[]> ordenar(int[] array) {
        List<int[]> passos = new ArrayList<>();

        for (int j = 1; j < array.length; j++) {
            int chave = array[j];
            int i = j - 1;

            while (i >= 0 && array[i] > chave) {
                array[i + 1] = array[i];
                i--;
                passos.add(array.clone());
            }

            array[i + 1] = chave;
            passos.add(array.clone());
        }

        return passos;
    }
}