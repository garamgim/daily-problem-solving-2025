/*
 * LeetCode Medium 92 Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 * */

public class L92 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            // 현재 노드를 순회하기 위한 포인터 변수
            ListNode curr = head;
            // 뒤집힌 리스트를 연결하기 위한 임시 노드 (더미 노드)
            ListNode rev = new ListNode(0);

            int idx = 1;
            ListNode leftEnd = null;
            ListNode revEnd = null;

            while (curr != null && idx <= right) {
                if (idx == left - 1) {
                    // left 이전 노드를 저장
                    leftEnd = curr;
                } else if (idx >= left) {
                    // 뒤집힌 리스트에 노드를 추가
                    ListNode revNext = rev.next;
                    ListNode newNode = new ListNode(curr.val);
                    if (revNext == null) revEnd = newNode;
                    newNode.next = revNext;
                    rev.next = newNode;
                }
                curr = curr.next;
                idx++;
            }

            // left 이전 노드가 존재할 경우, 뒤집힌 구간의 시작점과 연결
            if (leftEnd != null) {
                leftEnd.next = rev.next;
            } else {
                // left가 1일 경우, head를 뒤집힌 리스트로 변경
                head = rev.next;
            }

            // 뒤집힌 구간의 끝 노드를 뒤집힌 구간 이후 노드와 연결
            if (revEnd != null) revEnd.next = curr;
            return head;
        }
    }
}
