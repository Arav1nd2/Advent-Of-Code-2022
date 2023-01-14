package Day06;

import utils.CommonUtils;
import java.util.*;
import java.util.stream.Collectors;


public class Day06 {

    private Boolean isUnique(String str) {
        HashSet<Character> charSet = new HashSet<>();
        for(Character ch: str.toCharArray()) {
            charSet.add(ch);
        }
        return charSet.size() == str.length();
    }
    public String solvePartOne(String input) {
        int len = input.length();
        StringBuilder str = new StringBuilder();
        for(int end = 0; end < len; end++) {
            str.append(input.charAt(end));
            if(str.length() > 4) {
                str.delete(0, 1);
                if(isUnique(str.toString())) {
                    return "" + (end+1);
                }
            }
        }
        return "0";
    }

    public String solvePartTwo(String input) {
        int len = input.length();
        StringBuilder str = new StringBuilder();
        for(int end = 0; end < len; end++) {
            str.append(input.charAt(end));
            if(str.length() > 14) {
                str.delete(0, 1);
                if(isUnique(str.toString())) {
                    return "" + (end+1);
                }
            }
        }
        return "0";
    }

    public static void main(String[] args) {
        Day06 solver =  new Day06();

        CommonUtils utils = new CommonUtils();

        String input = utils.readFile(6, "input.txt");

        String outputA = solver.solvePartOne(input);
        System.out.println("Part One: " + outputA);

        String outputB = solver.solvePartTwo(input);
        System.out.println("Part Two: " + outputB);
    }
}
