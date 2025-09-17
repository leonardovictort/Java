package org.example;
import java.util.Scanner;

//Atividade 3 --------------------------------------------------
public class AtividadeTres {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Quantos valores deseja inserir");
        int tamanho = leitor.nextInt();
        int[] v = new int[tamanho];
        int elementosInseridos = 0;

        for(int i =0;i < tamanho ;i++){
            System.out.println("Informe Numero de 0 a "+tamanho+": ");
            int numero = leitor.nextInt();

            int posicao = 0;
            while (posicao < elementosInseridos && v[posicao] < numero) {
                posicao++;
            }

            for(int j = elementosInseridos; j > posicao; j--){
                v[j] = v[j-1];
            }

            v[posicao] = numero;
            elementosInseridos++;

            System.out.println(Ordenar.imprimir(v));
        }
    }
}
//----------------------------------------------------- Atividade 3