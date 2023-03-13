package ClienteServidorTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(4444);
        System.out.println("Servidor iniciado. Esperando conexión de clientes...");

        while (true) {
            Socket socket = listener.accept();
            System.out.println("Cliente conectado");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            output.println("¡Bienvenido al juego de adivinar el número!\n" +
                    "Elige un número entre 0 y 100 (inclusive)");

            int numAzar = generarNumero(101);
            boolean acierto = false;

            while (!acierto) {
                String clientMessage = input.readLine();
                int numUsuario = Integer.parseInt(clientMessage);

                int info = infoNumero(numUsuario, numAzar);
                if (info == 0) {
                    output.println("¡Has acertado el número!");
                    acierto = true;
                } else if (info == 1) {
                    output.println("El número es mayor que " + numUsuario);
                } else {
                    output.println("El número es menor que " + numUsuario);
                }
            }

            socket.close();
        }
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

}
