/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.superal_javaactivities;

import java.util.Scanner;

/**
 *
 * @author natal
 */
public class Week3 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
        // (BASIC) SIMPLE GRADE CLASSIFIER 
        System.out.println("(BASIC) SIMPLE GRADE CLASSIFIER");
            System.out.print("Enter first score: ");
            int score1 = input.nextInt();
            System.out.print("Enter second score: ");
            int score2 = input.nextInt();
 
            if (score1 >= 60) {
                System.out.println("Score " + score1 + ": PASSED");
            } else {
                System.out.println("Score " + score1 + ": FAILED (BELOW 60)");
            }
 
            if (score2 >= 60) {
                System.out.println("Score " + score2 + ": PASSED");
            } else {
                System.out.println("Score " + score2 + ": FAILED (BELOW 60)");
           }
        }
        System.out.println("--------------------------");
        
        
        System.out.println();
        // (INTERMEDIATE) MULTI-TIER DISCOUNT SYSTEM
        System.out.println("(INTERMEDIATE) MULTI-TIER DISCOUNT SYSTEM");
        double purchaseAmount = 2500.00;
 
        int discountPercent;
        String tier;
 
        // Highest amount is checked first so every tier can be reached
        if (purchaseAmount >= 5000) {
            discountPercent = 15;
            tier = "Tier 4";
        } else if (purchaseAmount >= 2000) {
            discountPercent = 10;
            tier = "Tier 3";
        } else if (purchaseAmount >= 1000) {
            discountPercent = 5;
            tier = "Tier 2";
        } else {
            discountPercent = 0;
            tier = "Tier 1";
        }
 
        double finalPrice = purchaseAmount - (purchaseAmount * discountPercent / 100);
 
        System.out.println("=== Discount Summary ===");
        System.out.printf("Purchase amount: %.2f%n", purchaseAmount);
        System.out.println("Discount tier:   " + tier + " (" + discountPercent + "%)");
        System.out.printf("Final price:     %.2f%n", finalPrice);
        System.out.println("--------------------------");
        
        
        System.out.println();
        // (ADVANCED) NESTED LOGIN VALIDATOR
        System.out.println("(ADVANCED) NESTED LOGIN VALIDATOR");
        boolean accountLocked = false;      
        int maxAttempts = 3;                

        try (Scanner input = new Scanner(System.in)) {
            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                // input credentials
                System.out.print("Enter username: ");
                String username = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();

                String result = login(username, password, accountLocked);
                System.out.println(result);

                // Stop on success (retrying isn't needed)
                if (result.startsWith("Login successful")) {
                    return;
                }

                int remaining = maxAttempts - attempt;
                if (remaining > 0) {
                    System.out.println("Attempts left: " + remaining + "\n");
                }
            }

            // Reached the limit and still wrong, the account will be lock 
            accountLocked = true;
            System.out.println("Too many failed attempts. The account is now locked.\n");

            //Rerun again to see if the account is locked
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();
            System.out.println(login(username, password, accountLocked));
        }
    }

    static final String CORRECT_USERNAME = "admin";
    static final String CORRECT_PASSWORD = "pass123";

    static String login(String username, String password, boolean accountLocked) {
        if (username.equals(CORRECT_USERNAME)) {                    // Stage 1: username
            if (password.equals(CORRECT_PASSWORD)) {                // Stage 2: password (only if username matched)
                if (!accountLocked) {                               // Stage 3: account status (only if BOTH matched)
                    return "Login successful. Welcome, " + username + "!";
                } else {
                    return "Login failed: account is locked.";      // Stage 3 failure
                }
            } else {
                return "Login failed: incorrect password.";         // Stage 2 failure
            }
        } else {
            return "Login failed: username not found.";             // Stage 1 failure
        }
    }
}
