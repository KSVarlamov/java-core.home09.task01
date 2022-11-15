package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    private final String host;

    public Client(String host) {
        this.host = host;
    }

    @Override
    public void run() {
        try (Socket clientSocket = new Socket(host, 9745);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("hi");
            String resp = in.readLine();
            System.out.printf("Server: %s \n", resp);
            if (resp.equals("Write your name")) {
                out.println("John Doe");
            }
            resp = in.readLine();
            System.out.printf("Server: %s \n", resp);

            if ("Are you child? (yes/no)".equals(resp)) {
//                out.println("yes");
                out.println("no");
//                out.println("234234");

            }
            resp = in.readLine();
            System.out.printf("Server: %s \n", resp);

            out.println("exit");
            resp = in.readLine();
            System.out.printf("Server: %s \n", resp);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
