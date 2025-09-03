package org.example;

public class Main {
    public static void main(String[] args) {

        TabelaHashLinear tabelaHashLinear = new TabelaHashLinear(10);

        System.out.println(tabelaHashLinear.mod);

        for(int i =0; i< tabelaHashLinear.vetor.length-1;i++){
            System.out.println("Posição: "+ i +" Chave: "+tabelaHashLinear.vetor[i]);
        }

        System.out.println(tabelaHashLinear.inserir(44));
        System.out.println(tabelaHashLinear.inserir(44));
        System.out.println(tabelaHashLinear.inserir(44));
        System.out.println(tabelaHashLinear.inserir(4));
        System.out.println(tabelaHashLinear.inserir(4));
        System.out.println(tabelaHashLinear.inserir(4));
        System.out.println(tabelaHashLinear.inserir(14));
        System.out.println(tabelaHashLinear.inserir(14));
        System.out.println(tabelaHashLinear.inserir(14));
        System.out.println(tabelaHashLinear.inserir(17));
        System.out.println(tabelaHashLinear.inserir(17));

        System.out.println(tabelaHashLinear.vetor.length);
        System.out.println(tabelaHashLinear.mod);
        System.out.println(tabelaHashLinear.sondagem);

        for(int chave: tabelaHashLinear.vetor){
            System.out.println(chave);
        }

        System.out.println(tabelaHashLinear.buscar(17));

        for(int i =0; i< tabelaHashLinear.vetor.length-1;i++){
            System.out.println("Posição: "+ i +" Chave: "+tabelaHashLinear.vetor[i]);
        }

        System.out.println(tabelaHashLinear.remover(17));

        for(int i =0; i< tabelaHashLinear.vetor.length-1;i++){
            System.out.println("Posição: "+ i +" Chave: "+tabelaHashLinear.vetor[i]);
        }


    }
}