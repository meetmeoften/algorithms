package com.test.general;

public class GcdPrime {

	public static void main(String[] args) {
		GcdPrime prime = new GcdPrime();
		//		prime.prime(275858575);
		//		findGCD(16, 10);
		int[] element_array = {2, 7, 3, 9, 4};
		//		System.out.println(lcm_of_array_elements(element_array));
		System.out.println(findLCM(element_array, element_array.length));


	}

	private  void prime(int n) {
		int count = 1;
		for (int i = 2; i < n; i++) {
			boolean isValid = true;
			//			for (int j = 2; j < i; j++) {
			//				if(i%j== 0) {
			//					isValid = false;
			//					break;
			//				}
			//			}
			if(isValid) {
				int value = findGCD(i, n);
				if(value == 1) {
					count++;
				}
			}
		}
		System.out.println(count);

	}


	private static int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static void coprime(long n) {
		int j, i, k;
		int no = 0;

		for (i = 1; i <= n; i++) {
			k = 0;
			for (j = 1; j <= i; j++) {
				if (i % j == 0 && n % j == 0) {
					k++;
				}
			}
			if (k == 1) {
				no++;
			}
		}
		System.out.println(no);

	}

	public static void hcfAndLcm(int x, int y) {
		int a, b, t, hcf, lcm;

		System.out.print("Enter Two Number : ");
		a = x;
		b = y;

		while (b != 0) {
			t = b;
			b = a % b;
			a = t;
		}

		hcf = a;
		lcm = (x * y) / hcf;

		System.out.print("HCF = " + hcf);
		System.out.print("\nLCM = " + lcm);
	}

	private static int lcm(int a, int b) {
		return a * (b / gcd(a, b));
	}


	private  static int findGCD(int number1, int number2) {
		if (number2 == 0) {
			return number1;
		}
		return findGCD(number2, number1 % number2);
	}

	static int findGCD(int arr[], int n) {
		int result = arr[0];
		for (int i = 1; i < n; i++) {
			result = gcd(arr[i], result);
		}

		return result;
	}

	static int findLCM(int arr[], int n) {
		int ans = arr[0];

		// ans contains LCM of arr[0],..arr[i]
		// after i'th iteration,
		for (int i=1; i<n; i++) {
			ans = ( ((arr[i]*ans)) /
					(findGCD(arr[i], ans)) );
		}

		return ans;
	}
}
