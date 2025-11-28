package com.test;

import java.util.Random;

public class RandomPasswordGenerator {

    // Minimum of 6 Character
    // one special character
    // one Upper case
    // one lower case
    // one digit
    // Max 14 character
    // should not repeat

    public static void main(String[] args) {
        // Builder pattern

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        Integer length = random.nextInt(); // total length from 6 to 14
        length = 6;

        if(length > 0) {
            builder.append(generateSpecialCharacter(1));
            length--;
        }
        if(length > 0) {
            builder.append(generateNumeric(1));
            length--;
        }
        if(length > 0) {
            builder.append(generateUpperCase(1));
            length--;
        }
        if(length > 0) {
            builder.append(generateLowerCase(1));
            length--;
        }
        if(length > 0) {
            builder.append(generateRandom(2));
            length--;
        }
       System.out.println(builder.toString());
    }


    /**
     *   factory --> (lower and upper)
     *
     *
     *
     * @param length
     * @return
     */

    public static String generateSpecialCharacter(Integer length) {
        return "#";  //1
    }

    public static String generateNumeric(Integer length) {
        return "1"; // 2
    }

    public static String generateUpperCase(Integer length) {
        return "A";
    }

    public static String generateLowerCase(Integer length) {
        return "a";
    }

    public static String generateRandom(Integer length) {
        return "ab";
    }




}
