package com.example.view;

import java.util.Scanner;

public class MilitaryOptions {

    public static void militaryOptions() {
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("\n=============================");
            System.out.println("|      ### OPTIONS ###      |");
            System.out.println("=============================");
            System.out.println("|     1 - Send Message      |");
            System.out.println("|     2 - Join Channel      |");
            System.out.println("|     3 - Define Channel    |");
            System.out.println("|     4 - Create Task       |");
            System.out.println("|     5 - Accept Task       |");
            System.out.println("=============================\n");
            System.out.print("Select a option: ");
            option = scanner.nextInt();

        } while (option < 0 && option > 5);
    }
}
