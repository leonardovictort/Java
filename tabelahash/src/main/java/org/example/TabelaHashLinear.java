package org.example;

import java.util.Arrays;

public class TabelaHashLinear extends TabelaHash{

    int sondagem = 0;

    public TabelaHashLinear(int tamanho) {
        super(tamanho);
    }

    public int hash(int chave){
        return chave % this.mod;
    }

    public void resize(){
        int[] vetorAuxiliar = this.vetor;
        this.vetor = new int[this.vetor.length * 2];
        this.mod = primoMaisProximo();
        Arrays.fill(this.vetor,-1);

        this.sondagem = 0;

        for(int chave: vetorAuxiliar){
            if(chave!= -1 && chave != -2) {
                inserir(chave);
            }
        }
    }

    @Override
    public String inserir(int chave) {
        int posicao = hash(chave);
        int inicio = posicao;

        if(this.sondagem >= this.vetor.length * 0.75){
            resize();
            posicao = hash(chave);
            inicio = posicao;
        }

        do{
            if(this.vetor[posicao] == -1 || this.vetor[posicao] == -2){
                this.vetor[posicao] = chave;
                this.sondagem++;
                return "Chave "+chave+" Inserida na posição: "+posicao;
            }

            posicao = (posicao + 1) % this.vetor.length;
        }while(posicao != inicio);
        return "Erro";
    }

    @Override
    public String buscar(int chave) {
        int posicao = hash(chave);
        int inicio = posicao;

        do {
            if (this.vetor[posicao] == chave) {
                return "A chave: " + chave + " está na posição: " + posicao;
            }

            posicao = (posicao +1 ) % this.vetor.length;
        }while (posicao != inicio);
        return "Chave não encontrada";
    }

    @Override
    public String remover(int chave) {
        int posicao = hash(chave);
        int inicio = posicao;

        do{
            if(this.vetor[posicao] == chave){
                this.vetor[posicao] = -2;
                this.sondagem--;
                return "Chave: "+chave+" removida da posição:"+posicao;
            }
            posicao = (posicao+1) % this.vetor.length;
        }while(posicao != inicio);
        return "Chave:"+chave+" não encontrada.";
    }

}
