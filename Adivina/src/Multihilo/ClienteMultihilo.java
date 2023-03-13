package Multihilo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMultihilo {

    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la dirección IP del servidor: ");
        String ip = sc.nextLine();

        System.out.print("Introduce el puerto del servidor: ");
        int puerto = sc.nextInt();
        sc.nextLine();

        socket = new Socket(ip, puerto);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        Thread hiloLeer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String respuesta;
                    while ((respuesta = in.readLine()) != null) {
                        System.out.println(respuesta);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hiloLeer.start();

        String entrada;

        do {
            entrada = sc.nextLine();
            out.println(entrada);

        } while (!entrada.equals("FIN"));

        in.close();
        out.close();
        socket.close();
    }
}
