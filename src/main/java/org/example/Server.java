package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private int PORT = 9745;

    @Override
    public void run() {
        System.out.printf("Server started. listen port %s\n", PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept()) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("New connection accepted");
            final String name = in.readLine();
            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}