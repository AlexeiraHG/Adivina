package ClienteServidorUDP;

import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(4445);
        byte[] mensaje = new byte[1024];

        int numAzar = generarNumero(MAXIMO);
        boolean acierto = false;
        String bienvenida = "\n*************************************\n*   Juego del Adivinar 2ºDAM 2023   *\n*************************************\n";

        while (!acierto) {
            DatagramPacket packet = new DatagramPacket(mensaje, mensaje.length);
            socket.receive(packet);
            String input = new String(packet.getData(), 0, packet.getLength());
            int numUsuario = Integer.parseInt(input.trim());
            int info = infoNumero(numUsuario, numAzar);
            String respuesta;
            if (info == 0) {
                respuesta = "\n*** Enhorabuena has acertado el número ***\n";
                acierto = true;
            } else if (info == 1) {
                respuesta = "\n\tEl número es: --> MAYOR <--";
            } else {
                respuesta = "\n\tEl número es: --> MENOR <--";
            }
            mensaje = respuesta.getBytes();
            packet = new DatagramPacket(mensaje, mensaje.length, packet.getAddress(), packet.getPort());
            socket.send(packet);
        }
        socket.close();
    }

    public static int generarNumero(int max) {
        return (int) (Math.random() * max);
    }

    public static int infoNumero(int num, int azar) {
        if (azar == num) {
            return 0;
        } else if (azar > num) {
            return 1;
        } else {
            return -1;
        }
    }

    public static final int MAXIMO = 100;
}
