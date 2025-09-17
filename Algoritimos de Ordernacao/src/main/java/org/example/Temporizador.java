package org.example;
import java.util.Arrays;

public class Temporizador {

    //Atividade 4 --------------------------------------------------
    public static String temporizadorSmartBubbleSort(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.smartBubbleSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "SmartBubbleSort Tempo em segundos: " + tempoSegundos;
    }
    //----------------------------------------------------- Atividade 4

    //Atividade 5 --------------------------------------------------
    public static String temporizadorDualPivotQuickSor(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.dualPivotQuickSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "DualPivotQuickSort Tempo em segundos: " + tempoSegundos;
    }
    //----------------------------------------------------- Atividade 5

    //Atividade 2 --------------------------------------------------
    public static String temporizadorBubbleSort(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.bubbleSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "BubbleSort Tempo em segundos: " + tempoSegundos;
    }

    public static String temporizadorSelectionSort(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.selectionSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "SelectionSort Tempo em segundos: " + tempoSegundos;
    }

    public static String temporizadorInsertionSort(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.insertionSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "InsertionSort Tempo em segundos: " + tempoSegundos;
    }

    public static String temporizadorShellSort(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.shellSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "ShellSort Tempo em segundos: " + tempoSegundos;
    }

    public static String temporizadorMergeSort(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.mergeSort(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "MergeSort Tempo em segundos: " + tempoSegundos;
    }

    public static String temporizadorQuickSortLomuto(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.quickSortLomuto(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "QuickSortLomuto Tempo em segundos: " + tempoSegundos;
    }

    public static String temporizadorQuickSortHoare(int[] vetor) {
        int[] copia = Arrays.copyOf(vetor, vetor.length);
        long inicio = System.nanoTime();
        Ordenar.quickSortHoare(copia);
        long fim = System.nanoTime();
        double tempoSegundos = (fim - inicio) / 1_000_000_000.0;
        return "QuickSortHoare Tempo em segundos: " + tempoSegundos;
    }
    //----------------------------------------------------- Atividade 2

}

