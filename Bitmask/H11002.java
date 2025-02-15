/*
 * 소프티어 Level 3 11002 CPTI
 * https://softeer.ai/practice/11002
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H11002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int M =  Integer.parseInt(info[1]);

        // 각 사람의 CPTI를 저장할 배열
        int[] ppl = new int[N];

        // 각 사람의 CPTI를 이진수로 읽어서 배열에 저장
        for (int i = 0; i < N; i++) {
            // BinaryString을 읽고 이를 이진수로 변환하여 배열에 저장
            ppl[i] = Integer.parseInt(br.readLine(), 2);
        }

        // 친밀감을 느끼는 사람 쌍의 수를 셀 변수
        int cnt = 0;

        // 모든 사람 쌍을 비교
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // XOR 연산을 통해 두 사람의 CPTI가 얼마나 다른지 계산
                // Integer.bitCount()는 XOR 결과에서 1의 개수를 세서 서로 다른 비트 수를 구함
                if (Integer.bitCount(ppl[i] ^ ppl[j]) < 3) {
                    // 다른 비트 수가 2개 이하이면 친밀감을 느끼는 쌍으로 카운트
                    cnt++;
                }
            }
        }

        // 친밀감을 느끼는 사람 쌍의 수를 출력
        System.out.println(cnt);
    }

//    성능 최적화 관점에서 재귀호출 방식은 반복문을 사용하는 방식에 비해
//    함수 호출과 스택 관리로 인한 오버헤드가 발생할 수 있음을 유의해야 한다.
//    public static void comb(int[] selected, int lev, int start) {
//        if (lev == 2) {
//            int p = ppl[selected[0]];
//            int q = ppl[selected[1]];
//
//            if (Integer.bitCount(p ^ q) < 3) totalCnt++;
//            return;
//        }
//
//        for (int i = start; i < N; i++) {
//            selected[lev] = i;
//            comb(selected, lev + 1, i + 1);
//        }
//    }
}
