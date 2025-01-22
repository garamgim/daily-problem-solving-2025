/*
 * LeetCode Easy 771 Jewels and Stones
 * https://leetcode.com/problems/jewels-and-stones/description/
 * */

import java.util.HashSet;

public class L771 {
    class Solution {
        public int numJewelsInStones(String jewels, String stones) {
            HashSet<Character> charSet = new HashSet<>();
            for (int i = 0; i < jewels.length(); i++) {
                charSet.add(jewels.charAt(i));
            }
            int ans = 0;
            for (int i = 0; i < stones.length(); i++) {
                if (charSet.contains(stones.charAt(i))) ans++;
            }
            return ans;
        }
    }
}
