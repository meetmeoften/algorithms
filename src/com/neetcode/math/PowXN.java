package com.neetcode.math;

public class PowXN {

	double myPow(double x, int n) {
		if (n < 0) {
			return 1 / x * myPow(1 / x, -(n + 1));
		}
		if (n == 0) {
			return 1;
		}
		if (n == 2) {
			return x * x;
		}
		if (n % 2 == 0) {
			return myPow(myPow(x, n / 2), 2);
		} else {
			return x * myPow(myPow(x, n / 2), 2);
		}
	}


	public double myPow2(double x, int n) {
		if (n == 0) {
			return 1.0;
		}

		if (n % 2 == 1) {
			return x * myPow2(x, n - 1);
		}

		if (n % 2 == 0) {
			return myPow2(x * x, n / 2);
		}

		return 1 / myPow2(x, -n);
	}

	public static void main(String[] args) {
		PowXN xn = new PowXN();
		xn.myPow2(2, -2);
	}

}
