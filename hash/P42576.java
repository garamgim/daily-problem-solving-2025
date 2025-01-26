/*
 * Programmers Level 1 완주하지 못한 선수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 * */

import java.util.HashMap;

public class P42576 {
    class Solution {
        public String solution(String[] participant, String[] completion) {
            // 참가자 이름과 해당 이름의 등장 횟수를 저장하기 위한 HashMap 생성
            HashMap<String, Integer> cntMap = new HashMap<>();
            for (String name : participant) {
                cntMap.put(name, cntMap.getOrDefault(name, 0) + 1);
            }

            // 완주자 배열을 순회하며 HashMap에서 이름의 등장 횟수를 차감
            for (String name : completion) {
                int cnt = cntMap.get(name);
                if (cnt > 1) {
                    cntMap.put(name, cnt - 1);
                } else {
                    cntMap.remove(name);
                }
            }

            // HashMap에 남아 있는 이름을 탐색
            String answer = "";
            for (String key : cntMap.keySet()) {
                // 등장 횟수가 0보다 큰 이름(완주하지 못한 사람)을 결과로 설정
                if (cntMap.get(key) > 0) answer = key;
            }

            return answer;
        }
    }
}
