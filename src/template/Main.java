package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;
import java.util.List;
import template.ordenacao.*;

public class Main extends EngineFrame {

    // arrays base
    private int[] array;
    private int[] array2;

    // listas de passos (frames)
    List<int[]> arraysSelection;
    List<int[]> arraysInsertion;
    List<int[]> arraysShell;
    List<int[]> arraysMerge;

    // controle de animação
    private int copiaSelection = 0;
    private int copiaInsertion = 0;
    private int copiaShell = 0;
    private int copiaMerge = 0;

    private double tempoParaMudar;
    private double contadorTempo;

    // desenho
    private int tamanho;
    private int espaco;
    private int corAtual = 0;

    private boolean apertado = false;

    public Main() {

        super(
                650,
                700,
                "Ordenações",
                60,
                true,
                false,
                false,
                false,
                false,
                false
        );

    }

    @Override
    public void create() {

        array = new int[]{7, 3, 1, 2, 9, 4, 6, 8, 5, 10};
        array2 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        tempoParaMudar = 0.5;

        tamanho = 20;
        espaco = 5;

        executarOrdenacoes(array);

    }

    @Override
    public void update(double delta) {

        if (isMouseButtonPressed(1)) {
            int x = getMouseX();
            int y = getMouseY();

            if (x > 25 && x < 91 && y > 45 && y < 65) {
                alternarArray();
            }
        }

        contadorTempo += delta;

        if (contadorTempo >= tempoParaMudar) {
            contadorTempo = 0;

            if (copiaSelection < arraysSelection.size() - 1) copiaSelection++;
            if (copiaInsertion < arraysInsertion.size() - 1) copiaInsertion++;
            if (copiaShell < arraysShell.size() - 1) copiaShell++;
            if (copiaMerge < arraysMerge.size() - 1) copiaMerge++;
        }

    }

    @Override
    public void draw() {

        clearBackground(WHITE);

        setFontSize(12);
        drawText("Projeto desenvolvido com a engine JSGE", 10, 690, GRAY);

        setFontSize(40);
        drawText("Projeto Ordenações", 120, 15, BLACK);

        fillRectangle(25, 45, 66, 20, ORANGE);
        drawRectangle(25, 45, 66, 20, GRAY);
        drawText("Alternar", 27, 50, 14, BLACK);

        // divisões
        drawRectangle(25, 70, 300, 350, BLACK);
        drawRectangle(getScreenWidth() / 2, 70, 300, 350, BLACK);
        drawRectangle(25, getScreenHeight() / 2 + 20, 300, 300, BLACK);
        drawRectangle(getScreenWidth() / 2, getScreenHeight() / 2 + 20, 300, 300, BLACK);

        // títulos
        setFontSize(20);
        drawText("Selection Sort", getScreenWidth() / 8, 90, BLACK);
        drawText("Insertion Sort", getScreenWidth() / 2 + 60, 90, BLACK);
        drawText("Shell Sort", getScreenWidth() / 8, getScreenHeight() / 2 + 40, BLACK);
        drawText("Merge Sort", getScreenWidth() / 2 + 60, getScreenHeight() / 2 + 40, BLACK);

        // desenhos
        desenharArray(arraysSelection.get(copiaSelection), 50, getScreenHeight() / 2 + 20);
        desenharArray(arraysInsertion.get(copiaInsertion), getScreenWidth() / 2 + 30, getScreenHeight() / 2 + 20);
        desenharArray(arraysShell.get(copiaShell), 50, getScreenHeight() - 30);
        desenharArray(arraysMerge.get(copiaMerge), getScreenWidth() / 2 + 30, getScreenHeight() - 30);

    }

    // 🔥 método central (nova lógica)
    private void executarOrdenacoes(int[] base) {

        Ordenador selection = new SelectionSort();
        Ordenador insertion = new InsertionSort();
        Ordenador shell = new ShellSort();
        Ordenador merge = new MergeSort();

        arraysSelection = selection.ordenar(base.clone());
        arraysInsertion = insertion.ordenar(base.clone());
        arraysShell = shell.ordenar(base.clone());
        arraysMerge = merge.ordenar(base.clone());

        // reset animação
        copiaSelection = 0;
        copiaInsertion = 0;
        copiaShell = 0;
        copiaMerge = 0;
        contadorTempo = 0;
        corAtual = 0;

    }

    private void desenharArray(int[] a, int xIni, int yIni) {

        Color[] cores = {BLUE, RED, ORANGE, PURPLE};
        Color cor = cores[corAtual % cores.length];

        for (int i = 0; i < a.length; i++) {

            int altura = tamanho * a[i];

            fillRectangle(
                    xIni + (tamanho + espaco) * i,
                    yIni - altura,
                    tamanho,
                    altura,
                    cor
            );

            drawRectangle(
                    xIni + (tamanho + espaco) * i,
                    yIni - altura,
                    tamanho,
                    altura,
                    BLACK
            );

            if (a[i] == i + 1) {
                drawText(String.valueOf(a[i]),
                        xIni + (tamanho + espaco) * i,
                        yIni - altura - 15,
                        GREEN);
            } else {
                drawText(String.valueOf(a[i]),
                        xIni + (tamanho + espaco) * i,
                        yIni - altura - 15,
                        GRAY);
            }
        }

        corAtual++;

    }

    public void alternarArray() {

        if (!apertado) {
            executarOrdenacoes(array2);
        } else {
            executarOrdenacoes(array);
        }

        apertado = !apertado;

    }

    public static void main(String[] args) {
        new Main();
    }

}