package org.example;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        try(Socket socket = new Socket("localhost",12345)) {
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String mensagem;
            while (true) {
                System.out.print("Digite a mensagem: ");
                mensagem = leitor.nextLine();
                saida.println(mensagem);

                String resposta = entrada.readLine();
                System.out.println(resposta);

                if(mensagem.equalsIgnoreCase("sair")) {
                    break; // encerra a conexão
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
