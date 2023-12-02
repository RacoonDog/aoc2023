package io.github.racoondog.aoc.util;

import java.util.List;

public abstract class AocPuzzle {
    public final int day;

    public AocPuzzle(int day) {
        this.day = day;
    }

    public void run() {
        List<String> input = AocInput.inputOf(this.day);
        long startTime = System.nanoTime();
        String result = solve(input);
        long runtime = System.nanoTime() - startTime;
        System.out.printf("[Advent of Code] Day %d solution finished with result '%s' in %.2f μs.%n", this.day, result, runtime / 1000f);
    }

    public abstract String solve(List<String> input);
}
