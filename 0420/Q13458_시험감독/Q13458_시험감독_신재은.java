package study.algo;

import java.io.*;

public class B13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 시험장 수
		String[] rowA = br.readLine().split(" "); // 시험장별 응시자수
		String[] bc = br.readLine().split(" "); // 총감독 감시 가능, 부감독 감시 가능 응시자수
		int B = Integer.parseInt(bc[0]);
		int C = Integer.parseInt(bc[1]);
		
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(rowA[i]) - B; // 이미 총감독은 감시함
		}
		
		// 주의! 답이 커서 int로 하면 틀림
		long minD = N; // 총감독은 시험장마다 한 명은 있어야함
		for(int i=0; i<N; i++) {
			if(A[i] < 0) continue; // 이미 총감독으로 감시 완료
			minD += A[i] / C;
			if(A[i]%C != 0) minD++; // 나머지 있으면 한 명 더 들어가야함
		}
		
		System.out.println(minD);
	}

}
