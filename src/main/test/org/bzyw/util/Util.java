package org.bzyw.util;

import java.util.Random;

public class Util {
    private Util() {
    }

    public static long[] createArray(int length, int radius) {
        if (length < 0)
            throw new IllegalArgumentException();
        Random random = new Random();
        long[] array = new long[length];
        int bound = radius * 2;
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(bound) - radius;
        }
        return array;
    }

    public static Long[] createArray1(int length, int radius) {
        if (length < 0)
            throw new IllegalArgumentException();
        Random random = new Random();
        Long[] array = new Long[length];
        int bound = radius * 2;
        for (int i = 0; i < length; i++) {
            array[i] = (long) (random.nextInt(bound) - radius);
        }
        return array;
    }
}
