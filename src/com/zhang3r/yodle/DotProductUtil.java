package com.zhang3r.yodle;

public class DotProductUtil {

	public static double dotProduct(int[] a1, int[] a2) {
		if (a1.length == a2.length) {
			double result=0;
			for (int i = 0; i < a1.length; i++) {
				result+= a1[i]*a2[i];
			}
			return result;
		}
		return -1;
	}

}
