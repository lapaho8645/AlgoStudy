package com.algostudy;

public class BOJ13458 {
	    public static void main(String[] args){
	        java.util.Scanner scan = new java.util.Scanner(System.in);
	        long resultCnt = 0 ;
	        int N = scan.nextInt();
	        int arr[] = new int[N];
	        for(int i = 0 ; i <N; i++){
	            arr[i]=scan.nextInt();
	        }
	        int A = scan.nextInt();
	        int B = scan.nextInt();
	        for(int i = 0 ; i < N ; i ++){
	            arr[i]-=A;
	            resultCnt++;
	            if(arr[i]<=0) continue;
	            else {
	                if(arr[i]<=B) {
	                    resultCnt++;
	                    continue;
	                }
	                if(arr[i]%B==0){
	                    resultCnt+=arr[i]/B;
	                } else{
	                    resultCnt+=(arr[i]/B)+1;
	                }
	            }
	        }
	        System.out.println(resultCnt);
	    }
	}