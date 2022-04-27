import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14503 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M, d, map[][];
	static boolean visited[][];


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		input = br.readLine().split(" ");
		int vacR = Integer.parseInt(input[0]);
		int vacC = Integer.parseInt(input[1]);
		d = Integer.parseInt(input[2]);
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		dfs(vacR, vacC, d);
		System.out.println(count);

	}

	static int count = 0;

	static void dfs(int r, int c, int d) {

		if (map[r][c] == 0 && !visited[r][c]) {			//빈 공간이고 청소하지 않은 공간이면
			count++;
		}
		visited[r][c] = true;

		boolean check = false;
		for (int i = 0; i < 4; i++) {
			int nd = (d + 3 * (i + 1)) % 4;				// 0 ->3 -> 2 -> 1
			int nr = r + dr[nd];
			int nc = c + dc[nd];

			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 1) {
				
				dfs(nr, nc, nd);
				check = true;
				return;				//왼쪽으로 갈 수 있었으면 해당 자리에서 탐색 그만
			}
		}
		if (!check) {			//4방향 다 가지 않았다면 후진
			int nr = r - dr[d];
			int nc = c - dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1)			//후진하려는 곳이 벽이면 작동 멈춤
				return;
			dfs(nr, nc, d);			//방향은 그대로, 후진하여 진행해보기
		}

	}

}
