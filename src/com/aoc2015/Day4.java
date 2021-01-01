package com.aoc2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {
    static String input = "yzbqklnj";

    public static class MD5Checksum {
        private String input;
        private byte[] digest;
        public String checksum;

        public MD5Checksum(String secretKey, Integer number) throws NoSuchAlgorithmException {
            input = secretKey.concat(number.toString());
            digest = createChecksum();
            checksum = getChecksum(digest);
        }

        private byte[] createChecksum() throws NoSuchAlgorithmException {
            byte[] message = input.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(message);
        }

        private String getChecksum(byte[] digest) {
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for(byte b: digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
    }

    public static int part1() {
        int number = 0;

        try {
            while (true) {
                MD5Checksum m = new MD5Checksum(input, number);
                if (m.checksum.startsWith("00000")) {
                    break;
                }
                number++;
            }
            System.out.println("Solution part 1: " + number);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static void part2(int number) {

        try {
            while (true) {
                MD5Checksum m = new MD5Checksum(input, number);
                if (m.checksum.startsWith("000000")) {
                    break;
                }
                number++;
            }
            System.out.println("Solution part 2: " + number);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        part2(part1());
    }
}
