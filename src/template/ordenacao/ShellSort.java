package template.ordenacao;

import java.util.ArrayList;
import java.util.List;

public class ShellSort implements Ordenador {

    @Override
    public List<int[]> ordenar(int[] array) {
        List<int[]> passos = new ArrayList<>();

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                int j = i;

                while (j >= gap && array[j - gap] > temp) {
                    array[j] = array[j - gap];
                    j -= gap;
                    passos.add(array.clone());
                }

                array[j] = temp;
                passos.add(array.clone());
            }
        }

        return passos;
    }
}