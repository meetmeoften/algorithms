package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EncodeDecode {

	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();

		for (String str : strs) {
			sb.append(str.length()).append(':').append(str);
		}

		return sb.toString();
	}

	public List<String> decode(String s) {
		if (s == null || s.length() == 0) {
			return Collections.emptyList();
		}

		List<String> result = new ArrayList<>();
		int idx = 0;

		while (idx < s.length()) {
			int colonIdx = s.indexOf(":", idx);
			int strLen = Integer.valueOf(s.substring(idx, colonIdx));
			idx = colonIdx + 1;
			result.add(s.substring(idx, idx + strLen));
			idx += strLen;
		}

		return result;
	}

	public static void main(String[] args) {
		EncodeDecode ec = new EncodeDecode();
		String encode =  ec.encode(Arrays.asList("lint:", "code"));
		ec.decode(encode);

	}
}
