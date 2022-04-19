package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_3190 {
	static int N, K, map[][], L;
	static int[] dr = { 0, 1, 0, -1 };			//우하좌상
	static int[] dc = { 1, 0, -1, 0 };
	static int dir = 0;	
	static int time;
	static int headR = 1, headC = 1;
	static Deque<location> dq = new LinkedList<>();
	static class location{
		int r, c;

		public location(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int [N+1][N+1];

		String [] input;
		for(int k = 0; k < K; k++) {
			input = br.readLine().split(" ");
			map[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = 1;		//map이 1이면 사과
		}	
		
		dq.offerFirst(new location(1,1));
		map[1][1] = 2;
		L = Integer.parseInt(br.readLine());
		boolean isEnd = false;
		for(int i = 0; i < L; i++) {
			input = br.readLine().split(" ");

			isEnd = move(headR,headC, dir, Integer.parseInt(input[0]));
			
			if(isEnd) break;
			if(input[1].charAt(0) == 'D')
				dir = (dir+1)% 4;
			else
				dir = (dir -1 + 4) % 4;
			location cur = dq.peekFirst();
			headR = cur.r;
			headC = cur.c;
		
		}
		if(!isEnd) move(headR, headC, dir, 10000);
		System.out.println(time);
	}
	public static boolean move(int r, int c, int dir, int t) {
		boolean isEnd = false;
		
		while(t > time) {
			time++;
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(nr < 1 || nc < 1 || nr > N || nc > N || map[nr][nc] == 2) {
				isEnd = true;
				break;
			}
				
			if(map[nr][nc] == 1) {				//가고자하는 곳에 사과가 있을 때
				dq.offerFirst(new location(nr,nc));
				map[nr][nc] = 2;
			}else {
				dq.offerFirst(new location(nr,nc));
				map[nr][nc] = 2;
				location tail = dq.peekLast();
				map[tail.r][tail.c] = 0; 
				dq.pollLast();
			}
			r = nr;
			c = nc;
			
		}
		return isEnd;
	}
		
}
