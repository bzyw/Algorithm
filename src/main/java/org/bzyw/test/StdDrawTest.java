package org.bzyw.test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

public class StdDrawTest {
    public static void main(String[] args) {
        int n = 100;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n * n);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(50.0,3000.0);
        StdDraw.line(10.0,200.0,50.0,2000.0);
        for (int i = 0; i < n; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }

    }
}
