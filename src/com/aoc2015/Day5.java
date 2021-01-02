package com.aoc2015;

import java.io.IOException;
import java.util.*;

import static com.aoc2015.Util.ReadFile;

public class Day5 {
    static List<String> data;
    static {
        try {
            data = ReadFile("resources/input5.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static boolean containsThreeVowels(String s) {
        int numVowels = 0;
        String allVowels = "aeiou";

        for (int i = 0; i < s.length(); i++) {
            if (allVowels.indexOf(s.charAt(i)) != -1) {
                numVowels += 1;
                if (numVowels >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsLetterTwice(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsBadSubstrings(String s) {
        return (s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy"));
    }

    public static boolean containsPairOfTwoLettersMoreThanOnce(String s) {
        for (int i = 1; i < s.length(); i++) {
            int lastIndex = s.indexOf(s.substring(i-1, i+1), i+1);
            if (lastIndex != -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsLetterTwiceWithAnotherInBetween(String s) {
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 2)) {
                return true;
            }
        }
        return false;
    }

    public static void part1() {
        int total = 0;
        for (String line : data) {
            if (containsThreeVowels(line) && containsLetterTwice(line) && !containsBadSubstrings(line)) {
                total += 1;
            }
        }
        System.out.println("Solution part 1: " + total);
    }

    public static void part2() {
        int total = 0;
        for (String line : data) {
            if (containsPairOfTwoLettersMoreThanOnce(line) && containsLetterTwiceWithAnotherInBetween(line)) {
                total += 1;
            }
        }
        System.out.println("Solution part 2: " + total);
    }

    public static void main(String[] args) {
        part1();
        part2();
    }
}
