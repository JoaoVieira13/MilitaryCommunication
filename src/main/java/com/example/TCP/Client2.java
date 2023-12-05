package com.example.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2048)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println(serverResponse);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading server response: " + e.getMessage());
                }
            });

            while (true) {
                System.out.print("Enter message: ");
                String userInput = scanner.nextLine();
                out.println(userInput);

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

            executorService.shutdown();
        } catch (IOException e) {
            System.err.println("Error establishing connection to the server: " + e.getMessage());
        }
    }
}
