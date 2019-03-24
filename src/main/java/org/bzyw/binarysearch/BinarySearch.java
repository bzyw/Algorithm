package org.bzyw.binarysearch;

public class BinarySearch {
    private static final int NOT_FOUND = -1;

    public static <T extends Comparable<? super T>> int binarySerch(T[] arr, T value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle].compareTo(value) > 0)
                right = middle - 1;
            else if (arr[middle].compareTo(value) < 0)
                left = middle + 1;
            else
                return middle;
        }
        return NOT_FOUND;
    }
}
