package com.google;

public class NumberOfMatchingSubsequences {


    public int numMatchingSubseq(String source, String[] words) {
        int res = 0;
        for(String str: words){
            if(isSubsequence(source, str))
                res++;
        }
        return res;
    }

    public boolean isSubsequence(String source, String t) {// abcde, ace
        int i=0, j=0;
        while(i < source.length() && j < t.length()) {
            if(source.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return t.length() == j;
    }

}
