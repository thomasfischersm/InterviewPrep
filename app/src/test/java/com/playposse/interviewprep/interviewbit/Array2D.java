package com.playposse.interviewprep.interviewbit;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by thoma on 7/23/2018.
 */
public class Array2D {

    @Test
    public void test() {
        doSomething();
    }

    private void doSomething() {
        // [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]] 
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<Integer>());
        A.get(0).add(1);
        A.get(0).add(2);
        A.get(0).add(3);
        A.get(0).add(4);
        A.add(new ArrayList<Integer>());
        A.get(1).add(5);
        A.get(1).add(6);
        A.get(1).add(7);
        A.get(1).add(8);
        A.add(new ArrayList<Integer>());
        A.get(2).add(9);
        A.get(2).add(10);
        A.get(2).add(11);
        A.get(2).add(12);

        ArrayList<ArrayList<Integer>> B = performOps(A);
        for (int i = 0; i < B.size(); i++) {
            for (int j = 0; j < B.get(i).size(); j++) {
                System.out.print(B.get(i).get(j) + " ");
            }
        }
    }

    ArrayList<ArrayList<Integer>> performOps(ArrayList<ArrayList<Integer>> A) {
        ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < A.size(); i++) {
            B.add(new ArrayList<Integer>());

            for (int j = 0; j < A.get(i).size(); j++) {
                B.get(i).add(0);
            }

            for (int j = 0; j < A.get(i).size(); j++) {
                B.get(i).set(A.get(i).size() - 1 - j, A.get(i).get(j));
            }
        }
        return B;
    }

}
