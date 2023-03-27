package com.algoexpert2.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ShortenPath {

	public static String simplifyPath(String path) {
		Stack<String> stack = new Stack<>();
		String[] p = path.split("/");

		for (int i = 0; i < p.length; i++) {
			if (!stack.empty() && p[i].equals("..")) {
				stack.pop();
			} else if (!p[i].equals(".") && !p[i].equals("") && !p[i].equals("..")) {
				stack.push(p[i]);
			}
		}
		List<String> list = new ArrayList<>(stack);
		return "/" + String.join("/", list);
	}


	public static String simplifyPath2(String path) {
		boolean startsWithPath = path.charAt(0) == '/';

		String[] p = path.split("/");

		List<String> tokensList = Arrays.asList(p);

		List<String> filteredTokens =
				tokensList.stream().filter(token -> isImportantToken(token))
				.collect(Collectors.toList());
		List<String> stack = new ArrayList<>();

		if(startsWithPath) {
			stack.add("");
		}

		Stack<String> stack2 = new Stack<>();
		for(String token: p) {
			if(token.equals("..")) {
				if(!stack2.empty() && token.equals("..")) {
					stack2.pop();
				}
			} else if(!token.equals(".") && !token.equals("") && !token.equals("..")) {
				stack2.push(token);
			}
		}

		for(String token: filteredTokens) {
			if(token.equals("..")) {
				if (stack.size() == 0 || stack.get(stack.size() - 1).equals("..")) {
					stack.add(token);
				} else if (!stack.get(stack.size()-1).equals("")) {
					stack.remove(stack.size()-1);
				}
			} else {
				stack.add(token);
			}
		}

		if(stack.size() == 1 && stack.get(0).equals("")) {
			return "/";
		}

		return String.join("/", stack);
	}

	public static boolean isImportantToken(String token) {
		return token.length() > 0 && !token.equals(".");
	}

	public static void main(String[] args) {
		//String s = "/home/..//foo/";

		String s =  "../../foo/../../bar/baz";

		System.out.println(simplifyPath(s));
		System.out.println(simplifyPath2(s));

	}

}
