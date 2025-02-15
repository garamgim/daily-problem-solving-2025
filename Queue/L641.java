/*
 * LeetCode Medium 622 Design Circular Deque
 * https://leetcode.com/problems/design-circular-deque/description/
 * */

public class L641 {
    class MyCircularDeque {
        public int[] q;
        public int size;
        public int front = 0;
        public int rear = 0;

        public MyCircularDeque(int k) {
            q = new int[k];
            size = k;
            for (int i = 0; i < k; i++) {
                q[i] = -1;
            }
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;
            front = (front + size - 1) % size;
            q[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;
            q[rear] = value;
            rear = (rear + 1) % size;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            q[front] = -1;
            front = (front + 1) % size;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            rear = (rear + size - 1) % size;
            q[rear] = -1;
            return true;
        }

        public int getFront() {
            return q[front];
        }

        public int getRear() {
            return q[(rear + size - 1) % size];
        }

        public boolean isEmpty() {
            return front == rear && q[front] == -1;
        }

        public boolean isFull() {
            return front == rear && q[front] != -1;
        }
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */
}
