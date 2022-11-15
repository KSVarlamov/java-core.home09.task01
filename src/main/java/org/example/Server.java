package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private final int PORT = 9745;

    @Override
    public void run() {
        System.out.printf("Server started. listen port %s\n", PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept()) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("New connection accepted");
            String resp = in.readLine();
            System.out.printf("Client: %s \n", resp);

            out.println("Write your name");
            resp = in.readLine();
            System.out.printf("Client: %s \n", resp);
            String clientName = resp;

            out.println("Are you child? (yes/no)");
            resp = in.readLine();
            System.out.printf("Client: %s \n", resp);

            switch (resp) {
                case "yes":
                    out.println("Welcome to the kids area, " + clientName + " Let's play!");
                    break;
                case "no":
                    out.println("Welcome to the adult zone, " + clientName + "! Have a good rest, or a good working day!");
                    break;
                default:
                    out.println("unknown answer");
            }
            resp = in.readLine();
            System.out.printf("Client: %s \n", resp);
            if ("exit".equals(resp)) {
                out.println("Bye, " + clientName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}