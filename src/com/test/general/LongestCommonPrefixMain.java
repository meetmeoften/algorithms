package com.test.general;

public class LongestCommonPrefixMain {

	public static void main(String[] args)
	{
		String[] strArr={"java2blog","javaworld","javabean","javatemp"};
		String longestPrefix=getLongestCommonPrefix(strArr);
		System.out.println("Longest Prefix : "+longestPrefix);
	}
	public static String getLongestCommonPrefix(String[] strArr) {
		if(strArr.length==0) {
			return "";
		}
		// Find minimum length String
		String minStr=getMinString(strArr);

		int minPrefixStrLength=minStr.length();
		for (String element : strArr) {
			int j;
			for( j=0;j<minPrefixStrLength;j++){
				if(minStr.charAt(j)!=element.charAt(j)) {
					break;
				}
			}
			if(j<minPrefixStrLength) {
				minPrefixStrLength=j;
			}
		}
		return minStr.substring(0,minPrefixStrLength);
	}
	public static String getMinString(String[] strArr)
	{
		String minStr=strArr[0];
		for(int i=1;i<strArr.length;i++){
			if(strArr[i].length()<minStr.length()) {
				minStr=strArr[i];
			}
		}
		return minStr;
	}
}
