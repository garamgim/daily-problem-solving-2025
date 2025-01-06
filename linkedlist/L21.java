/*
 * LeetCode Easy 21 Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * */

public class L21 {
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode head = new ListNode(-101);
            ListNode curr = head;
            while (!(list1 == null && list2 == null)) {
                if ((list1 != null && list2 == null) || (list1 != null && list1.val < list2.val)) {
                    curr.next = list1;
                    curr = curr.next;
                    list1 = list1.next;
                } else if ((list2 != null && list1 == null) || (list2 != null && list2.val <= list1.val)) {
                    curr.next = list2;
                    curr = curr.next;
                    list2 = list2.next;
                }
            }
            return head.next;
        }
    }
}
