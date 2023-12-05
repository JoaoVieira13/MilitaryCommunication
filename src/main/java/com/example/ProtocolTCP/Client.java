package com.example.ProtocolTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.view.startMenu;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        // generate random port for new client
        int port = (int) (Math.random() * 10000);

        try {
            System.out.println("local host: " + InetAddress.getLocalHost() + " port: " + port);
            echoSocket = new Socket("192.168.56.1", 7, InetAddress.getLocalHost(), port);

            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
            startMenu.startMenu();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + InetAddress.getLocalHost());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for"
                    + "the connection to:" + InetAddress.getLocalHost() + " port: " + port);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
