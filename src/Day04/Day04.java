package Day04;

import utils.CommonUtils;

class Range {
    public int lower_bound;
    public int upper_bound;
    public Range(String str) {
        try {
            String[] nums = str.split("-");
            lower_bound = Integer.parseInt(nums[0]);
            upper_bound = Integer.parseInt(nums[1]);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "(" + lower_bound + "-" + upper_bound + ")";
    }

    public Boolean isFullyOverlapping(Range b) {
        if(lower_bound >= b.lower_bound && upper_bound <= b.upper_bound) {
            return true;
        }
        if(b.lower_bound >= lower_bound && b.upper_bound <= upper_bound) {
            return true;
        }
        return false;
    }

    public Boolean hasAnyOverlap(Range b) {
        if(isFullyOverlapping(b)) return true;
        if(lower_bound >= b.lower_bound && lower_bound <= b.upper_bound) return true;
        if(b.lower_bound >= lower_bound && b.lower_bound <= upper_bound) return true;
        return false;
    }
}
public class Day04 {

    public String solvePartOne(String input) {
        String[] pairs = input.split("\n");
        Integer res = 0;
        for(String pair: pairs) {
            String[] ranges = pair.split(",");
            Range A = new Range(ranges[0]);
            Range B = new Range(ranges[1]);
            if(A.isFullyOverlapping(B)) {
                res++;
            }
        }
        return res.toString();
    }

    public String solvePartTwo(String input) {
        String[] pairs = input.split("\n");
        Integer res = 0;
        for(String pair: pairs) {
            String[] ranges = pair.split(",");
            Range A = new Range(ranges[0]);
            Range B = new Range(ranges[1]);
            if(A.hasAnyOverlap(B)) {
                res++;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Day04 dayFour = new Day04();
        CommonUtils utils = new CommonUtils();

        String input = utils.readFile(4, "input.txt");

        String outputA = dayFour.solvePartOne(input);
        System.out.println("Part One: " + outputA);

        String outputB = dayFour.solvePartTwo(input);
        System.out.println("Part Two: " + outputB);
    }
}
