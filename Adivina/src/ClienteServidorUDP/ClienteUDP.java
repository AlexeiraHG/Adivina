package ClienteServidorUDP;

import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        byte[] mensaje = new byte[1024];
        String respuesta;

        DatagramPacket packet = new DatagramPacket(mensaje, mensaje.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(mensaje, mensaje.length);
        socket.receive(packet);
        respuesta = new String(packet.getData(), 0, packet.getLength());
        System.out.println(respuesta);
        socket.close();
    }
}
