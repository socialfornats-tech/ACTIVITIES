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
public class Week2 {
    public static void main(String[] args) {
        
        // (BASIC)FOUR FUNCTION CALCULATOR
        System.out.println("(BASIC)FOUR FUNCTION CALCULATOR");
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter first integer: ");
            int firstNum = input.nextInt();
            System.out.print("Enter second integer: ");
            int secNum = input.nextInt();
            System.out.println("\n=== Results for " + firstNum + " and " + secNum + " ===");
            System.out.println("Addition:       " + firstNum + " + " + secNum + " = " + (firstNum + secNum));
            System.out.println("Subtraction:    " + firstNum + " - " + secNum + " = " + (firstNum - secNum));
            System.out.println("Multiplication: " + firstNum + " * " + secNum + " = " + (firstNum * secNum));
            // Division and modulus fail with an int divided by zero, so check b first
            if (secNum != 0) {
                // Negative operands: / and % are the ones that behave differently.
                // Integer division cuts off the decimal toward zero (-7 / 2 = -3, not -4),
                // and the % result takes the sign of the left number (-7 % 2 = -1).
                System.out.println("Division:       " + firstNum + " / " + secNum + " = " + (firstNum / secNum));
                System.out.println("Modulus:        " + firstNum + " % " + secNum + " = " + (firstNum % secNum));
            } else {
                System.out.println("Division:       cannot divide by zero");
                System.out.println("Modulus:        cannot divide by zero");
            }
        }
        System.out.println("--------------------------");
        
        
        System.out.println();
        // (INTERMEDIATE) GRADE TRESHOLD CHECKER
        System.out.println("(INTERMEDIATE) GRADE TRESHOLD CHECKER");
        // College grading: 1.0 is the highest, 5.0 is the lowest
        double grade = 2.25;
        int absences = 2;
 
        // Pass if (grade <= 3.0 AND absences <= 3) OR grade <= 1.75
        boolean meetsHighGradeRule = grade <= 1.75;
        boolean passes = (grade <= 3.0 && absences <= 3) || (grade <= 1.75);
        System.out.println("=== Eligibility Check ===");
        System.out.println("Grade:    " + grade);
        System.out.println("Absences: " + absences);
         if (passes) {
                System.out.println("Result: PASS");
                if (meetsHighGradeRule) {
                    System.out.println("Reason: grade " + grade + " is 1.75 or better (absences ignored).");
                } else {
                    System.out.println("Reason: grade " + grade + " <= 3.0 and absences " + absences + " <= 3.");
                }
            } else {
                System.out.println("Result: FAIL");
                if (grade > 3.0) {
                    System.out.println("Reason: grade " + grade + " is worse than 3.0.");
                } else {
                    System.out.println("Reason: absences " + absences + " > 3 and grade " + grade + " is worse than 1.75.");
            }
         }
         System.out.println("--------------------------");
         
         System.out.println();
        // (ADVANCED) COMPOUND GROWTH & INCREMENT TRACKER
        System.out.println("(ADVANCED) COMPOUND GROWTH & INCREMENT TRACKER");
         // --- Compound assignment: balance growing over time ---
        double balance = 1000.0;
        System.out.println("=== Balance Growth ===");
        System.out.println("Start:             " + balance);
        balance += 500;      // deposit of 500
        System.out.println("After += 500:      " + balance);
        balance *= 1.05;     // 5% interest
        System.out.println("After *= 1.05:     " + balance);
        balance += 250;      // another deposit of 250
        System.out.println("After += 250:      " + balance);
        balance *= 1.10;     // 10% bonus
        System.out.println("After *= 1.10:     " + balance);
        // --- Postfix vs prefix increment ---
        int count = 5;
        System.out.println("\n=== Postfix: count++ ===");
        System.out.println("count++ prints:    " + count++);
        System.out.println("count afterwards:  " + count);
        count = 5; // reset so both tests start from the same value
        System.out.println("\n=== Prefix: ++count ===");
        System.out.println("++count prints:    " + ++count);
        System.out.println("count afterwards:  " + count);
        /*
         * The output is different because count++ and ++count work differently
         * With count++, the old value (5) is printed first, then count becomes 6.
         * With ++count, count becomes 6 first, so 6 is printed right away.
         * In the end, both have the same final value, which is 6. The only
         * difference is whether the value is increased before or after printing.
         */
        System.out.println("--------------------------");
        
        System.out.println();
        // (HARD) PRECEDENCE PUZZLE SOLVER
        System.out.println("(HARD) PRECEDENCE PUZZLE SOLVER");
        int a = 5;
        int b = 3;
        int c = 2;
        int x = 4;
        System.out.println("=== Precedence Puzzle Solver ===");
        // Puzzle 1: The answer is true because a + b * c = 11, which is greater than 10.
        // Also, a % b = 2, so both conditions are true.
        System.out.println("Puzzle 1: " + (a + b * c > 10 && a % b == 2));
        // Puzzle 2: The answer is 16. x++ uses the old value (4), then x becomes 5.
        // After that, ++x makes x become 6. Since multiplication comes first,
        // 6 * 2 = 12, then 4 + 12 = 16.
        System.out.println("Puzzle 2: " + (x++ + ++x * 2));
        // Puzzle 3: The answer is 7.0. First, 7 / 2 = 3 because both are integers.
        // Then, 3 * 2.0 = 6.0, and 1 % 2 = 1.
        // Finally, 6.0 + 1 = 7.0.
        System.out.println("Puzzle 3: " + (7 / 2 * 2.0 + 1 % 2));
    } 
}
