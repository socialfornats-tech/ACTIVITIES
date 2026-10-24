/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.superal_javaactivities;

/**
 *
 * @author natal
 */

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Week4 {
    private static final String DIVIDER = "----------------------------------------";
    
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            
        // (BASIC) NUMBER AND SQUARE PRINTER
        System.out.println("(BASIC) NUMBER AND SQUARE PRINTER");
        int limit = 10;
        for (int i = 1; i <= limit; i++) {
            System.out.println("Number: " + i + "  Square: " + (i * i));
            System.out.println("--------------------------");
        }

        // (INTERMEDIATE) SUM UNTIL SENTINEL VALUE
        System.out.println();
        System.out.println("(INTERMEDIATE) SUM UNTIL SENTINEL VALUE");
            int total = 0;
            System.out.print("Enter a number (-1 to stop): ");
            int number = input.nextInt();
            // -1 is checked before adding, so it never reaches the total
            while (number != -1) {
                total += number;
                System.out.print("Enter a number (-1 to stop): ");
                number = input.nextInt();
            }
            System.out.println("Total: " + total);
            System.out.println("--------------------------");

        // (ADVANCED) PATTERN GENERATOR WITH EARLY EXIT
        System.out.println();
        System.out.println("(ADVANCED) PATTERN GENERATOR WITH EARLY EXIT");
        int size = 6;
        int skipRow = 3;
        int stopCol = 4;
        for (int row = 1; row <= size; row++) {
            if (row == skipRow) {
                continue;           // skips row 3 (no stars, no newline)
            }
            for (int col = 1; col <= row; col++) {
                if (col > stopCol) {
                    break;          // exits the INNER loop early after column 4
                }
                System.out.print("* ");
            }
            System.out.println();   // newline after each row
        }
        System.out.println("--------------------------");

        // (HARD) WAREHOUSE INVENTORY SCANNER
        System.out.println();
        System.out.println("(HARD) WAREHOUSE INVENTORY SCANNER");
        // ---------- Entry point: DO-WHILE menu ----------
        Warehouse warehouse = new Warehouse(new int[][] {
            {21, -1, 14, 25},
            { 8,  3, 19, 22},
            {40,  5, -1, 15}
        });
            int choice;
            try {
                do {
                    printMenu();
                    choice = readInt(input, "Choose an option: ");
                    System.out.println(DIVIDER);    // DIVIDER: separates the picked option from its answer
                    switch (choice) {
                        case 1 -> warehouse.display();
                        case 2 -> reportTotal(warehouse);
                        case 3 -> reportCriticalShelf(warehouse);
                        case 4 -> restockMenu(warehouse, input);
                        case 5 -> System.out.println("Goodbye!");
                        default -> System.out.println("Please choose 1-5.");
                    }
                } while (choice != 5);
            } catch (NoSuchElementException e) {
                System.out.println("\nNo more input available. Exiting.");
            }
        }
    }
    // ---------- Menu and report helpers ----------
    private static void printMenu() {
        System.out.println("\n=== Warehouse Inventory Scanner ===");
        System.out.println("1. Display warehouse");
        System.out.println("2. Total inventory");
        System.out.println("3. Find first critically low shelf");
        System.out.println("4. Restock a shelf");
        System.out.println("5. Exit");
    }
    private static void reportTotal(Warehouse w) {
        System.out.println("Total inventory: " + w.totalInventory() + " items");
        System.out.println("Damaged shelves skipped: " + w.countDamaged());
    }
    private static void reportCriticalShelf(Warehouse w) {
        int[] spot = w.findFirstCritical();
        if (spot == null) {
            System.out.println("No critically low shelves.");
        } else {
            System.out.println("First critically low shelf: row " + spot[0]
                    + ", shelf " + spot[1] + " (" + w.getCount(spot[0], spot[1]) + " items)");
        }
    }
    // ---------- WHILE + RETURN: restock with input validation ----------
    private static void restockMenu(Warehouse w, Scanner sc) {
        final int maxAttempts = 3;
        int attempts = 0;
        // WHILE: keep asking until the restock succeeds or attempts run out
        while (attempts < maxAttempts) {
            int row = readInt(sc, "Row (1-" + w.rows() + "): ");
            int col = readInt(sc, "Shelf (1-" + w.cols() + "): ");

            if (!w.isValidShelf(row, col)) {
                System.out.println("That shelf doesn't exist.");
                attempts++;
                continue;
            }
            if (w.isDamaged(row, col)) {
                System.out.println("That shelf is damaged and can't be restocked.");
                return;
            }
            int amount = readInt(sc, "Amount to add: ");
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                attempts++;
                continue;
            }
            w.addStock(row, col, amount);
            System.out.println("Restocked. Shelf now holds " + w.getCount(row, col) + " items.");
            return;
        }
        System.out.println("Too many invalid attempts. Restock cancelled.");
    }
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNext()) {
                throw new NoSuchElementException("No input available");
            }
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("Invalid input. Please enter a whole number.");
            sc.next();
        }
    }
}
// ---------- Data and operations ----------
final class Warehouse {
    static final int DAMAGED = -1;
    static final int CRITICAL = 5;
    private final int[][] shelves;
    Warehouse(int[][] initial) {
        this.shelves = new int[initial.length][];
        for (int r = 0; r < initial.length; r++) {
            this.shelves[r] = initial[r].clone();
        }
    }
    int rows() {
        return shelves.length;
    }
    int cols() {
        return shelves[0].length;
    }
    boolean isValidShelf(int row, int col) {
        return row >= 1 && row <= rows() && col >= 1 && col <= cols();
    }
    boolean isDamaged(int row, int col) {
        return shelves[row - 1][col - 1] == DAMAGED;
    }
    int getCount(int row, int col) {
        return shelves[row - 1][col - 1];
    }
    void addStock(int row, int col, int amount) {
        shelves[row - 1][col - 1] += amount;
    }
    void display() {
        System.out.println("(XX = damaged shelf)");
        for (int[] shelve : shelves) {
            for (int c = 0; c < shelve.length; c++) {
                if (shelve[c] == DAMAGED) {
                    System.out.printf("%4s", "XX");
                } else {
                    System.out.printf("%4d", shelve[c]);
                }
            }
            System.out.println();
        }
    }
    int totalInventory() {
        int total = 0;
        for (int[] shelve : shelves) {
            for (int c = 0; c < shelve.length; c++) {
                if (shelve[c] == DAMAGED) {
                    continue;
                }
                total += shelve[c];
            }
        }
        return total;
    }
    int countDamaged() {
        int count = 0;
        for (int[] shelve : shelves) {
            for (int c = 0; c < shelve.length; c++) {
                if (shelve[c] == DAMAGED) {
                    count++;
                }
            }
        }
        return count;
    }
    int[] findFirstCritical() {
        int foundRow = -1;
        int foundCol = -1;
        for (int r = 0; r < shelves.length; r++) {
            for (int c = 0; c < shelves[r].length; c++) {
                if (shelves[r][c] == DAMAGED) {
                    continue;
                }
                if (shelves[r][c] <= CRITICAL) {
                    foundRow = r + 1;
                    foundCol = c + 1;
                    break;
                }
            }
            if (foundRow != -1) {
                break;
            }
        }
        return (foundRow == -1) ? null : new int[] {foundRow, foundCol};
    }
}
