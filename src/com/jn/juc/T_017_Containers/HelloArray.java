package juc.T_017_Containers;

import java.util.Arrays;

public class HelloArray {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};


        Arrays.stream(a).map(o -> o + 1).forEach((o) -> System.out.println(o + "    "));

    }
}
