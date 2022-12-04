package Day02;

import java.io.*;
/**
 *  A, X - Rock
 *  B, Y - Paper
 *  C, Z - Scissor
 *
 */
public class DayTwo {
    private static final String ROCK = "Rock";
    private static final String PAPER = "Paper";
    private static final String SCISSOR = "Scissor";
    private static  final String LOSE = "LOSE";
    private static final String WIN = "WIN";
    private static final String TIE = "TIE";

    private String mapCodeToMove(String code) {
        switch (code) {
            case "A":
            case "X": return ROCK;
            case "B":
            case "Y": return PAPER;
            case "C":
            case "Z": return SCISSOR;
            default: return "";
        }
    }

    private String mapCodeToOutcome(String code) {
        switch (code) {
            case "X": return LOSE;
            case "Y": return TIE;
            case "Z": return WIN;
            default: return "";
        }
    }

    private int moveToScore(String move) {
        switch (move) {
            case ROCK: return 1;
            case PAPER: return 2;
            case SCISSOR: return 3;
            default: return 0;
        }
    }
    public String readFile(String fileName) {
        try {
            String newFileName = "./src/Day02/" + fileName;
            File inputFile = new File(newFileName);
            FileReader fr = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fr.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePartOne(String input) {
        String[] matches = input.split("\n");
        Integer score = 0;
        for(String match: matches) {
            score += evaluateMatch(match);
        }
        return score.toString();
    }

    public String solvePartTwo(String input) {
        String[] matches = input.split("\n");
        Integer score = 0;
        for(String match: matches) {
            score += findMatchScore(match);
        }
        return score.toString();
    }

    private int findMatchScore(String match) {
        String[] codes = match.split(" ");
        String opponentMove = mapCodeToMove(codes[0]);
        String gameOutcome = mapCodeToOutcome(codes[1]);
        int score = 0;
        if(gameOutcome == WIN) {
            score = 6;
        } else if(gameOutcome == TIE) {
            score = 3;
        }
        String myMove = findMove(opponentMove, gameOutcome);
        score += moveToScore(myMove);
        return score;
    }

    private String findMove(String opponentMove, String gameOutcome) {
        if(gameOutcome == TIE) {
            return opponentMove;
        } else if(gameOutcome == WIN) {
            if(opponentMove == ROCK) return PAPER;
            if(opponentMove == SCISSOR) return ROCK;
            if(opponentMove == PAPER) return SCISSOR;
        } else {
            if(opponentMove == ROCK) return SCISSOR;
            if(opponentMove == SCISSOR) return PAPER;
            if(opponentMove == PAPER) return ROCK;
        }
        return "";
    }
    private int evaluateMatch(String match) {
        String[] codes = match.split(" ");
        String opponentMove = mapCodeToMove(codes[0]);
        String myMove = mapCodeToMove(codes[1]);
        int score = moveToScore(myMove);
        int gameScore = 0;
        if(myMove == opponentMove) {
            gameScore = 3;
        } else {
            if(myMove == ROCK && opponentMove == SCISSOR) {
                gameScore = 6;
            } else if(myMove == SCISSOR && opponentMove == PAPER) {
                gameScore = 6;
            } else if(myMove == PAPER && opponentMove == ROCK) {
                gameScore = 6;
            } else {
                gameScore = 0;
            }
        }
        return gameScore + score;
    }
    public static void main(String[] args) {
        DayTwo p1 = new DayTwo();
        String input = p1.readFile("input.txt");
        String outputA = p1.solvePartOne(input);
        String outputB = p1.solvePartTwo(input);
        System.out.println("Part A: " + outputA);
        System.out.println("Part B: " + outputB);
    }
}
