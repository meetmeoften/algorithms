package com.techiedelight.binarytree.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    static class Node {
        char character;
        int frequency;
        Node left;
        Node right;

        public Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = this.right = null;
        }
    }

    public static Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() > 1) {
            Node left = minHeap.poll();
            Node right = minHeap.poll();
            Node newNode = new Node('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            minHeap.add(newNode);
        }

        return minHeap.poll();
    }

    // Step 2: Generate the Huffman codes by traversing the tree
    public static void generateHuffmanCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;

        // If it's a leaf node, add the character and its code to the map
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        // Traverse left and right subtrees
        generateHuffmanCodes(root.left, code + "0", huffmanCodes);
        generateHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    // Step 3: Encode the string using the Huffman codes
    public static String encode(String input, Map<Character, String> huffmanCodes) {
        StringBuilder encodedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedString.append(huffmanCodes.get(c));
        }
        return encodedString.toString();
    }

    // Step 4: Decode the string using the Huffman tree
    public static String decode(String encodedString, Node root) {
        StringBuilder decodedString = new StringBuilder();
        Node current = root;

        // Traverse the encoded string, moving left for '0' and right for '1'
        for (int i = 0; i < encodedString.length(); i++) {
            if (encodedString.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            // When we reach a leaf node, append the character to the result
            if (current.left == null && current.right == null) {
                decodedString.append(current.character);
                current = root;  // Go back to the root to start decoding the next character
            }
        }

        return decodedString.toString();
    }

    public static void main(String[] args) {
        // Example input string
        String input = "aabbcc";

        // Step 1: Calculate frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Build the Huffman Tree
        Node root = buildHuffmanTree(frequencyMap);

        // Step 3: Generate Huffman codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, "", huffmanCodes);

        // Step 4: Encode the input string
        String encodedString = encode(input, huffmanCodes);
        System.out.println("Encoded String: " + encodedString);

        // Step 5: Decode the encoded string
        String decodedString = decode(encodedString, root);
        System.out.println("Decoded String: " + decodedString);
    }
}
