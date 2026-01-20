package com.neetcode.stack;

import java.util.Stack;

public class BasicCalculator3NoRecursion {

    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                nums.push(num);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    compute(nums, ops);
                }
                ops.pop(); // remove '('
            } else { // operator + - * /
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    compute(nums, ops);
                }
                ops.push(c);
            }
        }
        // Finish remaining operations
        while (!ops.isEmpty()) {
            compute(nums, ops);
        }
        return nums.pop();
    }

    private void compute(Stack<Integer> nums, Stack<Character> ops) {
        int b = nums.pop();
        int a = nums.pop();
        char op = ops.pop();

        int result = 0;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        nums.push(result);
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }
}
