import java.io.*;

public class B9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int N = s1.length();
        int M = s2.length();
        int[][] memo = new int[N + 1][M + 1];

        // LCS 계산 (Bottom-up 방식)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                // 현재 비교하는 두 문자가 같다면, LCS 길이 증가
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                }
                // 문자가 다르면, 이전 단계의 최댓값 유지
                else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }

            }
        }

        System.out.println(memo[N][M]);
    }
}
