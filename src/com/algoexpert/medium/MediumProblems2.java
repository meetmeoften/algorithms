package com.algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MediumProblems2 {


	// Task Assignment
	public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
		ArrayList<ArrayList<Integer>> pairedTasks = new ArrayList<>();

		HashMap<Integer, ArrayList<Integer>> map = getTaskDurationToIndices(tasks);
		ArrayList<Integer> sortedTasks = tasks;
		Collections.sort(sortedTasks);

		for(int i= 0; i < k ;i++) {
			int task1Duration = sortedTasks.get(i);
			var indiciesWithTask1Duration = map.get(task1Duration);
			var task1Index = indiciesWithTask1Duration.remove(indiciesWithTask1Duration.size()-1);

			int task2SortedIndex = tasks.size()-1-i;
			int task2Duration = sortedTasks.get(task2SortedIndex);
			var indiciesWithTask2Duration = map.get(task2Duration);
			var task2Index = indiciesWithTask2Duration.remove(indiciesWithTask2Duration.size()-1);

			ArrayList<Integer> pairedTask = new ArrayList<>();
			pairedTask.add(task1Index);
			pairedTask.add(task2Index);
			pairedTasks.add(pairedTask);

		}
		return pairedTasks;
	}

	public HashMap<Integer, ArrayList<Integer>> getTaskDurationToIndices(ArrayList<Integer> tasks) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for(int i= 0; i< tasks.size(); i++) {
			int taskDuration = tasks.get(i);
			if(map.containsKey(taskDuration)) {
				map.get(taskDuration).add(i);
			} else {
				ArrayList<Integer> tempList = new ArrayList<>();
				tempList.add(i);
				map.put(taskDuration, tempList);
			}
		}
		return map;
	}

	// Valid Starting City
	public int validStartingCity(int[] distances, int[] fuel, int mpg) {

		int totalCities = distances.length;
		int milesRemaining = 0;

		int startCityIndex = 0;
		int smallestMilesRemaining = 0;

		for(int i= 1; i < totalCities; i++) {
			int fuelFromPreviousCity = fuel[i-1];
			int distanceFromPreviousCity = distances[i-1];

			milesRemaining += (fuelFromPreviousCity * mpg ) - distanceFromPreviousCity;

			if(milesRemaining < smallestMilesRemaining) {
				smallestMilesRemaining = milesRemaining;
				startCityIndex = i;
			}
		}
		return startCityIndex;

	}

	// Valid Starting City
	public int validStartingCity2(int[] distances, int[] fuel, int mpg) {
		int totalCities = distances.length;

		for(int i=0; i< totalCities; i++) {
			int milesRemaining = 0;

			for(int j =i; j< i+totalCities; j++) {
				if(milesRemaining < 0) {
					continue;
				}
				int currentCityRotated = j%totalCities;
				milesRemaining += (fuel[currentCityRotated] * mpg) - distances[currentCityRotated];
			}
			if(milesRemaining >= 0) {
				return i;
			}
		}
		return -1;
	}

	// Permutations
	public static List<List<Integer>> getPermutations(List<Integer> array) {
		List<List<Integer>> permutations = new ArrayList<>();
		getPermutations(0, array, permutations);
		return permutations;

	}

	private static void getPermutations(int i, List<Integer> array, List<List<Integer>> permutations) {
		if(i== array.size() -1) {
			permutations.add(new ArrayList<Integer>(array));
		} else {
			for(int j = i ; j < array.size(); j++) {
				swap(array, i, j);
				getPermutations(i+1, array, permutations);
				swap(array, i, j);
			}
		}
	}

	public static void swap(List<Integer> array, int i, int j) {
		var temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	//PowerSet
	public static List<List<Integer>> powerset(List<Integer> array) {
		return powerSetHelper(array, array.size()-1);

	}

	private static List<List<Integer>> powerSetHelper(List<Integer> array, int i) {
		// TODO Auto-generated method stub
		return null;
	}


	//Search In Sorted Matrix
	public static int[] searchInSortedMatrix(int[][] matrix, int target) {

		int row= 0;
		int col = matrix[0].length-1;

		while(row < matrix.length && col >= 0) {
			int searchValue = matrix[row][col];
			if(target == searchValue) {
				return new int[] {row, col};
			} else if(searchValue > target) {
				col--;
			} else if(searchValue < target) {
				row++;
			}
		}
		return new int[] {-1, -1};
	}


	// Binary Search
	public static int binarySearch(int[] array, int target) {
		int left = 0;
		int right = array.length-1;

		while(left <= right) {
			int mid = (left+ right)/2;
			if(array[mid] == target) {
				return mid;
			} else if(array[mid] > target) {
				right = mid -1;
			} else if(array[mid] < target) {
				left = mid + 1;
			}
		}

		return -1;
	}

	// Three Number Sort
	public int[] threeNumberSort(int[] array, int[] order) {
		int firstNum = order[0];
		int secondNum = order[1];

		int low = 0;
		int mid = 0;
		int high = array.length -1;

		while(mid <= high) {
			int value = array[mid];
			if(firstNum == value) {
				swap(low, mid, array);
				low++;
				mid++;
			} else if(secondNum == value) {
				mid++;
			} else {
				swap(mid, high, array);
				high--;
			}
		}
		return array;
	}

	private void swap(int low, int mid, int[] array) {
		// TODO Auto-generated method stub
		var temp = array[low];
		array[low] = array[mid];
		array[mid] = temp;

	}

	// Stack
	// balanced Brackets
	public static boolean balancedBrackets(String str) {

		String openingBrackets = "([{";
		String closingBrackets = ")]}";

		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');

		List<Character> stack = new ArrayList<>();

		for(int i=0; i<str.length(); i++) {
			Character letter = str.charAt(i);
			if(openingBrackets.indexOf(letter) != -1) {
				stack.add(letter);
			} else if(closingBrackets.indexOf(letter) != -1) {
				if(stack.size() == 0) {
					return false;
				}
				var lastAddedOpeningBracket = stack.get(stack.size()-1);
				if(lastAddedOpeningBracket ==  map.get(letter)) {
					stack.remove(stack.size()-1);
				} else {
					return false;
				}
			}
		}
		return stack.size() == 0;
	}

	//Next Greater Element
	public int[] nextGreaterElement(int[] array) {

		int[] result = new int[array.length];
		Arrays.fill(result, -1);
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < 2 * array.length; i++) {
			int circularIndex = i % array.length;
			while (stack.size() > 0) {
				if (array[circularIndex] > array[stack.peek()]) {
					int topIndex = stack.pop();
					result[topIndex] = array[circularIndex];
				} else {
					break;
				}
			}
			stack.push(circularIndex);
		}
		return result;
	}

	//SunSetViews
	public ArrayList<Integer> sunsetViews(int[] buildings, String direction) {
		ArrayList<Integer> buildingWithViews = new ArrayList<>();

		int startIndex = buildings.length-1;
		int step = -1;

		if(direction.equals("WEST")) {
			startIndex = 0;
			step = 1;
		}

		int index = startIndex;
		int runningMaxHeight = 0;

		while(index>=0 && index < buildings.length) {
			int buildingHeight = buildings[index];

			if(buildingHeight > runningMaxHeight) {
				runningMaxHeight = Math.max(runningMaxHeight, buildingHeight);
				buildingWithViews.add(index);
			}
			index = index+ step;
		}
		if(direction.equals("EAST")) {
			Collections.reverse(buildingWithViews);
		}
		return buildingWithViews;

	}

	// SortStack
	public ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
		if(stack.size() == 0) {
			return stack;
		}
		int top = stack.remove(stack.size() -1);
		sortStack(stack);
		insertInSortedOrder(stack, top);
		return stack;

	}

	private void insertInSortedOrder(ArrayList<Integer> stack, int value) {
		// TODO Auto-generated method stub
		if(stack.size() ==0  || stack.get(stack.size()-1) <= value) {
			stack.add(value);
			return;
		}
		int top = stack.remove(stack.size() -1);
		insertInSortedOrder(stack, top);
		stack.add(top);

	}

	static class MinMaxStack {

		List<Map<String, Integer>> minMaxStack = new ArrayList<>();
		List<Integer> stack = new ArrayList<>();

		public int peek() {
			// Write your code here.
			return stack.get(stack.size()-1);
		}

		public int pop() {
			minMaxStack.remove(minMaxStack.size()-1);
			return stack.remove(stack.size()-1);
		}

		public void push(Integer number) {
			Map<String, Integer> map = new HashMap<>();
			map.put("min", number);
			map.put("max", number);

			if(minMaxStack.size() > 0) {
				Map<String, Integer> lastMinMaxMap = new HashMap<>(minMaxStack.get(minMaxStack.size()-1));

				map.replace("min", Math.min(lastMinMaxMap.get("min"), number));
				map.replace("max", Math.max(lastMinMaxMap.get("max"), number));

			}
			minMaxStack.add(map);
			stack.add(number);
		}

		public int getMin() {
			return minMaxStack.get(minMaxStack.size()-1).get("min");
		}

		public int getMax() {
			return minMaxStack.get(minMaxStack.size()-1).get("max");
		}
	}

	//Reverse words in a String
	public String reverseWordsInString(String string) {

		ArrayList<String> words = new ArrayList<>();
		int startOfWord = 0;

		for(int i= 0; i< string.length(); i++) {
			Character character = string.charAt(i);

			if(character == ' ') {
				words.add(string.substring(startOfWord, i));
				startOfWord = i;
			} else if(string.charAt(startOfWord) == ' ') {
				words.add(" ");
				startOfWord = i;
			}
		}
		words.add(string.substring(startOfWord));
		Collections.reverse(words);

		return String.join("", words);

	}


	//Minimum Characters for Words
	public char[] minimumCharactersForWords(String[] words) {

		HashMap<Character, Integer> maxCharFreq = new HashMap<>();

		for(String word: words) {
			Map<Character, Integer> wordCharFreq = countCharFrequencies(word);

			// Update maxX
			for(Map.Entry<Character, Integer> frequency: wordCharFreq.entrySet()) {
				char charac = frequency.getKey();
				int characFreq = frequency.getValue();
			}

			for(Map.Entry<Character, Integer> frequency: wordCharFreq.entrySet()) {
				char charac = frequency.getKey();
				int characFreq = frequency.getValue();

				if(maxCharFreq.containsKey(charac)) {
					maxCharFreq.put(charac, Math.max(characFreq, maxCharFreq.get(charac)));
				} else {
					maxCharFreq.put(charac, characFreq);
				}
			}
		}

		return new char[0];

	}

	private Map<Character, Integer> countCharFrequencies(String word) {
		HashMap<Character, Integer> wordCharFreq = new HashMap<>();
		for(Character letter: word.toCharArray()) {
			wordCharFreq.put(letter, wordCharFreq.getOrDefault(letter, 0)+1);
		}
		return wordCharFreq;

	}

	// GroupAnagrams
	public static List<List<String>> groupAnagrams(List<String> words) {

		HashMap<String, List<String>> anagrams = new HashMap<>();
		for(String word: words) {
			var charArray = word.toCharArray();
			Arrays.sort(charArray);
			String sortedWord = new String(charArray);

			if(anagrams.containsKey(sortedWord)) {
				anagrams.get(sortedWord).add(word);
			} else {
				List<String> list = new ArrayList<>();
				list.add(word);
				anagrams.put(sortedWord, list);
			}
		}
		return new ArrayList<>(anagrams.values());
	}


	// String Palindrome
	public static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() -1;
		while(left < right) {
			if(str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static String longestPalindromicSubstring(String str) {
		String longest = "";
		for(int i= 0; i< str.length(); i++) {
			for(int j=i; j< str.length(); j++) {
				String subString = str.substring(i, j+1);
				if(subString.length() > longest.length() && isPalindrome(subString)) {
					longest = subString;
				}
			}
		}
		return longest;
	}

	public static String longestPalindromicSubstring2(String str) {
		int[] currentLongest = new int[] {0,1};

		for(int i= 1; i < str.length(); i++) {
			int[] odd = getLongestPalindromeFrom(str, i-1, i+1);
			int[] even = getLongestPalindromeFrom(str, i-1, i);

			int[] longest = odd[1] - odd[0] > even[1] - even[0] ? odd: even;
			currentLongest = currentLongest[1] - currentLongest[0] > longest[1] - longest[0] ? currentLongest: longest;
		}
		return str.substring(currentLongest[0], currentLongest[1]);
	}


	public static int[] getLongestPalindromeFrom(String str, int left, int right) {
		while(left >=0 && right < str.length()) {
			if(str.charAt(left) != str.charAt(right)) {
				break;
			}
			left++;
			right--;
		}
		return new int[] {left+1, right};
	}

	// Valid Ip Address
	public ArrayList<String> validIPAddresses(String string) {
		ArrayList<String> ipAddressList = new ArrayList<>();
		int minLength = Math.min(string.length(), 4);
		for(int i=1; i < minLength; i++) {
			String[] currentIpAddressParts = new String[] {"", "", "", ""};

			currentIpAddressParts[0] = string.substring(0, i);

			if(!isValidIpPartOfIp(string)) {
				continue;
			}

			for(int j = i+1; j < i+ Math.min(string.length()-i, 4); j++) {
				currentIpAddressParts[1] = string.substring(i, j);
				if(!isValidIpPartOfIp(currentIpAddressParts[1])) {
					continue;
				}


				for(int k = j+1; k < j+ Math.min(string.length()-j, 4); k++) {
					currentIpAddressParts[2] = string.substring(j, k);
					currentIpAddressParts[3] = string.substring(k);

					if(isValidIpPartOfIp(currentIpAddressParts[2]) && isValidIpPartOfIp(currentIpAddressParts[3])) {
					}
				}
			}

		}


		return ipAddressList;
	}

	public boolean isValidIpPartOfIp(String string) {
		int  stringAsInt = Integer.parseInt(string);
		if(stringAsInt > 255) {
			return false;
		}
		return string.length() == Integer.toString(stringAsInt).length();
	}


	// TrieNode
	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	}

	static class SuffixTrie {
		TrieNode root = new TrieNode();
		char endSymbol = '*';

		public SuffixTrie(String str) {
			populateSuffixTrieFrom(str);
		}

		public void populateSuffixTrieFrom(String str) {
			// Write your code here.
		}

		public boolean contains(String str) {
			// Write your code here.
			TrieNode node = root;
			for(int i=0; i< str.length(); i++) {
				char letter = str.charAt(i);
				if(!node.children.containsKey(letter)) {
					return false;
				}
				node = node.children.get(letter);
			}
			return node.children.containsKey(endSymbol);
		}
	}
}
