/*
 * LeetCode Medium 24 Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 * */

/*
 * When to use Dummy Node
 * 1. Performing multiple insertions or deletions, requiring frequent management of prev and next pointers.
 * 2. Edge cases like an empty list or a single-node list.
 * 3. When managing sections of a list where the starting node could either be the head or an intermediate node.
 * */

public class L24 {
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
        public ListNode swapPairs(ListNode head) {
            // 연결 리스트의 가상 시작 노드 생성
            ListNode root = new ListNode(0);
            root.next = head;
            ListNode curr = root;

            while (curr.next != null && curr.next.next != null) {
                // 교환 대상 노드들 지정
                ListNode second = curr.next;
                ListNode third = curr.next.next;

                // 노드 교환: 첫번째 노드의 다음을 세번째 노드로 변경
                curr.next = third;
                // 두번째 노드의 다음을 세번째 노드의 다음으로 변경
                second.next = third.next;
                // 세번째 노드의 다음을 두번째 노드로 변경
                third.next = second;
                curr = second;
            }

            return root.next;
        }
    }
}
