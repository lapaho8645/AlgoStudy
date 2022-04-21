package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
     북3-2
서2-1     동1-0
     남4-3
*/

public class Q14499_주사위굴리기_성성민 {

    static int N,M,x,y,K,arr[][], dice[], ndice[], di[]={0,0,-1,1}, dj[]={1,-1,0,0}; // 동서북남

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        x = Integer.parseInt(str[2]);
        y = Integer.parseInt(str[3]);
        K = Integer.parseInt(str[4]); // 명령 개수

        arr = new int[N][M];
        for(int i=0; i<N; i++){
            str = br.readLine().split(" ");
            for(int j=0; j<M; j++){
                arr[i][j]=Integer.parseInt(str[j]);
            }
        }

        dice = new int[7];
        ndice = new int[7];

        int dir=0;
        str = br.readLine().split(" ");
        for(int d=0; d<K; d++) {
            dir=Integer.parseInt(str[d])-1;
            int ni=x+di[dir];
            int nj=y+dj[dir];

            if(ni>=0 && ni<N && nj>=0 && nj<M){
                moveDice(dir);
                if(arr[ni][nj]==0) arr[ni][nj]=dice[6];
                else {
                    dice[6]=arr[ni][nj];
                    arr[ni][nj]=0;
                }
                x=ni; y=nj;
                System.out.println(dice[1]);
            }
        }
    }

    public static void moveDice(int d){
        if(d==0){ // 동
            ndice[4] = dice[1];
            ndice[1] = dice[3];
            ndice[6] = dice[4];
            ndice[3] = dice[6];
        } else if(d==1){  // 서
            ndice[3] = dice[1];
            ndice[6] = dice[3];
            ndice[1] = dice[4];
            ndice[4] = dice[6];
        } else if(d==2){  // 북
            ndice[5] = dice[1];
            ndice[1] = dice[2];
            ndice[6] = dice[5];
            ndice[2] = dice[6];
        } else if(d==3){  // 남
            ndice[2] = dice[1];
            ndice[6] = dice[2];
            ndice[1] = dice[5];
            ndice[5] = dice[6];
        }
        for(int i=1; i<7; i++) dice[i]=ndice[i];

    } // moveDice end

}
