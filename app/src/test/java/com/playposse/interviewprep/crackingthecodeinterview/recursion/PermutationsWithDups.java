package com.playposse.interviewprep.crackingthecodeinterview.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by thoma on 7/31/2018.
 */
public class PermutationsWithDups {

    @Test
    public void test() {
        test("a");
        test("ab");
        test("abc");
        test("aaaa");
        test("abaab");
        test("abcccab");
        test("abcd");
    }

    private static void test(String str) {
        System.out.println("=> " + str);
        System.out.println(computePermutations(str));
        System.out.println();
    }

    private static Set<String> computePermutations(String str) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }

        return permutate(new StringBuilder(), charMap);
    }

    private static Set<String> permutate(
            StringBuilder prefix,
            Map<Character, Integer> charMap) {

        if (charMap.size() == 0) {
            Set<String> result = new HashSet<>();
            result.add(prefix.toString());
            return result;
        }

        List<Character> charList = new ArrayList<>(charMap.keySet());

        Set<String> result = new HashSet<>();
        for (int i = 0; i < charList.size(); i++) {
            char c = charList.get(i);

            int charCount = charMap.get(c);
            if (charCount > 1) {
                charMap.put(c, charCount - 1);
            } else {
                charMap.remove(c);
            }

            prefix.append(c);

            result.addAll(permutate(prefix, charMap));

            prefix.deleteCharAt(prefix.length() - 1);

            charMap.put(c, charCount);
        }
        return result;
    }
}
