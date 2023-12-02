package io.github.racoondog.aoc.day1;

import io.github.racoondog.aoc.util.AocPuzzle;

import java.util.List;

public class Day1 extends AocPuzzle {
    public Day1() {
        super(1);
    }

    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public String solve(List<String> input) {
        int sum = 0;

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            int len = line.length();
            boolean first = true;
            int value = 0;
            for (int j = 0; j < len; j++) {
                int codepoint = line.codePointAt(j);

                switch (codepoint) {
                    case '1' -> value = 1;
                    case '2' -> value = 2;
                    case '3' -> value = 3;
                    case '4' -> value = 4;
                    case '5' -> value = 5;
                    case '6' -> value = 6;
                    case '7' -> value = 7;
                    case '8' -> value = 8;
                    case '9' -> value = 9;

                    case 'o' -> {
                        if (len - j >= 2 && line.codePointAt(j + 1) == 'n' && line.codePointAt(j + 2) == 'e') {
                            value = 1;
                            j+= 1;
                        }
                    }

                    case 't' -> {
                        if (len - j >= 2 && line.codePointAt(j + 1) == 'w' && line.codePointAt(j + 2) == 'o') {
                            value = 2;
                            j+= 1;
                        } else if (len - j >= 4 && line.codePointAt(j + 1) == 'h' && line.codePointAt(j + 2) == 'r' && line.codePointAt(j + 3) == 'e' && line.codePointAt(j + 4) == 'e') {
                            value = 3;
                            j+= 3;
                        }
                    }

                    case 'f' -> {
                        if (len - j >= 3) {
                            if (line.codePointAt(j + 1) == 'o' && line.codePointAt(j + 2) == 'u' && line.codePointAt(j + 3) == 'r') {
                                value = 4;
                                j+= 3;
                            } else if (line.codePointAt(j + 1) == 'i' && line.codePointAt(j + 2) == 'v' && line.codePointAt(j + 3) == 'e') {
                                value = 5;
                                j+= 2;
                            }
                        }
                    }

                    case 's' -> {
                        if (len - j >= 2 && line.codePointAt(j + 1) == 'i' && line.codePointAt(j + 2) == 'x') {
                            value = 6;
                            j+= 2;
                        } else if (len - j >= 4 && line.codePointAt(j + 1) == 'e' && line.codePointAt(j + 2) == 'v' && line.codePointAt(j + 3) == 'e' && line.codePointAt(j + 4) == 'n') {
                            value = 7;
                            j+= 3;
                        }
                    }

                    case 'e' -> {
                        if (len - j >= 4 && line.codePointAt(j + 1) == 'i' && line.codePointAt(j + 2) == 'g' && line.codePointAt(j + 3) == 'h' && line.codePointAt(j + 4) == 't') {
                            value = 8;
                            j+= 3;
                        }
                    }

                    case 'n' -> {
                        if (len - j >= 3 && line.codePointAt(j + 1) == 'i' && line.codePointAt(j + 2) == 'n' && line.codePointAt(j + 3) == 'e') {
                            value = 9;
                            j+= 2;
                        }
                    }
                }

                if (first && value != 0) {
                    first = false;
                    sum += value * 10;
                }
            }
            sum += value;
        }

        return Integer.toString(sum);
    }
}
