/*
 * LeetCode Medium 49 Group Anagrams
 * https://leetcode.com/problems/group-anagrams/description/
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class L49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> anagrams = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[(int) s.charAt(i) - 97]++;
            }

            if (!anagrams.containsKey(Arrays.toString(count))) {
                anagrams.put(Arrays.toString(count), new ArrayList<>());
            }

            anagrams.get(Arrays.toString(count)).add(s);
        }

        return new ArrayList<>(anagrams.values());
    }
}
