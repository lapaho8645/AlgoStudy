package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 시험장의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] places = new int[N];
		for(int i=0; i<N; i++) {
			places[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken()); // 총감독관이 감시하는 응시자수
		int C = Integer.parseInt(st.nextToken()); // 부감독관
		long ans = 0;
		int min = 1;
		
		for(int i=0; i<N; i++) {
			int num = places[i]-B;
			if(num<=0) {
				ans += min;
				continue;
			} 
			ans += (min + num/C);
			if(num%C!=0) ans++;
		}
		System.out.println(ans);

	}
}
