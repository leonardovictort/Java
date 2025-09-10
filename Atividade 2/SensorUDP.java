import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class SensorUDP {
    public static void main(String[] args) {
		
		//no terminal inicie o programa com parametros, exemplo java SensorUDP sensor2
        String nomeSensor = (args.length > 0) ? args[0] : "Sensor1";
        Random random = new Random();

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress enderecoServidor = InetAddress.getByName("localhost");

            while (true) {
                double valor = 20 + random.nextDouble() * 10;
                String mensagem = nomeSensor + ":" + String.format("%.2f°C", valor);

                byte[] dados = mensagem.getBytes();
                DatagramPacket pacote = new DatagramPacket(dados, dados.length, enderecoServidor, 12345);
                socket.send(pacote);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
