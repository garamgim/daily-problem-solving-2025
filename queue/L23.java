/*
 * LeetCode Medium 23 Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L23 {
    class Solution {
        public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        // 5ms
        public ListNode mergeKListsIntPQ(ListNode[] lists) {
            PriorityQueue<Integer> nums = new PriorityQueue<>();
            for (ListNode head : lists) {
                while (head != null) {
                    nums.add(head.val);
                    head = head.next;
                }
            }
            ListNode newHead = new ListNode(0);
            ListNode curr = newHead;
            while (!nums.isEmpty()) {
                curr.next = new ListNode(nums.poll());
                curr = curr.next;
            }
            return newHead.next;
        }

        /*
        * 6ms
        * - ListNode 객체의 참조와 비교 작업이 이루어져 int값만 비교할때보다 시간이 더 걸릴 수 있음
        * - 추가적인 객체 생성은 적지만 기존 연결 리스트 조작 과정에서 참조 관계 정리하는 GC 작업이 더 많아질 수 있음
        *   (GC가 참조 그래프를 분석하는 과정에서 더 많은 작업을 수행할 수 있음)
        * */
        public ListNode mergeKListsNodePQ(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
            for (ListNode head : lists) {
                while (head != null) {
                    pq.add(head);
                    head = head.next;
                }
            }
            ListNode newHead = new ListNode(0);
            ListNode curr = newHead;
            while (!pq.isEmpty()) {
                curr.next = pq.poll();
                curr = curr.next;
            }
            curr.next = null;
            return newHead.next;
        }
    }
}
