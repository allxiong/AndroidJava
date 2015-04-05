package com.example.JunitTest;

public class Utils {
	public static int sum(int[] vec){
		int total = 0;
		for(int i=0; i<vec.length; i++){
			total += vec[i];
		}
		return total;
	}

	public static int multiply(int[] vec){
		int total = 1;
		for(int i=0; i<vec.length; i++){
			total *= vec[i];
		}
		return total;
	}

}
