package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IControle extends Remote {

    String ligarDesligar() throws RemoteException;
    int canalUp() throws RemoteException;
    int canalDown() throws RemoteException;
    int selectCanal(int canal) throws RemoteException;
    int volumeUp() throws RemoteException;
    int volumeDown() throws RemoteException;

}
