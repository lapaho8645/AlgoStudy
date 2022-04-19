package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] num = new long[N];
		long result = N;
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(input[i]);
		}
		input = br.readLine().split(" ");
		int B = Integer.parseInt(input[0]);
		int C = Integer.parseInt(input[1]);
		for (int i = 0; i < N; i++) {
			if(num[i] - B < 0)
				continue;
			else	num[i] -= (long)B;
			
			if(num[i]%C == 0) result += num[i] / C;
			else result += num[i] / C + 1;
		}
		System.out.println(result);
	}

}
