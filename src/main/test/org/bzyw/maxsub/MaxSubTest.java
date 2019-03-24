package org.bzyw.maxsub;

import org.bzyw.util.Util;
import org.junit.Test;

import javax.naming.InsufficientResourcesException;
import java.util.Arrays;
import java.util.Random;

public class MaxSubTest {

    @Test
    public void test() {
        //int[] numbers = {-2, 11, -4, 13, -5, -2};
        //int[] numbers = {4, -3, 5, -2, -1, 2, 6, -2};
        //int[] numbers = {1,2,3,4,5,6,7,8,9};
        //int[] numbers = {-1, -2, -3, -4, -5, -6, -7, -8, 0};
        System.out.println(Integer.MAX_VALUE);
        long[] numbers = createArray(10000);
        System.out.println(Arrays.toString(numbers));
        double millon = 1000000.0;

        long start = System.nanoTime();
        long max4 = MaxSubSequence.maxSub4(numbers);
        long end = System.nanoTime();
        System.out.println("maxSub4:" + max4 + ",cost:" + (end - start) / millon + " ms");

        start = System.nanoTime();
        long max3 = MaxSubSequence.maxSub3(numbers);
        end = System.nanoTime();
        System.out.println("maxSub3:" + max3 + ",cost:" + (end - start) / millon + " ms");

        start = System.nanoTime();
        long max2 = MaxSubSequence.maxSub2(numbers);
        end = System.nanoTime();
        System.out.println("maxSub2:" + max2 + ",cost:" + (end - start) / millon + " ms");

        start = System.nanoTime();
        long max1 = MaxSubSequence.maxSub1(numbers);
        end = System.nanoTime();
        System.out.println("maxSub1:" + max1 + ",cost:" + (end - start) / millon + " ms");
    }

    private long[] createArray(int length) {
        return Util.createArray(length, Integer.MAX_VALUE / 4);
    }
}
