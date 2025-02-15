/*
 * LeetCode Easy 206 Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 * */

public class L206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode reverse(ListNode prev, ListNode curr) {
            if (curr == null) return prev;

            ListNode next = curr.next;
            curr.next = prev;
            return reverse(curr, next);
        }

        public ListNode reverseList(ListNode head) {
            return reverse(null, head);
        }
    }
}
