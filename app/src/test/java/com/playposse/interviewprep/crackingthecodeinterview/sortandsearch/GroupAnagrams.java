package com.playposse.interviewprep.crackingthecodeinterview.sortandsearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by thoma on 8/4/2018.
 */
public class GroupAnagrams {

    @Test
    public void test() {
        assertThat(sort(new ArrayList<String>()), is((List<String>) new ArrayList<String>()));
        assertThat(sort(Arrays.asList("abc")), is(Arrays.asList("abc")));
        assertThat(sort(Arrays.asList("abc", "cba", "aaa")), is(Arrays.asList("aaa", "abc", "cba")));
        assertThat(sort(Arrays.asList("abc", "cba", "aaa", "ddd")), is(Arrays.asList("aaa", "abc", "cba", "ddd")));
        assertThat(sort(Arrays.asList("abc", "cba", "aaa", "ddd", "bac")), is(Arrays.asList("aaa", "abc", "cba", "bac", "ddd")));
    }

    private static List<String> sort(List<String> words) {
        Map<String, List<String>> map = new HashMap<>();

        // Hash words by sorted anagram word.
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if (map.containsKey(key)) {
                map.get(key).add(word);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(word);
                map.put(key, list);
            }
        }

        // return back to a list.
        List<String> result = new ArrayList<>(); // Could have also returned in place.
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }
}
