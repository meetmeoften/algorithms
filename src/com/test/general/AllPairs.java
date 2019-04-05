package com.test.general;

public class AllPairs {

	String[] chars = { "a", "b", "c" };
	int reqLen = 3;

	private void formStrings(String crtStr) {

		if (crtStr.length() == reqLen) {

			System.out.println(crtStr);
			return;
		} else {
			for (int i = 0; i < chars.length; i++) {
				formStrings(crtStr + chars[i]);
			}
		}

	}



	class A1 {

	}

	public class B2 extends A1 {

	}


	public static void main(String[] args) {

		//new AllPairs().formStrings("");

		B obj = new B();
		obj.i = 1;
		obj.j = 2;
		obj.display();
	}



}