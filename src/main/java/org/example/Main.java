package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.start();

        Client client = new Client("localhost");
        Thread.sleep(3000);
        client.start();
    }
}
