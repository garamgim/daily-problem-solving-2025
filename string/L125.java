/*
 * LeetCode Easy 125 Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/description/
 * */

public class L125 {
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            // 영숫자인지 판별하고 유효하지 않으면 한 칸씩 이동
            while (!Character.isLetterOrDigit(s.charAt(l)) && l < r) {
                l++;
            }
            while (!Character.isLetterOrDigit(s.charAt(r)) && l < r) {
                r--;
            }
            // 유효하다면 소문자로 변경 후 비교
            char left = Character.toLowerCase(s.charAt(l));
            char right = Character.toLowerCase(s.charAt(r));
            if (left != right) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    public boolean isPalindromeSimple(String s) {
        // StringBuilder().reverse().toString() 후 같은지 확인하기
        // 코드 간결하나 시간 더걸림
        return true;
    }
}
