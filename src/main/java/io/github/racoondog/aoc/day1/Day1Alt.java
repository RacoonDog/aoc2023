package io.github.racoondog.aoc.day1;

import io.github.racoondog.aoc.util.AocPuzzle;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.List;

public class Day1Alt extends AocPuzzle {
    private static final VarHandle stringValueArrVarHandle;

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            MethodHandles.lookup();
            Field lookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            long lookupFieldOffset = unsafe.staticFieldOffset(lookupField);
            MethodHandles.Lookup trusted = (MethodHandles.Lookup) unsafe.getObject(MethodHandles.Lookup.class, lookupFieldOffset);
            stringValueArrVarHandle = trusted.findVarHandle(String.class, "value", byte[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Day1Alt() {
        super(1);
    }

    public static void main(String[] args) {
        new Day1Alt().run();
    }

    @Override
    public String solve(List<String> input) {
        int sum = 0;

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            byte[] array = (byte[]) stringValueArrVarHandle.get(line);
            int len = array.length;
            boolean first = true;
            int last = 0;
            for (int j = 0; j < len; j++) {
                byte codepoint = array[j];

                switch (codepoint) {
                    case '1' -> {
                        if (first) {
                            sum += 10;
                            first = false;
                        }
                        last = 1;
                    }
                    case '2' -> {
                        if (first) {
                            sum += 20;
                            first = false;
                        }
                        last = 2;
                    }
                    case '3' -> {
                        if (first) {
                            sum += 30;
                            first = false;
                        }
                        last = 3;
                    }
                    case '4' -> {
                        if (first) {
                            sum += 40;
                            first = false;
                        }
                        last = 4;
                    }
                    case '5' -> {
                        if (first) {
                            sum += 50;
                            first = false;
                        }
                        last = 5;
                    }
                    case '6' -> {
                        if (first) {
                            sum += 60;
                            first = false;
                        }
                        last = 6;
                    }
                    case '7' -> {
                        if (first) {
                            sum += 70;
                            first = false;
                        }
                        last = 7;
                    }
                    case '8' -> {
                        if (first) {
                            sum += 80;
                            first = false;
                        }
                        last = 8;
                    }
                    case '9' -> {
                        if (first) {
                            sum += 90;
                            first = false;
                        }
                        last = 9;
                    }

                    case 'o' -> {
                        if (len - j >= 2 && array[j + 1] == 'n' && array[j + 2] == 'e') {
                            if (first) {
                                sum += 10;
                                first = false;
                            }
                            last = 1;
                            j+= 1;
                        }
                    }

                    case 't' -> {
                        if (len - j >= 2 && array[j + 1] == 'w' && array[j + 2] == 'o') {
                            if (first) {
                                sum += 20;
                                first = false;
                            }
                            last = 2;
                            j+= 1;
                        } else if (len - j >= 4 && array[j + 1] == 'h' && array[j + 2] == 'r' && array[j + 3] == 'e' && array[j + 4] == 'e') {
                            if (first) {
                                sum += 30;
                                first = false;
                            }
                            last = 3;
                            j+= 3;
                        }
                    }

                    case 'f' -> {
                        if (len - j >= 3) {
                            if (array[j + 1] == 'o' && array[j + 2] == 'u' && array[j + 3] == 'r') {
                                if (first) {
                                    sum += 40;
                                    first = false;
                                }
                                last = 4;
                                j+= 3;
                            } else if (array[j + 1] == 'i' && array[j + 2] == 'v' && array[j + 3] == 'e') {
                                if (first) {
                                    sum += 50;
                                    first = false;
                                }
                                last = 5;
                                j+= 2;
                            }
                        }
                    }

                    case 's' -> {
                        if (len - j >= 2 && array[j + 1] == 'i' && array[j + 2] == 'x') {
                            if (first) {
                                sum += 60;
                                first = false;
                            }
                            last = 6;
                            j+= 2;
                        } else if (len - j >= 4 && array[j + 1] == 'e' && array[j + 2] == 'v' && array[j + 3] == 'e' && array[j + 4] == 'n') {
                            if (first) {
                                sum += 70;
                                first = false;
                            }
                            last = 7;
                            j+= 3;
                        }
                    }

                    case 'e' -> {
                        if (len - j >= 4 && array[j + 1] == 'i' && array[j + 2] == 'g' && array[j + 3] == 'h' && array[j + 4] == 't') {
                            if (first) {
                                sum += 80;
                                first = false;
                            }
                            last = 8;
                            j+= 3;
                        }
                    }

                    case 'n' -> {
                        if (len - j >= 3 && array[j + 1] == 'i' && array[j + 2] == 'n' && array[j + 3] == 'e') {
                            if (first) {
                                sum += 90;
                                first = false;
                            }
                            last = 9;
                            j+= 2;
                        }
                    }
                }
            }
            sum += last;
        }

        return Integer.toString(sum);
    }
}
