/*
 * LeetCode Medium 5 Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 * This is O(N^2) solution
 * For O(N) solution, check Manacher's Algorithm
 * */

public class L5 {
    int maxVal = -1;
    String ans;

    public void checkPalindrome(String s, int l, int r) {
        if (l < 0 || r >= s.length()) return;

        if (s.charAt(l) == s.charAt(r)) {
            if (r - l + 1 > maxVal) {
                maxVal = r - l + 1;
                ans = s.substring(l, r + 1);
            }
            checkPalindrome(s, l - 1, r + 1);
        }
    }

    public String longestPalindrome(String s) {
        // 짝수개 팰린드롬과 홀수개 팰린드롬을 체크
        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i + 1);
        }
        return ans;
    }
}
