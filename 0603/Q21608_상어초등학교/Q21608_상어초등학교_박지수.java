import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BOJ_21608 {
	static int N;
	static int[][] classroom;
	static int[][] check;
	static boolean[] setCheck;
	static int[][] preference;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		classroom = new int[N][N]; // 순서대로 자리 배정 //0~N-1
		check = new int[N * N + 1][2]; // 해당 학생의 위치는 어딘지 //1~N*N
		preference = new int[N * N + 1][4]; // 학생의 4명의 선호하는 학생 //1~N*N
		setCheck = new boolean[N * N + 1]; // 앉아있는 지 여부 //1~N*N
		String[] input;
		for (int i = 1; i <= N * N; i++) {
			input = br.readLine().split(" ");
			int student = Integer.parseInt(input[0]);
			for (int j = 0; j < 4; j++) {
				preference[student][j] = Integer.parseInt(input[j + 1]);
			}
			if (i == 1) {
				classroom[1][1] = student; // 첫번째 학생은 무조건 1,1자리
				check[student][0] = 1;
				check[student][1] = 1;
				setCheck[student] = true;
			} else {
				setPlace(student);
			}


		}

		System.out.println(getScore());
	}

	public static void setPlace(int no) { // 학생 no의 자리 정하기

		int[][] cntPossible = new int[N][N]; // 좋아하는 학생의 인접한 칸을 체크
		boolean noPreference = true; // 좋아하는 학생이 앉아있지 않다면


		for (int i = 0; i < 4; i++) { // 선호하는 학생들
			if (setCheck[preference[no][i]]) { // 선호하는 학생들이 자리를 잡은 상태라면
				for (int d = 0; d < 4; d++) {
					int nr = check[preference[no][i]][0] + dr[d]; // 선호하는 학생의 자리
					int nc = check[preference[no][i]][1] + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && classroom[nr][nc] == 0) {
						cntPossible[nr][nc]++;
					}
				}
				noPreference = false;
			}
		}
		int max = -1, maxI = 0, maxJ = 0, adjMax = -1;
		for (int i = 0; i < N; i++) { // 조건 1,2,3을 만족하며 현재 학생이 앉을 자리 구하기			//행의 번호가 가장 작은 칸부터, 열의 번호가 가장 작은 칸부터 자리를 배정하기 때문에 3번 만족
			for (int j = 0; j < N; j++) {
				if (classroom[i][j] != 0) { // 앉으려는 자리가 비어있지 않으면 continue
					continue;
				}
				if (!noPreference) { // 선호하는 학생이 놓여져 있다면
					if (max < cntPossible[i][j]) { // 1번 : 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정함
						int adjcnt = cntAdj(i, j);
						adjMax = adjcnt;
						maxI = i;
						maxJ = j;
						max = cntPossible[i][j];
					} else if (max == cntPossible[i][j]) {		//2번 : 1번을 만족하는 칸이 여러개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정함
						int adjcnt = cntAdj(i, j);
						if (adjMax < adjcnt) {
							maxI = i;
							maxJ = j;
							adjMax = adjcnt;
						}
					}
				} else { // 선호하는 학생이 없다면
					int adjcnt = cntAdj(i, j);
					if (adjMax < adjcnt) {
						maxI = i;
						maxJ = j;
						adjMax = adjcnt;
					}
				}
			}
		}
		classroom[maxI][maxJ] = no;
		check[no][0] = maxI;
		check[no][1] = maxJ;
		setCheck[no] = true;

	}

	public static int getScore() {	
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					if(isIncluded(classroom[i][j], classroom[nr][nc])) {				//손호학생에 포함되어 있는 지 확인
						cnt++;
					}

				}
				if (cnt == 1)
					sum += 1;
				else if (cnt == 2)
					sum += 10;
				else if (cnt == 3)
					sum += 100;
				else if (cnt == 4)
					sum += 1000;
			}
		}
		return sum;
	}
	public static int cntAdj(int i, int j) {			//인접한 칸 중에 비어있는 칸이 몇칸인지 반환
		int adjcnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && classroom[nr][nc] == 0) {
				adjcnt++;
			}
		}
		
		return adjcnt;
	}
	public static boolean isIncluded(int student, int preferStudent) {
		boolean isIncluded = false;
		for (int i = 0; i < 4; i++) {
			if(preference[student][i] == preferStudent) {
				isIncluded = true;
				break;
			}
		}
		return isIncluded;
	}
}
