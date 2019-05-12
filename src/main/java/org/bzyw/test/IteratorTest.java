package org.bzyw.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
            //String str = iterator.next();
            //if ("b".equals(str))
            //    iterator.remove();
        }
        System.out.println(Arrays.toString(list.toArray()));
    }
}
