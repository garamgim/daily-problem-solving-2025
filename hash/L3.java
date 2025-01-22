import java.util.HashSet;
import java.util.HashMap;

class L3 {
    class Solution {

        // 기존 코드: HashSet을 사용한 중복 처리
        public int lengthOfLongestSubstring(String s) {
            HashSet<Character> charSet = new HashSet<>();
            int maxCnt = 0;
            int l = 0; // 슬라이딩 윈도우의 왼쪽 포인터
            int r = 0; // 슬라이딩 윈도우의 오른쪽 포인터

            // 예외 처리
            if (s.length() == 0) return 0;
            if (s.length() == 1) return 1;

            while (r < s.length()) {
                char curr = s.charAt(r); // 현재 문자

                // 중복된 문자가 발견되면, 왼쪽 포인터를 이동하며 중복 제거
                while (charSet.contains(curr)) {
                    charSet.remove(s.charAt(l)); // 왼쪽 문자 제거
                    l++;
                }

                // 현재 문자를 추가하고 최대 길이를 갱신
                charSet.add(curr);
                maxCnt = Math.max(maxCnt, r - l + 1);

                // 오른쪽 포인터 이동
                r++;

                /*
                * "abcdbef" 예시를 기준으로
                * r=0: charSet={a}, l=0, maxCnt=1
                * r=1: charSet={a, b}, l=0, maxCnt=2
                * r=2: charSet={a, b, c}, l=0, maxCnt=3
                * r=3: charSet={a, b, c, d}, l=0, maxCnt=4
                * r=4: 중복된 'b' 발견 → l=2까지 l을 하나씩 이동하며 중복 문자를 제거 (속도저하 요인), charSet={c, d, b}, maxCnt=4
                * r=5: charSet={c, d, b, e}, maxCnt=4
                * r=6: charSet={d, b, e, f}, maxCnt=4
                * */
            }

            return maxCnt;
        }

        // 최적화된 코드: HashMap을 사용한 중복 처리
        public int lengthOfLongestSubstringOptimized(String s) {
            HashMap<Character, Integer> charIndexMap = new HashMap<>();
            int maxLen = 0;
            int start = 0; // 슬라이딩 윈도우의 시작 포인터

            for (int end = 0; end < s.length(); end++) {
                char currChar = s.charAt(end);

                // 중복된 문자가 발견되면, start 포인터를 갱신
                if (charIndexMap.containsKey(currChar)) {
                    start = Math.max(start, charIndexMap.get(currChar) + 1);
                }

                // 현재 문자의 인덱스를 업데이트
                charIndexMap.put(currChar, end);

                // 최대 길이 계산
                maxLen = Math.max(maxLen, end - start + 1);

                /*
                * "abcdbef" 예시를 기준으로
                * end=0: charIndexMap={a: 0}, start=0, maxLen=1
                * end=1: charIndexMap={a: 0, b: 1}, start=0, maxLen=2
                * end=2: charIndexMap={a: 0, b: 1, c: 2}, start=0, maxLen=3
                * end=3: charIndexMap={a: 0, b: 1, c: 2, d: 3}, start=0, maxLen=4
                * end=4: 중복된 'b' 발견 → start=2로 바로 갱신 (최적화 요인), charIndexMap={a: 0, b: 4, c: 2, d: 3}, maxLen=4
                * end=5: charIndexMap={a: 0, b: 4, c: 2, d: 3, e: 5}, start=2, maxLen=4
                * end=6: charIndexMap={a: 0, b: 4, c: 2, d: 3, e: 5, f: 6}, start=2, maxLen=5
                * */
            }

            return maxLen;
        }
    }
}
