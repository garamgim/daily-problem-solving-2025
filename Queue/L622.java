/*
 * LeetCode Medium 622 Design Circular Queue
 * https://leetcode.com/problems/design-circular-queue/description/
 * */

public class L622 {
    class MyCircularQueue {
        public int[] q;
        public int size;
        public int front = 0;
        public int rear = 0;

        public MyCircularQueue(int k) {
            q = new int[k];
            size = k;
            for (int i = 0; i < k; i++) {
                q[i] = -1;
            }
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            q[rear] = value;
            rear = (rear + 1) % size;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            q[front] = -1;
            front = (front + 1) % size;
            return true;
        }

        public int Front() {
            return q[front];
        }

        public int Rear() {
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
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */
}
