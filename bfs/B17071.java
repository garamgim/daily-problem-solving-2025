import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B17071 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] infos = br.readLine().split(" ");
        N = Integer.parseInt(infos[0]);
        K = Integer.parseInt(infos[1]);

        int ans = bfs();
        System.out.println(ans);
    }

    /*
    * 예제 17 5
    * 17 -> 16 -> 15 -> 16 -> 15
    * 1. 어떤 좌표 X에 동생이 t초에 도착한다고 가정하면
    * 수빈이는 그 좌표 X에 t-2, t-4, …에서 앞뒤로 제자리걸음 하면 동생을 만날 수 있음
    * 2. 중복 방문이 가능해야하나 최적화를 위해 모든 경우를 탐색하지 않아야 함
    * 3. 따라서 방문 체크 배열을 만들되 짝수와 홀수의 경우를 나누어 탐색 경우의 수를 가지치기 함
    * */

    public static int bfs() {
        // 방문 여부 저장 (짝수와 홀수 초 구분)
        //
        boolean[][] visited = new boolean[2][500001];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(N);
        visited[0][N] = true;
        int flag, time = 0;

        while (!q.isEmpty()) {
            if (K > 500000) return -1;

            // 짝수와 홀수 초 구분
            flag = time % 2;
            int sze = q.size();

            for (int i = 0; i < sze; i++) {
                int curr = q.poll();

                if (curr == K) {
                    return time;
                }

                for (int next : new int[]{curr - 1, curr + 1, curr * 2}) {
                    // 다음 위치가 범위를 벗어나지 않고, 아직 방문하지 않은 경우만 이동
                    if (next >= 0 && next <= 500000 && !visited[(flag + 1) % 2][next]) {
                        visited[(flag + 1) % 2][next] = true;
                        q.add(next);
                    }
                }
            }

            // 동생이 해당 시간(time)에 방문한 위치에 있다면 종료
            if (visited[flag][K]) return time;

            time++;
            K += time;
        }

        return -1;
    }
}
