package aoc.y2015;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    static List<String> data;
    static {
        try {
            data = Util.ReadFile("resources/input7.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static boolean isNumber(String strNumber) {
        try {
            Integer.parseInt(strNumber);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int findSignal(Map<String, String> circuit, String key) {
        if (isNumber(key)) {
            return (Integer.parseInt(key));
        } else if (circuit.get(key).contains("NOT ")) {
            String newKey = circuit.get(key).split(" ")[1];
            int newSignal = findSignal(circuit, newKey);
            circuit.put(key, String.valueOf(~newSignal));
            return (~newSignal);
        } else if (circuit.get(key).contains(" AND ")) {
            String newKeyA = circuit.get(key).split(" ")[0];
            String newKeyB = circuit.get(key).split(" ")[2];
            int newSignalA = findSignal(circuit, newKeyA);
            int newSignalB = findSignal(circuit, newKeyB);
            circuit.put(key, String.valueOf(newSignalA&newSignalB));
            return (newSignalA&newSignalB);
        } else if (circuit.get(key).contains(" OR ")) {
            String newKeyA = circuit.get(key).split(" ")[0];
            String newKeyB = circuit.get(key).split(" ")[2];
            int newSignalA = findSignal(circuit, newKeyA);
            int newSignalB = findSignal(circuit, newKeyB);
            circuit.put(key, String.valueOf(newSignalA|newSignalB));
            return (newSignalA|newSignalB);
        } else if (circuit.get(key).contains(" RSHIFT ")) {
            String newKey = circuit.get(key).split(" ")[0];
            int spaces = Integer.parseInt(circuit.get(key).split(" ")[2]);
            int newSignal = findSignal(circuit, newKey);
            circuit.put(key, String.valueOf(newSignal>>spaces));
            return (newSignal>>spaces);
        } else if (circuit.get(key).contains(" LSHIFT ")) {
            String newKey = circuit.get(key).split(" ")[0];
            int spaces = Integer.parseInt(circuit.get(key).split(" ")[2]);
            int newSignal = findSignal(circuit, newKey);
            circuit.put(key, String.valueOf(newSignal<<spaces));
            return  (newSignal<<spaces);
        } else {
            String newKey = circuit.get(key);
            int newSignal = findSignal(circuit, newKey);
            circuit.put(key, String.valueOf(newSignal));
            return newSignal;
        }
    }

    public static Map<String, String> resetWires(List<String> data) {
        Map<String, String> circuit = new HashMap<>();
        for (String instruction : data) {
            String circuitKey = instruction.split(" -> ")[1];
            String circuitValue = instruction.split(" -> ")[0];
            circuit.put(circuitKey, circuitValue);
        }
        return circuit;
    }

    public static Map<String, String> part1() {
        Map<String, String> circuit = resetWires(data);
        System.out.println("Solution part 1: " + findSignal(circuit, "a"));
        return circuit;
    }

    public static void part2(Map<String, String> circuit) {
        String aSignal = circuit.get("a");
        circuit = resetWires(data);
        circuit.put("b", aSignal);
        System.out.println("Solution part 2: " + findSignal(circuit, "a"));
    }

    public static void main(String[] args) {
        Map<String, String> circuit = part1();
        part2(circuit);
    }
}
