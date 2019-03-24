package org.bzyw.gcd;

public class GCD {

    /**
     * 求最大公约数（m > n）
     *
     * @param m
     * @param n
     * @return
     */
    public static int gcd(int m, int n) {
        while (n != 0) {
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
