package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        try(ServerSocket servidor = new ServerSocket(12345)){
            System.out.println("Servidor aguardando conexão...");
            Socket socket = servidor.accept();
            System.out.println("Cliente Conectado!");

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter saida = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Mensagem recebida: " + mensagem);
                saida.println("Servidor recebeu: " + mensagem);

                if(mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}