package org.example;
import java.util.Arrays;

public abstract class TabelaHash {

    int[] vetor;
    int mod;

    public TabelaHash(int tamanho){
        this.vetor = new int[tamanho];
        this.mod = primoMaisProximo();
        Arrays.fill(this.vetor,-1);
    }

    public abstract String inserir(int chave);
    public abstract String buscar(int chave);
    public abstract String remover(int chave);

    public int primoMaisProximo(){
        for(int i = this.vetor.length; i >=2; i--){
            if(isPrimo(i)){
                return i;
            }
        }
        return 2;
    }

    private boolean isPrimo(int numero){
        if(numero <= 1) return false;
        if(numero == 2) return true;
        if(numero % 2 == 0) return false;

        for(int i = 3; i*i <= numero; i+= 2){
            if (numero % i == 0){
                return false;
            }
        }
        return true;
    }

}
