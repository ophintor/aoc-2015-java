package aoc.y2015;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {
    static List<String> data;
    static {
        try {
            data = Util.ReadFile("resources/input6.txt");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static class Lights {
        int[][] matrix;

        public Lights() {
            matrix = new int[1000][1000];
        }

        public void operateLights(int[] section, String operation) {
            for (int i = section[0]; i <= section[2]; i++) {
                for (int j = section[1]; j <= section[3]; j++) {
                    switch (operation) {
                        case "turnOn":
                            matrix[i][j] = 1;
                            break;
                        case "turnOff":
                            matrix[i][j] = 0;
                            break;
                        case "toggle":
                            matrix[i][j] = Math.abs(matrix[i][j] - 1);
                            break;
                        case "turnOnBrightness":
                            matrix[i][j] += 1;
                            break;
                        case "turnOffBrightness":
                            if (matrix[i][j] > 0)
                                matrix[i][j] -= 1;
                            break;
                        case "toggleBrightness":
                            matrix[i][j] += 2;
                            break;
                        default:
                            System.err.println("Operation not found");
                            break;
                    }
                }
            }
        }

        public int getLitLights() {
            int total = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    total += matrix[i][j];
                }
            }
            return total;
        }
    }

    private static int[] getSection(String instruction) {
        String coordsRegex = "\\d+";
        int[] section = new int[4];

        Matcher m = Pattern.compile(coordsRegex).matcher(instruction);

        if (m.find())
            section[0] = Integer.parseInt(m.group());
        if (m.find())
            section[1] = Integer.parseInt(m.group());
        if (m.find())
            section[2] = Integer.parseInt(m.group());
        if (m.find())
            section[3] = Integer.parseInt(m.group());

        return section;
    }

    private static void operateLights(Lights lights, int[] section, String instruction, boolean brightness) {
        if (!brightness) {
            if (instruction.startsWith("turn on")) {
                lights.operateLights(section, "turnOn");
            } else if (instruction.startsWith("turn off")) {
                lights.operateLights(section, "turnOff");
            } else if (instruction.startsWith("toggle")) {
                lights.operateLights(section, "toggle");
            }
        }
        else {
            if (instruction.startsWith("turn on")) {
                lights.operateLights(section, "turnOnBrightness");
            } else if (instruction.startsWith("turn off")) {
                lights.operateLights(section, "turnOffBrightness");
            } else if (instruction.startsWith("toggle")) {
                lights.operateLights(section, "toggleBrightness");
            }
        }
    }

    public static void part1() {
        Lights lights = new Lights();
        for (String instruction : data) {
            int[] section = getSection(instruction);
            operateLights(lights, section, instruction, false);
        }
        System.out.println("Solution part 1: " + lights.getLitLights());
    }

    public static void part2() {
        Lights lights = new Lights();
        for (String instruction : data) {
            int[] section = getSection(instruction);
            operateLights(lights, section, instruction, true);
        }
        System.out.println("Solution part 2: " + lights.getLitLights());
    }

    public static void main(String[] args) {
        part1();
        part2();
    }
}
