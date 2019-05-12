package org.bzyw.maxsub;

public class MaxSubSequence {
    /**
     * O(N3)
     *
     * @param seq
     * @return
     */
    public static long maxSub1(long[] seq) {
        long maxSum = 0;
        for (int i = 0; i < seq.length; i++) {
            for (int j = i; j < seq.length; j++) {
                long thisSum = 0;
                for (int k = i; k <= j; k++)
                    thisSum += seq[k];

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }

    /**
     * O(N2)
     *
     * @param seq
     * @return
     */
    public static long maxSub2(long[] seq) {
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i < seq.length; i++) {
            long thisSum = 0;
            for (int j = i; j < seq.length; j++) {
                thisSum += seq[j];
                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }

    /**
     * N*log2(N)
     *
     * @param seq
     * @return
     */
    public static long maxSub3(long[] seq) {
        return maxSubRec(seq, 0, seq.length - 1);
    }

    private static long maxSubRec(long[] seq, int left, int right) {
        if (left == right)
            return seq[left];
        int middle = (left + right) / 2;
        long leftMaxSub = maxSubRec(seq, left, middle);
        long rightMaxSub = maxSubRec(seq, middle + 1, right);

        long maxLeftBorder = Long.MIN_VALUE;
        long temp = 0;
        for (int i = middle; i >= left; i--) {
            temp += seq[i];
            if (temp > maxLeftBorder)
                maxLeftBorder = temp;
        }

        long maxRightBorder = Long.MIN_VALUE;
        long temp2 = 0;
        for (int i = middle + 1; i <= right; i++) {
            temp2 += seq[i];
            if (temp2 > maxRightBorder)
                maxRightBorder = temp2;
        }

        return max3(leftMaxSub, rightMaxSub, maxLeftBorder + maxRightBorder);
    }

    private static long max3(long num1, long num2, long num3) {
        long temp1 = Math.max(num1, num2);
        return Math.max(temp1, num3);
    }

    public static long maxSub4(long[] seq) {
        long maxSum = 0;
        long thisSum = 0;
        long maxNegative = Long.MIN_VALUE;
        for (long num : seq) {
            thisSum += num;
            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
            if (num > maxNegative)
                maxNegative = num;
        }

        if (maxNegative < 0)
            return maxNegative;
        else
            return maxSum;
    }

}
