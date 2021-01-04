package aoc.y2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> ReadFile(String filename) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
        return result;
    }

    public static boolean isNumber(String strNumber) {
        try {
            Integer.parseInt(strNumber);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
}
