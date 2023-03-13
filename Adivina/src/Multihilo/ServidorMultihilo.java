package Multihilo;

import java.io.*;
import java.net.*;

public class ServidorMultihilo {
    private static final int PUERTO = 12345;
    private static final int MAXIMO = 100;
    private static int numAzar;
    private static boolean acierto;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado. Esperando conexiones...");

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Conexión aceptada de " + cliente.getInetAddress().getHostName());

            // Generar número aleatorio
            numAzar = generarNumero(MAXIMO);
            acierto = false;

            // Crear un nuevo hilo para el cliente
            Thread hiloCliente = new Thread(new ManejadorCliente(cliente));
            hiloCliente.start();
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket cliente;

        public ManejadorCliente(Socket cliente) {
            this.cliente = cliente;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

                // Enviar mensaje de bienvenida al cliente
                out.println(
                        "\n*************************************\n*   Juego del Adivinar 2ºDAM 2023   *\n*************************************\n");

                while (!acierto) {
                    String linea = in.readLine();
                    int numUsuario = Integer.parseInt(linea);
                    int info = infoNumero(numUsuario, numAzar);
                    infoUsuario(info, out);
                }

                cliente.close();
            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
            }
        }
    }

    private static int generarNumero(int max) {
        return (int) (Math.random() * max);
    }

    private static int infoNumero(int num, int azar) {
        if (azar == num) {
            return 0;
        } else if (azar > num) {
            return 1;
        } else {
            return -1;
        }
    }

    private static void infoUsuario(int num, PrintWriter out) {
        acierto = false;
        if (num == 0) {
            out.println("\n*** Enhorabuena has acertado el número ***\n");
            acierto = true;
        } else if (num == 1) {
            out.println("\n\tEl número es: --> MAYOR <--");
        } else {
            out.println("\n\tEl número es: --> MENOR <--");
        }
    }
}
