package org.example;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;

public class Controle extends UnicastRemoteObject implements IControle {

    private boolean ligada = false;
    private int canal = 1;
    private int volume = 50;

    public Controle() throws RemoteException{
        super();
    }

    @Override
    public String ligarDesligar() throws RemoteException{
        if(!ligada){
            this.ligada = true;
            return "Ligando...";
        } else {
            this.ligada = false;
            return "Desligando o/";
        }
    }

    @Override
    public int canalUp() throws RemoteException{
        return this.canal ++;
    }

    @Override
    public int canalDown() throws RemoteException{
       return this.canal --;
    }

    @Override
    public int selectCanal(int canal) throws RemoteException{
        return this.canal = canal;
    }

    @Override
    public int volumeUp() throws RemoteException{
     if(this.volume < 100){
         this.volume++;
     }
     return this.volume;
    }

    @Override
    public int volumeDown() throws RemoteException{
        if(this.volume > 0){
            this.volume--;
        }
        return this.volume;
    }
}
