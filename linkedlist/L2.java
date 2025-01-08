/*
 * LeetCode Medium 2 Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/description/
 * */

public class L2 {
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 결과를 저장할 새로운 연결 리스트의 더미 헤드 노드 생성
            ListNode head = new ListNode(0);
            ListNode curr = head;

            int sum;
            int carry = 0;
            int remainder;

            while (l1 != null || l2 != null || carry != 0) {
                sum = 0;

                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }

                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }

                // 현재 합(sum)과 올림 값(carry)을 더한 결과를 10으로 나눈 나머지
                remainder = (sum + carry) % 10;

                // 합과 올림 값을 더한 결과를 10으로 나눈 몫을 올림 값으로 설정
                carry = (sum + carry) / 10;

                // 새 노드를 생성해 연결 리스트에 추가
                curr.next = new ListNode(remainder);
                curr = curr.next;
            }
            return head.next;
        }
    }
}
