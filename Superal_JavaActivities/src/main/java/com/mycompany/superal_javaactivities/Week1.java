/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.superal_javaactivities;

import java.util.Arrays;

/**
 *
 * @author natal
 */
public class Week1 {

    public static void main(String[] args) {
        
        // (BASIC)PERSONAL PROFILE PRINTER
        System.out.println("(BASIC)PERSONAL PROFILE PRINTER");
        int age = 19;
        double height = 176.7;     
        char initial = 'N';
        boolean isStudent = true;
        String fullName = "Natalia Basilga Superal";
            System.out.println("=== My Profile ===");
            System.out.println("Full Name: " + fullName);
            System.out.println("Age: " + age);
            System.out.println("Height (cm): " + height);
            System.out.println("Initial: " + initial);
            System.out.println("Are you student?: " + isStudent);
            System.out.println("-------------------------------");
        
        // (INTERMEDIATE) TYPE SAFE SHOPPING CALCULATOR
        System.out.println();
        System.out.println("(INTERMEDIATE) TYPE SAFE SHOPPING CALCULATOR");
        int quantity = 5;
        double unitPrice = 10.55;
        String storeName = "Ate Aya's Store";
        //computation
        double totalCost = quantity * unitPrice;
        //output
            System.out.println("=== Purchase Summary ===");
            System.out.println("Store:      " + storeName);
            System.out.println("Quantity:   " + quantity);
            System.out.printf("Unit price: %.2f peso%n", unitPrice);
            System.out.printf("Total cost: %.2f peso%n", totalCost);
        /*
        *Why totalCost should NOT be an int?
        *Multiplying quantity by unitPrice produces a double because unitPrice
        *has decimal values. An int cannot store decimals, and converting a double
        *to an int removes the decimal part instead of rounding it.
        */
        System.out.println("-------------------------------");
        
        // (ADVANCED) UNIT CASTING CONVERTER 
        System.out.println();
        System.out.println("(ADVANCED) UNIT CASTING CONVERTER");
        // length to centimeters
        double centimeters = 275.8;
        // Explicit narrowing cast: (int) removes the decimal part, leaving only the WHOLE meters.
        int wholeMeters = (int) (centimeters / 100);
        // Centimeters left over once the whole meters are taken out.
        // wholeMeters is an int, but Java automatically widens it to double in this subtraction.
        double remainingCm = centimeters - (wholeMeters * 100);
            System.out.println("=== Centimeter Conversion ===");
            System.out.printf("Input:     %.1f cm%n", centimeters);
            System.out.printf("Result:    %d m and %.1f cm%n", wholeMeters, remainingCm);
        // --- Implicit widening example (int -> double) ---
        int itemCount = 42;
        // No cast is needed here: a double is a bigger type that can hold any int exactly,
        // so Java converts it on its own and nothing is lost.
        double widenedCount = itemCount;
            System.out.println("\n=== Implicit Widening ===");
            System.out.println("int " + itemCount + " -> double " + widenedCount);
        // --- Explicit narrowing example (double -> int) ---
        double measurement = 9.99;
        // A cast is required because a double might not fit into an int.
        // What gets lost: the whole decimal part (.99). Java cuts it off instead of
        // rounding, so 9.99 turns into 9, not 10.
        int narrowedMeasurement = (int) measurement;
            System.out.println("\n=== Explicit Narrowing ===");
            System.out.println("double " + measurement + " -> int " + narrowedMeasurement);
            System.out.println("-------------------------------");

        // (HARD) OVERFLOW AND REFERENCE INVESTIGATOR
        System.out.println();
        System.out.println("(HARD) OVERFLOW AND REFERENCE INVESTIGATOR");
        // ---------- Part 1: byte overflow ----------
        // A byte can only hold numbers from -128 to 127.
        byte number = 127;
            System.out.println("=== Part 1: Byte Overflow ===");
            System.out.println("Before adding 1: " + number);
        number++;   // 127 + 1 is too big for a byte, so it wraps around to -128
            System.out.println("After adding 1:  " + number);   // -128, not 128!
        // ---------- Part 2: Comparing Strings ----------
        String first = new String("hello");    // creates a brand new object
        String second = new String("hello");   // creates another brand new object
        String third = "hello";                // a literal (shared "String pool" object)
            System.out.println();
            System.out.println("=== Part 2: String Comparison ===");
        // first vs second
            System.out.println("first == second:      " + (first == second));
            System.out.println("first.equals(second): " + first.equals(second));
        // == is false: "new String" always makes a separate object, so these two variables point to different objects, even though the text is the same.
        // first vs third
            System.out.println("first == third:       " + (first == third));
            System.out.println("first.equals(third):  " + first.equals(third));
        // == is false: first is a new object, but third points to the pooled literal.
        // second vs third
            System.out.println("second == third:      " + (second == third));
            System.out.println("second.equals(third): " + second.equals(third));
        // == is false for the same reason: second is its own object and third points to the pooled literal.
        // .equals() is true every time because it compares the TEXT, while == only checks if two variables point to the SAME object.
        // ---------- Part 3: Shared array ----------
        int[] original = {1, 2, 3};
        int[] copy = original;    // this does NOT copy the array, it copies the reference
            System.out.println();
            System.out.println("=== Part 3: Shared Array ===");
            System.out.println("Before: original = " + Arrays.toString(original));
            System.out.println("Before: copy     = " + Arrays.toString(copy));
                copy[0] = 99;  
            System.out.println("After:  original = " + Arrays.toString(original));
            System.out.println("After:  copy     = " + Arrays.toString(copy));
            System.out.println("original == copy: " + (original == copy));   // true 
    }
}
