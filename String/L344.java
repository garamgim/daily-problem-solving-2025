/*
 * LeetCode Easy 344 Reverse String
 * https://leetcode.com/problems/reverse-string/description/
 * */

public class L344 {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            if (s[l] != s[r]) {
                char temp = s[l];
                s[l] = s[r];
                s[r] = temp;
            }
            l++;
            r--;
        }
    }
}
