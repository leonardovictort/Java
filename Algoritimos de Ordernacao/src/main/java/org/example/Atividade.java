package org.example;

import java.util.Random;

public class Atividade {
    public static void main(String[] args) {

        Random random = new Random();

        int[] copia;
        int[] vetor = new int[100000];
        for(int i =0; i < 100000;i++){
            vetor[i] = random.nextInt(0,100000);
        }

        //Atividade 2 --------------------------------------------------
        for(int i = 1; i<= 10; i++) {
            System.out.println("\nAnalisando tempo de ordenação execução: "+i);
            System.out.println(Temporizador.temporizadorBubbleSort(vetor));
            System.out.println(Temporizador.temporizadorInsertionSort(vetor));
            System.out.println(Temporizador.temporizadorSelectionSort(vetor));
            System.out.println(Temporizador.temporizadorShellSort(vetor));
            System.out.println(Temporizador.temporizadorMergeSort(vetor));
            System.out.println(Temporizador.temporizadorQuickSortHoare(vetor));
            System.out.println(Temporizador.temporizadorQuickSortLomuto(vetor));
        }
        //----------------------------------------------------- Atividade 2


        //Atividade 4 --------------------------------------------------
        for(int i = 1; i<= 10; i++) {
            System.out.println("\nAnalisando tempo de ordenação execução: "+i);
            System.out.println(Temporizador.temporizadorSmartBubbleSort(vetor));
        }
        //----------------------------------------------------- Atividade 4

        //Atividade 5 --------------------------------------------------
        for(int i = 1; i<= 10; i++) {
            System.out.println("\nAnalisando tempo de ordenação execução: "+i);
            System.out.println(Temporizador.temporizadorDualPivotQuickSor(vetor));
        }
        //----------------------------------------------------- Atividade 5

    }
}