package template.ordenacao;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Ordenador {

    @Override
    public List<int[]> ordenar(int[] array) {
        List<int[]> passos = new ArrayList<>();
        mergeSort(array, passos, 0, array.length - 1);
        return passos;
    }

    private void mergeSort(int[] array, List<int[]> passos, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(array, passos, inicio, meio);
            mergeSort(array, passos, meio + 1, fim);

            intercala(array, passos, inicio, meio, fim);
        }
    }

    private void intercala(int[] array, List<int[]> passos, int inicio, int meio, int fim) {

        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) L[i] = array[inicio + i];
        for (int j = 0; j < n2; j++) R[j] = array[meio + 1 + j];

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        int i = 0, j = 0;

        for (int k = inicio; k <= fim; k++) {
            if (L[i] <= R[j]) {
                array[k] = L[i++];
            } else {
                array[k] = R[j++];
            }
            passos.add(array.clone());
        }
    }
}