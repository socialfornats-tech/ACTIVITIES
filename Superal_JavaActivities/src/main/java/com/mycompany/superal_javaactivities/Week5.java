/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.superal_javaactivities;



import java.util.Arrays;
/**
 *
 * @author natal
 */
public class Week5 {

    public static void main(String[] args) {
        // (BASIC) FAVORITE THINGS ARRAY
        System.out.println("(BASIC) FAVORITE THINGS ARRAY");
        String[] favorites = {
            "Desserts",
            "Pastries",
            "Food",
            "Rainy Days",
            "Watching Ghibli Movies"
        };
        System.out.println("=== My Favorite Things ===");
        for (int i = 0; i < favorites.length; i++) {
            System.out.println((i + 1) + ". " + favorites[i]);
        }
        System.out.println("Total favorites: " + favorites.length);
        System.out.println("--------------------------");

        // (INTERMEDIATE) CLASSIC STATISTICS CALCULATOR
        System.out.println();
        System.out.println("(INTERMEDIATE) CLASSIC STATISTICS CALCULATOR");
        int[] scores = {67, 89, 67, 56, 65, 48, 79, 99, 69, 85};
        int sum = 0;
        int highest = scores[0];
        int lowest = scores[0];

        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
            if (scores[i] > highest) {
                highest = scores[i];
            }
            if (scores[i] < lowest) {
                lowest = scores[i];
            }
        }

        double average = (double) sum / scores.length;
        System.out.println("=== Class Statistics ===");
        System.out.println("Number of scores: " + scores.length);
        System.out.println("Sum:              " + sum);
        System.out.printf("Average:          %.2f%n", average);
        System.out.println("Highest score:    " + highest);
        System.out.println("Lowest score:     " + lowest);
        System.out.println("--------------------------");

        // (ADVANCED) SEARCH AND SORT MINI TOOL
        System.out.println();
        System.out.println("(ADVANCED) SEARCH AND SORT MINI TOOL");
        int[] searchScores = {67, 76, 96, 69, 81, 18, 27, 72, 13, 31};
        int target = 81;

        // ---------- Linear Search ----------
        int foundIndex = -1;

        for (int i = 0; i < searchScores.length; i++) {
            if (searchScores[i] == target) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Target " + target + " found at index " + foundIndex);
        } else {
            System.out.println("Target " + target + " was not found.");
        }

        // ---------- Sort a Copy ----------
        int[] sorted = Arrays.copyOf(searchScores, searchScores.length);
        Arrays.sort(sorted);

        System.out.println();
        System.out.println("Original: " + Arrays.toString(searchScores));
        System.out.println("Sorted:   " + Arrays.toString(sorted));

        /* Why sort a copy?
        I sorted a copy to keep the original scores in the same order. Sorting 
        the original array would move the scores to different positions, which 
        could make it confusing to track them. Using a copy lets me arrange the
        scores from lowest to highest without changing the original array. */
        System.out.println("--------------------------");

        // (HARD) CLASS RANKING SYSTEM
        System.out.println();
        System.out.println("(HARD) STUDENT CLASS LEADERBOARD");
        runStudentLeaderboard();
    }
    /*THIS PART CONTAINS IT'S OWN CLASS TO RUN AND SEVERAL HELPER METHODS SO I MOVED IT INSTEAD
    TO THE OUTSIDE OF MAIN CLASS BUT STILL INSIDE OF THE WEEK5 CLASS*/
    // Student Class Leaderboard
    static class Student {
        String name;
        int[] quizzes;
        Student(String name, int[] quizzes) {
            this.name = name;
            this.quizzes = quizzes.clone();     // Copy quiz scores
        }
        // Calculate the student's average
        double average() {
            int sum = 0;
            for (int i = 0; i < quizzes.length; i++) {
                sum += quizzes[i];
            }
            return (double) sum / quizzes.length;
        }
        // Convert quiz scores into a string
        String quizzesText() {
            String text = "";
            for (int i = 0; i < quizzes.length; i++) {
                text += quizzes[i];
                if (i < quizzes.length - 1) {
                    text += " ";
                }
            }
            return text;
        }
    }
    // ---------- OWN SORT: highest average first ----------
    private static void sortByAverage(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[j].average() > students[i].average()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }
    // ---------- OWN LINEAR SEARCH: returns rank or -1 ----------
    private static int findRank(Student[] students, String name) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].name.equalsIgnoreCase(name)) {
                // Count rank, accounting for tied averages
                int rank = 1;
                for (int j = 1; j <= i; j++) {
                    if (students[j].average() != students[j - 1].average()) {
                        rank = j + 1;
                    }
                }
                return rank;
            }
        }
        return -1; // Student not found
    }
    // ---------- Print leaderboard ----------
    private static void printLeaderboard(Student[] students) {
        System.out.println("=== Class Leaderboard ===");
        System.out.printf("%-6s%-10s%-20s%8s%n",
                "Rank", "Name", "Quizzes", "Average");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < students.length; i++) {
            int rank = findRank(students, students[i].name);
            boolean tiedWithPrev = i > 0
                    && students[i].average() == students[i - 1].average();
            boolean tiedWithNext = i < students.length - 1
                    && students[i].average() == students[i + 1].average();
            String note = (tiedWithPrev || tiedWithNext) ? "  <- tie" : "";
            System.out.printf("%-6d%-10s%-20s%8.2f%s%n",
                    rank, students[i].name, students[i].quizzesText(),
                    students[i].average(), note);
        }
    }
    // ---------- Run Student Leaderboard ----------
    private static void runStudentLeaderboard() {
        // Raw data: one row of quiz scores per student
        String[] names = {"Ben", "Maria", "Kaeley", "Hanah", "Jahzel", "Hazel"};
        int[][] rawScores = {
            {90, 85, 92, 88},       // Ben: 88.75
            {78, 82, 80, 76},       // Maria: 79.00
            {95, 91, 89, 97},       // Kaeley: 93.00
            {70, 65, 74, 71},       // Hanah: 70.00
            {88, 90, 86, 88},       // Jahzel: 88.00
            {85, 92, 90, 88}        // Hazel: 88.75 (ties with Ben)
        };
        // Build Student objects from the 2D array
        Student[] students = new Student[names.length];
        for (int i = 0; i < names.length; i++) {
            students[i] = new Student(names[i], rawScores[i]);
        }
        // Sort a copy so the original student order stays intact
        Student[] ranked = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            ranked[i] = students[i];
        }
        sortByAverage(ranked);
        printLeaderboard(ranked);
        // ---------- Search demo ----------
        System.out.println();
        String[] lookups = {"Kaeley", "Hanah", "Hazel", "Ben", "Natalia"};
        for (int i = 0; i < lookups.length; i++) {
            int rank = findRank(ranked, lookups[i]);
            if (rank != -1) {
                System.out.println(lookups[i] + " is ranked #" + rank);
            } else {
                System.out.println(lookups[i] + " was not found.");
            }
        }
    }
}
