package org.bzyw.test;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        int size = 800000;
        int boundary = 100;
        List<Integer> list = getIntegerArrayList(size, boundary);
        //System.out.println(list);
        long start = System.currentTimeMillis();
        removeEventNumber(list);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);
        //System.out.println(list);
    }

    private static List<Integer> getIntegerArrayList(int size, int boundary) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(boundary));
        }
        return list;
    }


    private static List<Integer> getIntegerLinkedList(int size, int boundary) {
        List<Integer> list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(boundary));
        }
        return list;
    }

    private static void removeEventNumber(List<Integer> list) {
        /*int index = 0;
        while (index < list.size()) {
            if (list.get(index) % 2 == 0) {
                list.remove(index);
            } else {
                index++;
            }
        }*/
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int number = iterator.next();
            if (number % 2 == 0) {
                iterator.remove();
            }
        }
    }
}
