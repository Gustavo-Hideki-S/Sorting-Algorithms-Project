package template.ordenacao;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort implements Ordenador {

    @Override
    public List<int[]> ordenar(int[] array) {
        List<int[]> passos = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            int min = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            copiar(array, passos);
            trocar(array, i, min);
        }

        copiar(array, passos);
        return passos;
    }

    private void trocar(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void copiar(int[] array, List<int[]> lista) {
        lista.add(array.clone());
    }
}