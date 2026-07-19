package com.algoexpert.topological;

import java.util.*;

public class PhoneDictionaryTrie {

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

    static class TrieNode {
        TrieNode[] children = new TrieNode[8]; // digits 2-9
        Set<String> words = new HashSet<>();
        boolean isWord;
    }

    private final TrieNode root = new TrieNode();

    public PhoneDictionaryTrie(List<String> dictionary) {
        for (String word : dictionary) {
            addWord(word);
        }
    }

    // Add a word
    public void addWord(String word) {

        String number = encode(word);

        TrieNode curr = root;

        for (char digit : number.toCharArray()) {

            int idx = digit - '2';

            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
        }

        curr.isWord = true;
        curr.words.add(word);
    }

    // Search exact phone number
    public List<String> search(String phoneNumber) {

        TrieNode curr = root;

        for (char digit : phoneNumber.toCharArray()) {

            int idx = digit - '2';

            if (curr.children[idx] == null)
                return Collections.emptyList();

            curr = curr.children[idx];
        }

        return new ArrayList<>(curr.words);
    }

    // Remove a word
    public void removeWord(String word) {
        remove(root, encode(word), 0, word);
    }

    private boolean remove(TrieNode node,
                           String number,
                           int depth,
                           String word) {

        if (node == null)
            return false;

        if (depth == number.length()) {

            node.words.remove(word);

            if (node.words.isEmpty()) {
                node.isWord = false;
            }

            return isEmpty(node);
        }

        int idx = number.charAt(depth) - '2';

        if (remove(node.children[idx], number, depth + 1, word)) {
            node.children[idx] = null;
        }

        return isEmpty(node);
    }

    private boolean isEmpty(TrieNode node) {

        if (node.isWord)
            return false;

        for (TrieNode child : node.children) {
            if (child != null)
                return false;
        }

        return true;
    }

    // Convert word -> digits
    private String encode(String word) {

        StringBuilder sb = new StringBuilder();

        for (char c : word.toLowerCase().toCharArray()) {
            sb.append(CHAR_TO_DIGIT.get(c));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        List<String> words = Arrays.asList(
                "careers",
                "linkedin",
                "linkedgo",
                "hiring",
                "interview"
        );

        PhoneDictionaryTrie trie = new PhoneDictionaryTrie(words);

        System.out.println(trie.search("2273377"));
        // [careers]

        System.out.println(trie.search("54653346"));
        // [linkedin, linkedgo]

        trie.addWord("careful");

        System.out.println(trie.search("2273385"));
        // [careful]

        trie.removeWord("linkedin");

        System.out.println(trie.search("54653346"));
        // [linkedgo]

        trie.removeWord("linkedgo");

        System.out.println(trie.search("54653346"));
        // []
    }
}
