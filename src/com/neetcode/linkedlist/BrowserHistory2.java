package com.neetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class BrowserHistory2 {


	private List<String> urlsHistory;
	private int currURL;
	private int lastURL;

	public BrowserHistory2(String homepage) {
		urlsHistory = new ArrayList<>();
		urlsHistory.add(homepage);
		currURL = 0;
		lastURL = 0;
	}

	public void visit(String url) {
		currURL++;
		if (urlsHistory.size() > currURL) {
			urlsHistory.set(currURL, url);
		} else {
			urlsHistory.add(url);
		}
		lastURL = currURL;
	}

	public String back(int steps) {
		currURL = Math.max(0, currURL - steps);
		return urlsHistory.get(currURL);
	}

	public String forward(int steps) {
		currURL = Math.min(lastURL, currURL + steps);
		return urlsHistory.get(currURL);
	}

	public static void main(String[] args) {
		BrowserHistory2 b = new BrowserHistory2("home.com");
		b.visit("go.com");
		b.visit("fb.com");
		b.visit("yo.com");
		b.back(1);
		b.back(1);
		b.forward(1);
		b.visit("li.com");
	}

}
