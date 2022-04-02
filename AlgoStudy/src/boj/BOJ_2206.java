/*package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//https://www.acmicpc.net/problem/2206
public class BOJ_2206 {
	static class node{
		int x, y;

		public node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int [][] dir = {{-1, 0}, {1, 0} ,{0, -1}, {0, 1}};			//상하좌우
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input1 = br.readLine().split(" ");
		int N = Integer.parseInt(input1[0]);
		int M = Integer.parseInt(input1[1]);
		int [][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		String input="";
		for(int i = 0; i < N; i++) {
			input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		sfor(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " " );
			}
			System.out.println();
		}
		int min = 1;
		Queue<node> q = new LinkedList<>();
		q.add(new node(0,0));
		
		while(!q.isEmpty()) {
			node cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				 int nx = cur.x + dir[i][0];
				 int ny = cur.y + dir[i][1];
				 
				 if(nx >= 0 || nx < N && ny >= 0 && ny < M && visited[nx][ny]) {
					 visited[nx]
				 }
			}
		}
		
	}
}
*/