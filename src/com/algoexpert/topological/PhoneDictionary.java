package com.algoexpert.topological;

import java.util.*;

public class PhoneDictionary {


    private static final Map<Character, Character> MAP = new HashMap<>();

    static {
        put("abc", '2');
        put("def", '3');
        put("ghi", '4');
        put("jkl", '5');
        put("mno", '6');
        put("pqrs", '7');
        put("tuv", '8');
        put("wxyz", '9');
    }

    private static void put(String letters, char digit) {
        for (char c : letters.toCharArray()) {
            MAP.put(c, digit);
        }
    }

    public static List<String> findWords(String phoneNumber,
                                         List<String> words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (word.length() != phoneNumber.length())
                continue;
            if (matches(word, phoneNumber))
                result.add(word);
        }
        return result;
    }

    private static boolean matches(String word, String phoneNumber) {
        for (int i = 0; i < word.length(); i++) {
            if (MAP.get(word.charAt(i)) != phoneNumber.charAt(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "careers",
                "linkedin",
                "hiring",
                "interview",
                "linkedgo"
        );
        System.out.println(findWords("2273377", words));
        System.out.println(findWords("54653346", words));
    }
}
