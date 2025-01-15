/*
 * Programmers Level 2 더 맵게
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 * */

import java.util.PriorityQueue;

public class P42626 {
    class Solution {
        public int solution(int[] scoville, int K) {
            // 우선순위 큐(PriorityQueue)를 사용하여 음식의 스코빌 지수를 정렬된 상태로 관리
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // 배열의 모든 스코빌 지수를 우선순위 큐에 추가
            for (int i = 0; i < scoville.length; i++) {
                pq.add(scoville[i]);
            }

            int answer = 0; // 섞는 횟수를 저장할 변수

            // 큐의 크기가 2 이상일 때 반복 (최소 두 개의 음식을 섞을 수 있어야 함)
            while (pq.size() >= 2) {
                // 가장 스코빌 지수가 낮은 두 음식을 꺼냄
                int a = pq.poll(); // 첫 번째로 낮은 스코빌 지수
                int b = pq.poll(); // 두 번째로 낮은 스코빌 지수

                // 만약 두 음식 중 하나라도 K보다 작은 경우 섞기
                if (a < K || b < K) {
                    // 새로운 음식의 스코빌 지수를 계산
                    int mix = a + 2 * b;

                    // 큐가 비었는데도 새로 만든 음식이 K보다 작으면 -1 반환
                    if (pq.isEmpty() && mix < K) return -1;

                    answer++; // 섞은 횟수 증가
                    pq.add(mix); // 새로 만든 음식을 큐에 추가
                } else {
                    // 두 음식 모두 K 이상이면 반복 종료
                    break;
                }
            }

            // 반복이 끝났을 때, 모든 음식의 스코빌 지수가 K 이상이 되지 않았다면 -1 반환
            if (pq.peek() < K) return -1;

            return answer; // 최소 섞은 횟수 반환
        }
    }
}
