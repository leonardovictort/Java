import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ServidorUDP {
    public static void main(String[] args) {
        Map<String, String> leituras = new HashMap<>();

        try (DatagramSocket socket = new DatagramSocket(12345)) {
            System.out.println("Servidor UDP aguardando dados dos sensores...");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote);

                String mensagem = new String(pacote.getData(), 0, pacote.getLength());
                String[] partes = mensagem.split(":");
                if (partes.length == 2) {
                    leituras.put(partes[0], partes[1]);
                }

                // Exibir leituras atuais
                System.out.println("Leituras mais recentes:");
                leituras.forEach((sensor, valor) -> System.out.println(sensor + " -> " + valor));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
