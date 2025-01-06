/*
 * LeetCode Easy 234 Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/description/
 * */

import java.util.ArrayDeque;

public class L234 {
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public boolean isPalindrome(ListNode head) {
            ArrayDeque<Integer> q = new ArrayDeque<>();

            while (head != null) {
                q.addLast(head.val);
                head = head.next;
            }

            while (q.size() > 1) {
                if (q.pollFirst() != q.pollLast()) {
                    return false;
                }
            }

            return true;
        }
    }

    class SolutionWithRunner {
        public boolean isPalindrome(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            if (head.next == null) return true;

            // Runner 기법을 사용하여 리스트의 중간 지점을 찾음
            // fast는 두 칸씩 이동, slow는 한 칸씩 이동
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            // fast가 null이 아니면 리스트의 길이가 홀수
            // 이 경우 slow를 한 칸 더 이동하여 중간 값을 건너뜀
            if (fast != null) {
                slow = slow.next;
            }

            // 중간부터 시작해서 리스트의 뒷부분을 뒤집기
            // rev는 뒤집힌 리스트를 나타냄
            ListNode rev = new ListNode(slow.val);

            while (true) {
                slow = slow.next;
                if (slow == null) break;
                ListNode prev = new ListNode(slow.val, rev);
                rev = prev;
            }

            // 원래 리스트의 앞부분과 뒤집힌 뒷부분을 비교
            while (rev != null) {
                if (head.val != rev.val) return false;
                head = head.next;
                rev = rev.next;
            }

            return true;
        }
    }


}
