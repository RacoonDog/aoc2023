package io.github.racoondog.aoc.day2;

import io.github.racoondog.aoc.util.AocPuzzle;

import java.util.List;

public class Day2 extends AocPuzzle {
    public Day2() {
        super(2);
    }

    public static void main(String[] args) {
        new Day2().run();
    }

    @Override
    public String solve(List<String> input) {
        int sum = 0;

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);

            boolean isNumber = false;
            int numberStartIdx = 0;
            int value = 0;

            int red = 0;
            int green = 0;
            int blue = 0;

            for (int j = line.indexOf(':', 6) + 1; j < line.length(); j++) {
                int codepoint = line.codePointAt(j);

                if (codepoint >= '0' && codepoint <= '9') {
                    if (!isNumber) {
                        isNumber = true;
                        numberStartIdx = j;
                    }
                } else {
                    if (isNumber) {
                        isNumber = false;
                        value = Integer.parseInt(line.substring(numberStartIdx, j));
                    } else {
                        switch (codepoint) {
                            case 'r' -> {
                                red = Math.max(value, red);
                                j += 4;
                            }
                            case 'g' -> {
                                green = Math.max(value, green);
                                j += 6;
                            }
                            case 'b' -> {
                                blue = Math.max(value, blue);
                                j += 5;
                            }
                        }
                    }
                }
            }

            sum += red * green * blue;
        }

        return Integer.toString(sum);
    }
}
