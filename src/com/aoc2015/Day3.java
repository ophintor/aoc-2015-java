package com.aoc2015;
import java.io.IOException;
import java.util.*;
import static com.aoc2015.Util.ReadFile;

public class Day3 {
    static List<String> data;
    static {
        try {
            data = ReadFile("resources/input3.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    static String actualData = data.get(0);

    public static class DeliveryMan {
        ArrayList<Integer> currentCoords;
        Set<ArrayList<Integer>> visitedCoords = new HashSet<>();
        int deliveryPeople;

        public DeliveryMan() {
            currentCoords = new ArrayList<>(Arrays.asList(0,0));
            visitedCoords.add(currentCoords);
            deliveryPeople = 1;
        }

        public DeliveryMan(int people) {
            currentCoords = new ArrayList<>(Arrays.asList(0,0));
            visitedCoords.add(currentCoords);
            deliveryPeople = people;
        }

        private void initCoordinates() {
            currentCoords = new ArrayList<>(Arrays.asList(0,0));
        }

        public int deliver() {
            for (int p = 0; p < deliveryPeople; p ++) {
                initCoordinates();
                for (int i = p; i < actualData.length(); i += deliveryPeople) {
                    ArrayList<Integer> nextCoords = new ArrayList<>(currentCoords);
                    switch (actualData.charAt(i)) {
                        case '^':
                            nextCoords.set(1, nextCoords.get(1) + 1);
                            break;
                        case '>':
                            nextCoords.set(0, nextCoords.get(0) + 1);
                            break;
                        case '<':
                            nextCoords.set(0, nextCoords.get(0) - 1);
                            break;
                        case 'v':
                            nextCoords.set(1, nextCoords.get(1) - 1);
                            break;
                    }
                    currentCoords = new ArrayList<>(nextCoords);
                    visitedCoords.add(currentCoords);
                }
            }
            return visitedCoords.size();
        }
    }

    public static void part1() {
        DeliveryMan santa = new DeliveryMan();
        System.out.println("Solution part 1: " + santa.deliver());
    }

    public static void part2() {
        DeliveryMan santaAndRobot = new DeliveryMan(2);
        System.out.println("Solution part 2: " + santaAndRobot.deliver());
    }

    public static void main(String[] args) {
        part1();
        part2();
    }
}
