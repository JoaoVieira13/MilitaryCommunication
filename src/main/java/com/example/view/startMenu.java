package com.example.view;	

import java.util.Scanner;

public class startMenu {
    public static void startMenu() {
        int choice;
        Scanner option = new Scanner(System.in);

        do {

            System.out.println("\n\n                          ### Welcome to our SD project ###");
            System.out.println("\n                            =============================");
            System.out.println("                            |     1 - Register          |");
            System.out.println("                            |     2 - Login             |");
            System.out.println("                            |     3 - Exit              |");
            System.out.println("                            =============================\n");

            System.out.println("Option -> ");
            choice = option.nextInt();

            switch (choice) {
                case 1:
                    register.register();
                case 2:
                    login.login();
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (choice < 1 || choice > 3);
    }
}
