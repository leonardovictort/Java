package org.example;

public class Ordenar {

    //Atividade 5 --------------------------------------------------
    public static void dualPivotQuickSort(int[] vetor){
        dualPivotQuickSort(vetor, 0, vetor.length - 1);
    }

    public static void dualPivotQuickSort(int[] vetor, int esquerda, int direita) {
        if (esquerda < direita) {
            if (vetor[esquerda] > vetor[direita]) {
                int temp = vetor[esquerda];
                vetor[esquerda] = vetor[direita];
                vetor[direita] = temp;
            }

            int pivoEsquerdo = vetor[esquerda];
            int pivoDireito = vetor[direita];

            int indice = esquerda + 1;
            int limiteMenor = esquerda + 1;
            int limiteMaior = direita - 1;

            while (indice <= limiteMaior) {
                if (vetor[indice] < pivoEsquerdo) {
                    trocar(vetor, indice, limiteMenor);
                    limiteMenor++;
                } else if (vetor[indice] > pivoDireito) {
                    trocar(vetor, indice, limiteMaior);
                    limiteMaior--;
                    indice--;
                }
                indice++;
            }

            limiteMenor--;
            limiteMaior++;

            trocar(vetor, esquerda, limiteMenor);
            trocar(vetor, direita, limiteMaior);

            dualPivotQuickSort(vetor, esquerda, limiteMenor - 1);
            dualPivotQuickSort(vetor, limiteMenor + 1, limiteMaior - 1);
            dualPivotQuickSort(vetor, limiteMaior + 1, direita);
        }
    }
    //----------------------------------------------------- Atividade 5

    //Atividade 4 --------------------------------------------------
    public static void smartBubbleSort(int[] vetor) {
        for (int iteracao = 0; iteracao < vetor.length - 1; iteracao++) {
            boolean houveTroca = false;

            for (int indice = 0; indice < vetor.length - iteracao - 1; indice++) {
                if (vetor[indice] > vetor[indice + 1]) {
                    int temp = vetor[indice];
                    vetor[indice] = vetor[indice + 1];
                    vetor[indice + 1] = temp;
                    houveTroca = true;
                }
            }

            if (!houveTroca) {
                break;
            }
        }
    }
    //----------------------------------------------------- Atividade 4

    public static void bubbleSort(int[] vetor){
        for(int iteracao = 0; iteracao < vetor.length -1; iteracao++){

            for(int indice = 0; indice < vetor.length-iteracao-1;indice++){
                if(vetor[indice] > vetor[indice +1]){
                    int temp = vetor[indice];
                    vetor[indice] = vetor[indice+1];
                    vetor[indice+1] = temp;
                }
            }

        }
    }

    public static void selectionSort(int[] vetor) {
        for (int iteracao = 0; iteracao < vetor.length - 1; iteracao++) {
            int pos_menor = iteracao;

            for (int indice = iteracao + 1; indice < vetor.length; indice++) {
                if (vetor[indice] < vetor[pos_menor]) {
                    pos_menor = indice;
                }
            }

            if (pos_menor != iteracao) {
                int auxiliar = vetor[iteracao];
                vetor[iteracao] = vetor[pos_menor];
                vetor[pos_menor] = auxiliar;
            }
        }
    }

    public static void insertionSort(int[] vetor){
        for(int iteracao = 0; iteracao< vetor.length; iteracao++){
            int elemento = vetor[iteracao];
            int indice = iteracao - 1;

            while(indice >=0 && vetor[indice] > elemento){
                vetor[indice+1] = vetor[indice];
                indice--;
            }
            vetor[indice+1] = elemento;
        }
    }

    public static void shellSort(int[] vetor) {
        int tamanho = vetor.length;
        int intervalo = 1;

        while (intervalo < tamanho / 3) {
            intervalo = 3 * intervalo + 1;
        }

        while (intervalo >= 1) {
            for (int iteracao = intervalo; iteracao < tamanho; iteracao++) {
                int elemento = vetor[iteracao];
                int indice = iteracao - intervalo;

                while (indice >= 0 && vetor[indice] > elemento) {
                    vetor[indice + intervalo] = vetor[indice];
                    indice -= intervalo;
                }

                vetor[indice + intervalo] = elemento;
            }
            intervalo = intervalo / 3;
        }
    }

    public static void mergeSort(int[] vetor) {
        mergeSort(vetor, 0, vetor.length - 1);
    }

    private static void mergeSort(int[] vetor, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergeSort(vetor, esquerda, meio);
            mergeSort(vetor, meio + 1, direita);
            merge(vetor, esquerda, meio, direita);
        }
    }

    private static void merge(int[] vetor, int esquerda, int meio, int direita) {
        int[] helper = new int[vetor.length];

        for (int i = esquerda; i <= direita; i++) {
            helper[i] = vetor[i];
        }

        int i = esquerda;
        int j = meio + 1;
        int k = esquerda;

        while (i <= meio && j <= direita) {
            if (helper[i] <= helper[j]) {
                vetor[k] = helper[i];
                i++;
            } else {
                vetor[k] = helper[j];
                j++;
            }
            k++;
        }

        while (i <= meio) {
            vetor[k] = helper[i];
            i++;
            k++;
        }

    }

    public static void quickSortHoare(int[] vetor){
        quickSortHoare(vetor, 0, vetor.length - 1);
    }

    private static void quickSortHoare(int[] vetor, int esquerda, int direita) {
        if (esquerda < direita) {
            int p = partitionHoare(vetor, esquerda, direita);
            quickSortHoare(vetor, esquerda, p);
            quickSortHoare(vetor, p + 1, direita);
        }
    }

    private static int partitionHoare(int[] vetor, int esquerda, int direita) {
        int pivo = vetor[esquerda];
        int i = esquerda - 1;
        int j = direita + 1;

        while (true) {
            do {
                i++;
            } while (vetor[i] < pivo);

            do {
                j--;
            } while (vetor[j] > pivo);

            if (i >= j) {
                return j;
            }

            trocar(vetor, i, j);
        }
    }

    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    public static void quickSortLomuto(int[] vetor) {
        quickSortLomuto(vetor, 0, vetor.length - 1);
    }

    private static void quickSortLomuto(int[] vetor, int esquerda, int direita) {
        if (esquerda < direita) {
            int pivoIndice = partitionLomuto(vetor, esquerda, direita);
            quickSortLomuto(vetor, esquerda, pivoIndice - 1);
            quickSortLomuto(vetor, pivoIndice + 1, direita);
        }
    }

    private static int partitionLomuto(int[] vetor, int esquerda, int direita) {
        int pivo = vetor[direita];
        int i = esquerda - 1;

        for (int indice = esquerda; indice < direita; indice++) {
            if (vetor[indice] <= pivo) {
                i++;
                trocar(vetor, i, indice);
            }
        }

        trocar(vetor, i + 1, direita);
        return i + 1;
    }

    public static String imprimir(int[] vetor){
        String list = " ";
        for(int i = 0; i <= vetor.length-1;i++){
            list += " "+vetor[i]+" ";
        }
        return list;
    }

}

