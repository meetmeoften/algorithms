package com.algoexpert.topological;

import java.util.*;

public class PhoneDictionaryPreProcess {

    // Character -> Digit mapping
    private static final Map<Character, Character> CHAR_TO_DIGIT = new HashMap<>();

    static {
        map("abc", '2');
        map("def", '3');
        map("ghi", '4');
        map("jkl", '5');
        map("mno", '6');
        map("pqrs", '7');
        map("tuv", '8');
        map("wxyz", '9');
    }

    private static void map(String letters, char digit) {
        for (char c : letters.toCharArray()) {
            CHAR_TO_DIGIT.put(c, digit);
        }
    }

    // Encoded number -> words
    private final Map<String, Set<String>> phoneMap = new HashMap<>();

    public PhoneDictionaryPreProcess(List<String> words) {
        preprocess(words);
    }

    // Build dictionary once
    private void preprocess(List<String> words) {
        for (String word : words) {
            addWord(word);
        }
    }

    // Add a new word dynamically
    public void addWord(String word) {
        String number = encode(word);
        phoneMap
                .computeIfAbsent(number, k -> new HashSet<>())
                .add(word);
    }

    // Remove an existing word
    public void removeWord(String word) {
        String number = encode(word);
        Set<String> words = phoneMap.get(number);

        if (words == null)
            return;
        words.remove(word);
        // Remove key if no words remain
        if (words.isEmpty()) {
            phoneMap.remove(number);
        }
    }

    // Search by phone number
    public List<String> search(String phoneNumber) {

        Set<String> words = phoneMap.get(phoneNumber);

        if (words == null)
            return Collections.emptyList();

        return new ArrayList<>(words);
    }

    // Convert word -> digit string
    private String encode(String word) {

        StringBuilder sb = new StringBuilder();

        for (char c : word.toLowerCase().toCharArray()) {
            sb.append(CHAR_TO_DIGIT.get(c));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        List<String> dictionary = Arrays.asList(
                "careers",
                "linkedin",
                "linkedgo",
                "hiring",
                "interview"
        );

        PhoneDictionaryPreProcess phoneDictionary =
                new PhoneDictionaryPreProcess(dictionary);

        System.out.println(phoneDictionary.search("2273377"));
        // [careers]

        System.out.println(phoneDictionary.search("54653346"));
        // [linkedin, linkedgo]

        phoneDictionary.addWord("careful");

        System.out.println(phoneDictionary.search("2273385"));
        // [careful]

        phoneDictionary.removeWord("linkedin");

        System.out.println(phoneDictionary.search("54653346"));
        // [linkedgo]

        phoneDictionary.removeWord("linkedgo");

        System.out.println(phoneDictionary.search("54653346"));
        // []
    }
}
