package com.playposse.interviewprep.publicfacebookquestions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is a demo question that a Facebook interview video answers.
 * <p>
 * <p>See https://www.facebook.com/Engineering/videos/10152735777427200/
 */
public class NearbyWords {

    @Test
    public void test() {
        System.out.println(findNearbyWords("gi"));
    }

    // Note: This method could be further sped up by considering the prefix of a word. If we look
    // at the dictionary, we may find that 'magnates' is followed by 'magnesia'. So, we know that
    // any string between those words is not a word. Therefore, we could skip over all strings
    // that have a prefix like 'magnb*', magnc*", and so on. We might use a trie structure to have
    // fast lookup of prefixes.
    //
    // We could further optimize the approach based on the knowledge that the English language has
    // about one million words. (That's if we only consider English words and don't have an
    // internationalized product.) Thus any word that's so short that all possible prefixes are
    // taken could skip the overhead of the prefix optimization. For long words, e.g. 20 characters,
    // we'll save a lot of unnecessary word checks by skipping whole ranges of permutations.
    private static Set<String> findNearbyWords(String input) {
        // Check for early exit.
        if ((input == null) || (input.length() == 0)) {
            return null; // Or return new HashSet<>() depending on what method contract we want.
        }

        // Prepare a list of possible character alternatives for each character in the string.
        List<List<Character>> possibleCharacters = new ArrayList<>(input.length());
        for (int i = 0; i < input.length(); i++) {
            possibleCharacters.add(new ArrayList<>(getNearbyChars(input.charAt(i))));
        }

        int[] charIndexes = new int[input.length()];
        Set<String> words = new HashSet<>();
        outer:
        while (true) {
            StringBuilder sb = new StringBuilder(input.length());
            // Could be improved by using a char array to avoid creating objects. It would require
            // changing the signature of the isWord method.
            for (int i = 0; i < input.length(); i++) {
                sb.append(possibleCharacters.get(i).get(charIndexes[i]));
            }

            String str = sb.toString();
            if (isWord(str)) {
                words.add(str);
            }

            // Increment the character index.
            // Note: This loop could directly modify the character array, so that only the character
            // that changes is changed. This saves creating a full new string for each possible
            // word.
            //
            // Still the limit is that the isWord method will likely created a hash code by
            // iterating over every character. We could be smart by having this loop create a
            // hash code that only updates the particular letters that are changed.
            for (int i = 0; i < input.length(); i++) {
                if (charIndexes[i] < possibleCharacters.get(i).size() - 1) {
                    charIndexes[i]++;
                    continue outer;
                } else {
                    charIndexes[i] = 0;
                }
            }

            // We've reached all permutations.
            break;
        }

        return words;
    }

    // Note: This returns the character itself, not only simply the nearby characters.
    private static Set<Character> getNearbyChars(char c) {
        HashSet<Character> characters = new HashSet<>();
        if (c == 'g') {
            characters.add('g');
            characters.add('h');
            characters.add('f');
        } else {
            characters.add('i');
            characters.add('o');
            characters.add('k');
        }
        return characters;
    }

    private static boolean isWord(String word) {
        return word.equals("go") || word.equals("hi");
    }
}
