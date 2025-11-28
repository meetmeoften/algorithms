package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    /**
     * Given a list of integers, find out all the numbers starting with 1 using Stream functions?
     * Input: List<Integer> list = [10,15,8,49,25,98,32];
     * output: 10,15
     */
    public List<Integer> findNumbersStartingWithOne(List<Integer> inputList) {
        List<String> inputStringList = inputList.stream()
                .map(e -> e.toString())
                .collect(Collectors.toList());
        return inputStringList.stream().filter(e -> e.startsWith("1"))
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Test test = new Test();
        List<Integer> list = List.of(10,15,8,49,25,98,32);
        List<Integer> result = test.findNumbersStartingWithOne(list);
        System.out.println(result);
    }
}
