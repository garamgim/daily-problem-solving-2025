/*
 * LeetCode Medium 328 Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list/description/
 * */

public class L328 {
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
        public ListNode oddEvenList(ListNode head) {
            // 예외 처리: 리스트가 비어있는 경우(null) 바로 반환
            if (head == null) return null;

            // oddCurr: 홀수 노드의 현재 위치를 가리키는 포인터
            ListNode oddCurr = head;
            ListNode evenHead = head.next;
            // evenCurr: 짝수 노드의 현재 위치를 가리키는 포인터
            ListNode evenCurr = evenHead;

            while (oddCurr != null && oddCurr.next != null) {
                ListNode even = oddCurr.next;

                // oddCurr의 다음 노드를 짝수 노드의 다음 노드로 업데이트 (홀수 노드끼리 연결)
                oddCurr.next = even.next;
                // 짝수 노드(even)를 짝수 리스트의 끝에 연결
                evenCurr.next = even;

                // 짝수 리스트의 현재 위치를 다음 노드로 이동
                evenCurr = evenCurr.next;
                // oddCurr의 다음 노드가 존재하면 oddCurr를 다음 노드로 이동
                if (oddCurr.next != null) oddCurr = oddCurr.next;
            }

            // 짝수 리스트의 마지막 노드의 다음 노드를 null로 설정 (마지막 처리)
            if (evenCurr != null) evenCurr.next = null;

            // 홀수 리스트의 끝을 짝수 리스트의 시작으로 연결
            oddCurr.next = evenHead;
            return head;
        }
    }
}
