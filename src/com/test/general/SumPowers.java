package com.test.general;

public class SumPowers {


	public static int exponent(int n,int m)
	{
		int nProd = n;
		// COMPLETE THE CODE THAT MAKES THE FUNCTION RETURN n^m
		for(int i=1;i<m;i++) {
			nProd=nProd*n;
		}
		return nProd;
	}
	public static void main(String args[])
	{
		int N=2, M=3;
		int power = 0;
		int result = 0;

		for (int i = 2; i < 5; i++) {
			power=exponent(2,i);
			result = result + power;
		}

		//		power=exponent(N,M);
		//		System.out.println ("Sum of Powers: "+power);
		System.out.println ("Sum of Powers: "+result);
	}

}
