package com.ssafy.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 
// 벽 부수고 이동하기
public class Q2206 {
	
	static final int[] dr = {-1, 0, 1, 0}; // 상우하좌
	static final int[] dc = {0, 1, 0, -1};

	static class Position{
		int r,c,dist;
		boolean flag;
		public Position(int r, int c, int dist, boolean flag) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.flag = flag;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		char[][] board = new char[N][];
		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		} // board 완성
		
		// 아직 벽을 부순적이 없는 경우라면 checkForOne은 확인하지 않고 진행
		boolean[][] checkForZero = new boolean[N][M];
		// 벽을 부순 이후부터는 이 checkForOne과 checkForZero 모두를 확인
		boolean[][] checkForOne = new boolean[N][M];
		
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0,0,1,false));
		checkForZero[0][0] = true; // position을 큐에 넣을 때 체크를 미리 해버리겠다. 원래라면 뺄 때 체크하는 것이 맞지만 
		
		int ans = -1;
		while(!queue.isEmpty()) {
			Position p = queue.poll();
			int r = p.r;
			int c = p.c;
			if(r==N-1&&c==M-1) {
				ans = p.dist;
				break;
			}
			for(int i=0,len=dr.length; i<len; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 범위 체크
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				if(p.flag) { // 벽 부수기 기술을 이미 사용했을 경우
					if(board[nr][nc]=='1' || checkForZero[nr][nc] || checkForOne[nr][nc]) continue;
					// 이미 벽 부수기를 했으므로 더 이상 벽을 부술 수는 없다. 그리고 이미 check판에 true라고 체크가 되어있다면 더 짧은 방법이 있었다는 뜻이므로 넣지 않는다.
					checkForOne[nr][nc] = true;
					queue.add(new Position(nr,nc,p.dist+1,true));
				}else { // 벽 부수기 기술을 아직 사용하지 않은 경우
					if(board[nr][nc]=='1') { // 벽이 있는 경우로 부수는 기술을 사용
						if(checkForZero[nr][nc]) continue;
						checkForZero[nr][nc] = true;
						checkForOne[nr][nc] = true;
						queue.add(new Position(nr,nc,p.dist+1,true));
					}else { // 벽이 없는 경우
						if(checkForZero[nr][nc]) continue;
						checkForZero[nr][nc] = true;
						queue.add(new Position(nr,nc,p.dist+1,false));
					}
				}
			} // 상우하좌 방향으로 탐색 끝
			
		} // end of queue
		System.out.println(ans);
	}
}
