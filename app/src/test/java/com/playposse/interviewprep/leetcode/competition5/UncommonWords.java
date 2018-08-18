package com.playposse.interviewprep.leetcode.competition5;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by thoma on 8/11/2018.
 */
public class UncommonWords {

    @Test
    public void test() {
        assertThat(new Solution().uncommonFromSentences("this apple is sweet", "this apple is sour"), is(new String[]{"sweet","sour"}));
        assertThat(new Solution().uncommonFromSentences("apple apple", "banana"), is(new String[]{"banana"}));
    }

    class Solution {
        public String[] uncommonFromSentences(String A, String B) {
            String[] wordsA = A.split(" ");
            String[] wordsB = B.split(" ");

            Set<String> words = new HashSet<>();
            Set<String> uniques = new HashSet<>();

            for (String word : wordsA) {
                if (words.contains(word)) {
                    uniques.remove(word);
                } else {
                    words.add(word);
                    uniques.add(word);
                }
            }

            for (String word : wordsB) {
                if (words.contains(word)) {
                    uniques.remove(word);
                } else {
                    words.add(word);
                    uniques.add(word);
                }
            }

            return uniques.toArray(new String[uniques.size()]);
        }
    }
}
