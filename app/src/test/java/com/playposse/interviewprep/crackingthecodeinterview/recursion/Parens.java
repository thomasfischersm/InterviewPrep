package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by thoma on 7/31/2018.
 */
public class Parens {

    @Test
    public void test() {
        test(0);
        test(1);
        test(2);
        test(3);
        test(4);
        test(5);
    }

    private static void test(int parensCount) {
        System.out.println(" => " + parensCount);
        System.out.println(permutate(parensCount));
        System.out.println();
    }

    private static Set<String> permutate(int parensCount) {
        Set<String> result = new HashSet<>();
        permutate(parensCount, 0, new StringBuilder(), result);
        return result;
    }

    private static void permutate(
            int openCount,
            int closeCount,
            StringBuilder sb,
            Set<String> result) {

        if ((openCount == 0) && (closeCount == 0)) {
            result.add(sb.toString());
        }

        if (openCount > 0) {
            sb.append("(");
            permutate(openCount - 1, closeCount + 1 , sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (closeCount > 0) {
            sb.append(")");
            permutate(openCount, closeCount - 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
