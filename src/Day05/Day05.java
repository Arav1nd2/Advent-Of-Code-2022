package Day05;

import utils.CommonUtils;
import java.util.*;
import java.util.stream.Collectors;


public class Day05 {

    private ArrayList<Stack<String>> buildStack(String[] lines) {
        int lineLen = lines.length;
        int numberOfStacks = lines[lineLen - 1].split("   ").length;
        ArrayList<Stack<String>> stacks = new ArrayList<>();
        for(int i = 0; i < numberOfStacks; i++) {
            stacks.add(new Stack<>());
        }
        for(int i = lineLen - 2; i >= 0; i--) {
            String line = lines[i];
            int l = line.length();
            for(int k = 0; k < l; k+= 4) {
                String element = line.substring(k, l > k+4 ? k+4 : l).substring(1,2);
                if(element.trim() != "") {
                    stacks.get(k / 4).add(element.trim());
                }
            }
        }
        return stacks;
    }
    private void executeCommand(String command, ArrayList<Stack<String>> stacks) {
        String[] commandList = command.split(" ");
        int count = Integer.parseInt(commandList[1]);
        int from = Integer.parseInt(commandList[3]);
        int to = Integer.parseInt(commandList[5]);
        while(count > 0) {
            try {
                String stackTop = stacks.get(from - 1).pop();
                stacks.get(to - 1).add(stackTop);
            } catch (EmptyStackException e) {}
            count--;
        }
    }
    private void advExecuteCommand(String command, ArrayList<Stack<String>> stacks) {
        String[] commandList = command.split(" ");
        int count = Integer.parseInt(commandList[1]);
        int from = Integer.parseInt(commandList[3]);
        int to = Integer.parseInt(commandList[5]);
        ArrayList<String> temp = new ArrayList<>();
        while(count > 0) {
            try {
                String stackTop = stacks.get(from - 1).pop();
                temp.add(stackTop);
            } catch (EmptyStackException e) {}
            count--;
        }
        Collections.reverse(temp);
        for(String str: temp) {
            stacks.get(to - 1).add(str);
        }
    }
    public String solvePartOne(String input) {
        String[] lines = input.split("\n");
        int stackInputEnd = 0;
        for(int i = 0; i < lines.length; i++) {
            if(lines[i].length() == 0) {
                stackInputEnd = i;
                break;
            }
        }
        String[] stackInput = Arrays.stream(lines).limit(stackInputEnd).collect(Collectors.toList()).toArray(new String[stackInputEnd]);
        ArrayList<Stack<String>> stacks = buildStack(stackInput);
        for(int i = stackInputEnd + 1; i < lines.length; i++) {
            executeCommand(lines[i], stacks);
        }
        return stacks.stream().map(st -> st.peek()).collect(Collectors.joining());
    }

    public String solvePartTwo(String input) {
        String[] lines = input.split("\n");
        int stackInputEnd = 0;
        for(int i = 0; i < lines.length; i++) {
            if(lines[i].length() == 0) {
                stackInputEnd = i;
                break;
            }
        }
        String[] stackInput = Arrays.stream(lines).limit(stackInputEnd).collect(Collectors.toList()).toArray(new String[stackInputEnd]);
        ArrayList<Stack<String>> stacks = buildStack(stackInput);
        for(int i = stackInputEnd + 1; i < lines.length; i++) {
            advExecuteCommand(lines[i], stacks);
        }
        return stacks.stream().map(st -> st.peek()).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Day05 solver =  new Day05();

        CommonUtils utils = new CommonUtils();

        String input = utils.readFile(5, "input.txt");

        String outputA = solver.solvePartOne(input);
        System.out.println("Part One: " + outputA);

        String outputB = solver.solvePartTwo(input);
        System.out.println("Part Two: " + outputB);
    }
}
