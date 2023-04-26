package com.neetcode.stack;

import java.util.Stack;

public class StockSpanner {

	Stack<int[]> st;

	public StockSpanner() {
		st = new Stack<>();
	}

	public int next(int price) {
		int span = 1;
		while (st.size() > 0 && price >= st.peek()[0]) {
			span += st.pop()[1];
		}
		st.push(new int[] { price, span });
		return span;
	}

	public static void main(String[] args) {
		StockSpanner span = new StockSpanner();
		System.out.println(span.next(100));
		System.out.println(span.next(80));
		System.out.println(span.next(60));
		System.out.println(span.next(70));
		System.out.println(span.next(60));
		System.out.println(span.next(75));
		System.out.println(span.next(85));
	}

}
