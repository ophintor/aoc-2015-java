package aoc.y2015;

import java.io.IOException;
import java.util.List;

public class Day8 {
    static List<String> data;
    static {
        try {
            data = Util.ReadFile("resources/input8.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static class SpecialString {
        public String str;
        public int strLength;

        public SpecialString(String s) {
            str = s;
            strLength = str.length();
        }

        public int decodedLength() {
            String memStr = str;

            // Remove surrounding strings
            memStr = memStr.replaceAll("^\"|\"$", "");

            // Replace escaped quotes
            memStr = memStr.replaceAll("\\\\\"", "\"");

            // Replace escaped slashes
            memStr = memStr.replaceAll("\\\\\\\\", "?");

            // Replace hex chars
            memStr = memStr.replaceAll("\\\\x[0-9a-f]{2}", "?");

            return memStr.length();
        }

        public int encodedLength() {
            String codeStr = str;

            // Escape slashes
            codeStr = codeStr.replaceAll("\\\\", "\\\\\\\\");

            // Escape quotes
            codeStr = codeStr.replaceAll("\"", "\\\\\"");

            // Add surrounding strings
            codeStr = "\"" + codeStr + "\"";

            return codeStr.length();
        }
    }

    public static void part1() {
        int total = 0;
        for (String word : data) {
            SpecialString ss = new SpecialString(word);
            int memStringLength = ss.decodedLength();
            total += ss.strLength - memStringLength;
        }
        System.out.println("Solution part 1: " + total);
    }

    public static void part2() {
        int total = 0;
        for (String word : data) {
            SpecialString ss = new SpecialString(word);
            int codeStringLength = ss.encodedLength();
            total += codeStringLength - ss.strLength;
        }
        System.out.println("Solution part 2: " + total);    }

    public static void main(String[] args) {
        part1();
        part2();
    }
}
