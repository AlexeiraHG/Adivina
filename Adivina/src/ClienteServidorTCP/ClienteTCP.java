package ClienteServidorTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 4444);
        System.out.println("Conectado al servidor");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        String serverMessage = input.readLine();
        String clientMessage;
        System.out.println("Mensaje del servidor: " + serverMessage);

        Scanner scanner = new Scanner(System.in);
        while (true) {

            serverMessage = input.readLine();
            System.out.println("Mensaje del servidor: " + serverMessage);

            // enviar mensaje de la consola al servidor
            clientMessage = scanner.nextLine();
            output.println(clientMessage);

            if (serverMessage.equals("Has acertado el número!")) {
                break;
            }
        }

        socket.close();
        scanner.close();
    }

}
