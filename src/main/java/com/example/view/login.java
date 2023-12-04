package com.example.view;

import java.util.List;
import java.util.Scanner;

import com.example.User;
import com.example.JSON.SaveJsonToFile;
import com.google.gson.reflect.TypeToken;

public class login {

    public static void login() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean loginSuccessful = false;

            do {
                System.out.println("\n=============================");
                System.out.println("|       ### LOGIN  ###      |");
                System.out.println("=============================\n");
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                List<User> existingUsers = SaveJsonToFile.loadObjects("users.json", new TypeToken<List<User>>() {}.getType());

                if (isValidLogin(existingUsers, username, password)) {
                    System.out.println("\u001B[32mLogin successful!\u001B[0m");
                    loginSuccessful = true;
                } else {
                    System.out.println("\u001B[31mInvalid username or password. Please try again.\u001B[0m");
                    System.out.println("Do you wish to try again? (y/n)");
                    String answer = scanner.nextLine();
                    if (answer.equals("n")) {
                        startMenu.startMenu();
                        return; 
                    }
                }
            } while (!loginSuccessful);
        }
    }

    private static boolean isValidLogin(List<User> users, String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}