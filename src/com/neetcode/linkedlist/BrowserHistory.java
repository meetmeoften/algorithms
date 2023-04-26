package com.neetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserHistory {

	private Deque<String> history = new ArrayDeque<>(); // Stack to store past pages
	private Deque<String> future; // Stack to store future pages

	public BrowserHistory(String homepage) {
		visit(homepage);
	}

	public void visit(String url) {
		history.push(url);
		future = new ArrayDeque<>();
	}

	public String back(int steps) {
		while (history.size() > 1 && steps-- > 0) {
			future.push(history.pop()); // Move pages from history to future stack
		}
		return history.peek(); // Return the current page after navigation
	}

	public String forward(int steps) {
		while (!future.isEmpty() && steps-- > 0) {
			history.push(future.pop()); // Move pages from future to history stack
		}
		return history.peek(); // Return the current page after navigation
	}

	public static void main(String[] args) {
		BrowserHistory b = new BrowserHistory("home.com");
		b.visit("go.com");
		b.visit("fb.com");
		b.visit("yo.com");
		b.back(1);
		b.back(1);
		b.forward(1);
		b.visit("li.com");
	}

}
