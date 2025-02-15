/*
 * 백준 G4 13913 숨바꼭질 4
 * https://www.acmicpc.net/problem/13913
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B13913 {
    static int N, K;
    static int[] visited = new int[100001]; // 방문한 위치와 이전 위치를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        N = Integer.parseInt(infos[0]);
        K = Integer.parseInt(infos[1]);

        int ans = bfs();
        System.out.println(ans);
        System.out.println(writePath(ans));
    }

    public static int bfs() {
        Arrays.fill(visited, -1); // 방문 배열을 -1로 초기화 (방문하지 않았음을 의미)

        Deque<Integer> q = new ArrayDeque<>();
        q.add(N);
        visited[N] = -2; // 시작 위치는 특별한 값(-2)로 설정 (이전 위치가 없음을 의미)
        int time = 0;

        while (!q.isEmpty()) {
            int sze = q.size();

            for (int i = 0; i < sze; i++) {
                int curr = q.poll();

                if (curr == K) {
                    return time;
                }

                for (int next : new int[]{curr - 1, curr + 1, curr * 2}) {
                    if (next >= 0 && next <= 100000 && visited[next] == -1) {
                        visited[next] = curr; // 이전 위치 저장 (경로 추적을 위해)
                        q.add(next);
                    }
                }
            }

            time++;
        }

        return time;
    }

    public static String writePath(int time) {
        List<Integer> pathList = new ArrayList<>(time);

        // K에서부터 역추적하여 경로를 리스트에 저장
        for (int i = K; i != -2; i = visited[i]) {
            pathList.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N).append(" "); // 시작 위치 추가

        // 역추적한 경로를 뒤에서부터 출력하여 순서 맞추기
        for (int i = time - 1; i >= 0; i--) {
            sb.append(pathList.get(i)).append(" ");
        }

        return sb.toString();
    }
}
