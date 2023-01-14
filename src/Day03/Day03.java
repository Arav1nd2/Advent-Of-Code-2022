package Day03;

import utils.CommonUtils;

import java.util.HashSet;

public class Day03 {

    public String solvePartOne(String input) {
        String[] sacks = input.split("\n");
        Integer res = 0;
        for(String sack: sacks) {
            int len = sack.length();
            if(len % 2 != 0) {
                throw new Error("String with invalid length");
            }
            String A = sack.substring(0, len / 2);
            String B = sack.substring(len / 2);
            if(A.length() != B.length()) {
                throw new Error("String split wrong!");
            }
            HashSet<Character> charMapA = new HashSet<>();
            HashSet<Character> charMapB = new HashSet<>();

            for(char ch: A.toCharArray()) {
                charMapA.add(ch);
            }
            for(char ch: B.toCharArray()) {
                charMapB.add(ch);
            }
            HashSet<Character> common = new HashSet<>(charMapA);
            common.retainAll(charMapB);

            if(common.size() > 1) {
                throw new Error("Invalid Input");
            }
            if(common.size() < 1) {
                continue;
            }
            Character[] commonChars = new Character[1];
            common.toArray(commonChars);
            Character ans = commonChars[0];

            res += getPriority(ans);
        }
        return res.toString();
    }

    private int getPriority(Character ans)  {
        if(ans >= 'A' && ans <= 'Z') {
            return (int) ans - (int)'A' + 27;
        }
        return (int) ans - (int) 'a' + 1;
    }

    public String solvePartTwo(String input) {
        String[] sacks = input.split("\n");
        int len = sacks.length;
        Integer res = 0;
        for(int i = 0; i < len; i+= 3) {
            String A = sacks[i];
            String B = sacks[i+1];
            String C = sacks[i+2];
            HashSet<Character> charMapA = new HashSet<>();
            HashSet<Character> charMapB = new HashSet<>();
            HashSet<Character> charMapC = new HashSet<>();

            // Fill the maps
            for(char ch: A.toCharArray()) {
                charMapA.add(ch);
            }
            for(char ch: B.toCharArray()) {
                charMapB.add(ch);
            }
            for(char ch: C.toCharArray()) {
                charMapC.add(ch);
            }
            HashSet<Character> common = new HashSet<>(charMapA);
            common.retainAll(charMapB);
            common.retainAll(charMapC);

            if(common.size() == 0) {
                throw new Error("No badge found");
            }
            if(common.size() > 1) {
                throw new Error("More common badges");
            }

            Character[] commonChars = new Character[1];
            common.toArray(commonChars);
            Character ans = commonChars[0];

            res += getPriority(ans);

        }
        return res.toString();
    }

    public static void main(String[] args) {
        Day03 dayThree = new Day03();
        CommonUtils utils = new CommonUtils();

        String input = utils.readFile(3, "input.txt");

        String outputA = dayThree.solvePartOne(input);
        System.out.println("Part One: " + outputA);

        String outputB = dayThree.solvePartTwo(input);
        System.out.println("Part Two: " + outputB);
    }
}
