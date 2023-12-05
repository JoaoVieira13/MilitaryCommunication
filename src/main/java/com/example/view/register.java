package com.example.view;


import com.example.models.User;
import com.example.JSON.SaveJsonToFile;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Scanner;

public class register {

    public static void register() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("\n=============================");
            System.out.println("|     ### REGISTER  ###     |");
            System.out.println("=============================\n");

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Repeat your password: ");
            String repeatPassword = scanner.nextLine();

            if (!password.equals(repeatPassword)) {
                System.out.println("Passwords do not match. Please try again.");
                register();
                return;
            }

            User user = new User();
            user.setName(username);
            user.setPassword(password);
            user.setProfile("Soldier");

            List<User> existingUsers = SaveJsonToFile.loadObjects("users.json", new TypeToken<List<User>>() {}.getType());

            if (isUsernameTaken(existingUsers, username)) {
                System.out.println("Username already taken. Please choose a different one.");
            } else {
                existingUsers.add(user);

                SaveJsonToFile.saveJsonToFile(existingUsers, "users.json");

                System.out.println("User registered successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isUsernameTaken(List<User> users, String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
