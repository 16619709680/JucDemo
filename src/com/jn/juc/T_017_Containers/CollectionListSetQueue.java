package juc.T_017_Containers;

import java.util.*;

public class CollectionListSetQueue {


    public static void main(String[] args) {
        Collection<Integer> objects = new ArrayList();

        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);

        objects.stream().forEach(System.out::println);

        List<Integer> c1 = new ArrayList();
        Set<Integer> c2 = new HashSet();
        Queue<Integer> c3 = new LinkedList();

    }
}
