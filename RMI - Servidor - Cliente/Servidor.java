package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) throws RemoteException {
        try {
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("RMI Registry iniciado na porta 1099.");
            } catch (RemoteException e) {
                System.out.println("RMI Registry já estava em execução.");
            }

            IControle controle = new Controle();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("ControleService", controle);

            System.out.println("Servidor da Controle pronto.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e);
            e.printStackTrace();
        }
    }
}