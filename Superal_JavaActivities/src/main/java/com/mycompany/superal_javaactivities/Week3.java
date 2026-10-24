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
            input.nextLine(); 
            boolean loginSuccessful = false;
            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                // input credentials
                System.out.print("Enter username: ");
                String loginUsername = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();
                String result = login(loginUsername, password, accountLocked);
                System.out.println(result);
                // Stop on success (retrying isn't needed)
                if (result.startsWith("Login successful")) {
                    loginSuccessful = true;
                    break;
                }
                int remaining = maxAttempts - attempt;
                if (remaining > 0) {
                    System.out.println("Attempts left: " + remaining + "\n");
                }
            }
            if (!loginSuccessful) {
                // Reached the limit and still wrong, the account will be locked
                accountLocked = true;
                System.out.println("Too many failed attempts. The account is now locked.\n");
                // Rerun again to see if the account is locked
                System.out.print("Enter username: ");
                String loginUsername = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();
                System.out.println(login(loginUsername, password, accountLocked));
            }
        System.out.println("--------------------------");

        System.out.println();
        // (HARD) ATM TRANSACTION AUTHORIZER
        System.out.println("(HARD) ATM TRANSACTION AUTHORIZER");
            boolean authenticated = false;
            boolean approved = false;
            String denialReason = "";
            String atmTier = "";
            double feePercent = 0;
            double fee = 0;
            // Customer input
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter 4-digit PIN: ");
            int pin = input.nextInt();
            System.out.print("Enter withdrawal amount: ");
            double amount = input.nextDouble();
            // Saved account details
            String correctUsername = "tala";
            int correctPin = 6767;
            double balance = 6700.00;
            double withdrawnToday = 1000.00;
            if (username.equals(correctUsername)) {
                if (pin == correctPin) {
                    authenticated = true;
                } else {
                    denialReason = "Incorrect PIN.";
                }
            } else {
                denialReason = "Username not found.";
            }
            // Nothing else proceeds unless authentication passed
            if (authenticated) {
                // REQUIREMENT 2: else-if ladder picks the tier from the balance
                if (balance >= 50000) {
                    atmTier = "Platinum";
                } else if (balance >= 20000) {
                    atmTier = "Gold";
                } else if (balance >= 5000) {
                    atmTier = "Silver";
                } else {
                    atmTier = "Bronze";
                }
                double dailyLimit;
                switch (atmTier) {
                    case "Platinum" -> {
                        feePercent = 0.0;
                        dailyLimit = 50000;
                    }
                    case "Gold" -> {
                        feePercent = 0.5;
                        dailyLimit = 10000;
                    }
                    case "Silver" -> {
                        feePercent = 1.0;
                        dailyLimit = 3000;
                    }
                    default -> {
                        feePercent = 2.0;
                        dailyLimit = 1000;
                    }
                }
                if (amount <= 0) {
                    denialReason = "Withdrawal amount must be greater than 0.";
                } else {
                    fee = amount * feePercent / 100;
                    if (amount + fee <= balance) {                      
                        if (withdrawnToday + amount <= dailyLimit) {    
                            approved = true;
                        } else {
                            denialReason = String.format(
                                "Daily limit exceeded. %s limit is %.2f and %.2f was already withdrawn today.",
                                atmTier, dailyLimit, withdrawnToday);
                        }
                    } else {
                        denialReason = String.format(
                            "Insufficient balance. Withdrawal plus fee is %.2f but balance is %.2f.",
                            amount + fee, balance);
                    }
                }
            }
            System.out.println("\n=== ATM Result ===");
            if (authenticated) {
                System.out.println("Tier:    " + atmTier + " (fee " + feePercent + "%)");
            }
            if (approved) {
                System.out.println("Status:  APPROVED");
                System.out.printf("Amount:  %.2f%n", amount);
                System.out.printf("Fee:     %.2f%n", fee);
                System.out.printf("New balance: %.2f%n", balance - amount - fee);
            } else {
                System.out.println("Status:  DENIED");
                System.out.println("Reason:  " + denialReason);
            }
        }
    }
    // login() for multi-tier discount is outside of main because it is a method 
    static final String CORRECT_USERNAME = "admin";
    static final String CORRECT_PASSWORD = "pass123";
    static String login(String username, String password, boolean accountLocked) {
        if (username.equals(CORRECT_USERNAME)) {                    
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
