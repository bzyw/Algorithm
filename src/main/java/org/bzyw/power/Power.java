package org.bzyw.power;

public class Power {
    public static int power(int x, int n) {
        if (n == 0)
            return 1;
        else if (idEven(n))
            return power(x * x, n / 2);
        else
            return power(x * x, (n - 1) / 2) * x;
    }

    private static boolean idEven(int n) {
        return n % 2 == 0 ? true : false;
    }
}
