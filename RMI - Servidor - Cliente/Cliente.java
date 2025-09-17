package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IControle controle = (IControle) registry.lookup("ControleService");

            System.out.println(controle.ligarDesligar());
            System.out.println("Select Canal: "+controle.selectCanal(11));
            System.out.println("Volume: "+controle.volumeUp());
            System.out.println("Volume: "+controle.volumeUp());
            System.out.println("Volume: "+controle.volumeDown());
            System.out.println("Canal: "+controle.canalDown());
            System.out.println("Canal: "+controle.canalUp());
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e);
            e.printStackTrace();
        }
    }
}
