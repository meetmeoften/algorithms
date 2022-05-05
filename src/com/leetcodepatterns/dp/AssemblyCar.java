package com.leetcodepatterns.dp;

public class AssemblyCar {

	public static int recur(int[] h, int pos, int x, int y, int n) {
		if (pos >= n) {
			return 0;
		}
		int v1 = 0, v2 = 0, v3 = 0;

		if (x >= h[pos]) {
			v1++;
			v1 += recur(h, pos + 1, x - h[pos], y, n);
		}

		if (y >= h[pos]) {
			v2++;
			v2 += recur(h, pos + 1, x, y - h[pos], n);
		}
		v3 = recur(h, pos + 1, x, y, n);
		return Math.max(v1, Math.max(v2, v3));
	}


	public static void main(String[] args) {
		int[] h={6, 5, 5, 4, 3};
		int x=8,y=9;
		int n=h.length;

		int ans= recur(h,0,x,y,n);
		System.out.println(ans);
	}

}
