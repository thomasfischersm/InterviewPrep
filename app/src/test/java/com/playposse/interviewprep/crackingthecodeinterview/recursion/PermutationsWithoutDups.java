package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by thoma on 7/31/2018.
 */
public class PermutationsWithoutDups {

    @Test
    public void test() {
        test("a");
        test("ab");
        test("abc");
        test("abcd");
    }

    private static void test(String str) {
        System.out.println("=> " + str);
        System.out.println(computePermutations(str));
        System.out.println();
    }

    private static Set<String> computePermutations(String str) {
        List<Character> characters = new LinkedList<>();
        for (char c : str.toCharArray()) {
            characters.add(c);
        }

        return permutate(new StringBuilder(), characters);
    }

    private static Set<String> permutate(
            StringBuilder prefix,
            List<Character> remainingCharacters) {

        if (remainingCharacters.size() == 0) {
            Set<String> result = new HashSet<>();
            result.add(prefix.toString());
            return result;
        }

        Set<String> result = new HashSet<>();
        for (int i = 0; i < remainingCharacters.size(); i++) {
            char c = remainingCharacters.remove(i);
            prefix.append(c);

            result.addAll(permutate(prefix, remainingCharacters));

            prefix.deleteCharAt(prefix.length() - 1);
            remainingCharacters.add(i, c);
        }
        return result;
    }
}
