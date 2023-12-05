package com.example.TCP;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int SERVER_PORT = 2048;
    private static final int THREAD_POOL_SIZE = 10;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            server.setReuseAddress(true);
            System.out.println("Server started. Listening on port " + SERVER_PORT);

            while (true) {
                Socket client = server.accept();
                System.out.println("NEW CONNECTION: IP:" + client.getInetAddress().getHostAddress() + " PORT:" + client.getPort());

                ClientHandler clientHandler = new ClientHandler(client);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    String currentTime = getCurrentTime();
                    String clientInfo = "IP:" + clientSocket.getInetAddress().getHostAddress() + " PORT:" + clientSocket.getPort();
                    System.out.println(String.format("[%s || %s] MESSAGE: %s", currentTime, clientInfo, line));
                    out.println("SERVER MESSAGE RECEIVED: " + line);
                }
            } catch (IOException e) {
                System.err.println("Error handling client connection: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }

        private String getCurrentTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(new Date());
        }
    }
}
