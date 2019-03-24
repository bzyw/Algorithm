package org.bzyw.binarysearch;

import org.bzyw.util.Util;
import org.junit.Test;

import java.util.Arrays;

public class BinarySearchTest {
    @Test
    public void test() {
        Long[] arr = Util.createArray1(100000000, Integer.MAX_VALUE / 2);

        Arrays.sort(arr);

        Long value = 10000L;
        long start = System.nanoTime();
        int index = BinarySearch.binarySerch(arr, value);
        long end = System.nanoTime();
        System.out.println("index:" + index + ",cost:" + (end - start) / 1000000.0 + " ms");
    }
}
