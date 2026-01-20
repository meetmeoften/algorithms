package com.neetcode.stack;

import java.util.Stack;

public class BasicCalculator3 {

    public int calculate(String s) {
        return helper(s.toCharArray(), new int[]{0});
    }

    private static int helper2(char[] arr, int[] index) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';

        while (index[0] < arr.length) {
            char c = arr[index[0]];

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (c == '(') {
                index[0]++;
                num = helper2(arr, index); // solve inside parentheses
            }

            // If operator OR end OR closing parenthesis
            if ((!Character.isDigit(c) && c != ' ') || index[0] == arr.length - 1) {

                if (sign == '+') stack.push(num);
                else if (sign == '-') stack.push(-num);
                else if (sign == '*') stack.push(stack.pop() * num);
                else if (sign == '/') stack.push(stack.pop() / num);

                sign = c;
                num = 0;
            }

            if (c == ')') break;  // return to previous call
            index[0]++;
        }

        int result = 0;
        for (int v : stack) result += v;
        return result;
    }


    private int helper(char[] arr, int[] idx) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';

        while (idx[0] < arr.length) {
            char c = arr[idx[0]];

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (c == '(') {
                idx[0]++; // skip '('
                num = helper(arr, idx); // compute inside parentheses
            }

            // When operator or end or ')'
            if ((!Character.isDigit(c) && c != ' ') || idx[0] == arr.length - 1) {
//                if (idx[0] == arr.length - 1 && Character.isDigit(c)) {
//                    // add last number if at end
//                }

                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }

                if (c == ')') break; // end parentheses group

                sign = c;
                num = 0;
            }

            idx[0]++;
        }

        int sum = 0;
        for (int v : stack) sum += v;
        return sum;
    }

    public static void main(String[] args) {
        BasicCalculator3 basicCalculator3 = new BasicCalculator3();
        basicCalculator3.calculate("1-(2*1)");
    }
}
