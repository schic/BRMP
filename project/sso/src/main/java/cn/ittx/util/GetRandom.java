package cn.ittx.util;

import java.util.Random;


public class GetRandom {
	/*
	 *main方法 
	public static void main(String[] args) {
		System.out.println(getRandomMaxMin(99, 10));
	}
	 */

	/*
	 *生成随机 最大 到最小 
	 */
	public static int getRandomMaxMin(int max,int min){
        return new Random().nextInt((max+1-min)) + min;
				
	}
	
	

}
