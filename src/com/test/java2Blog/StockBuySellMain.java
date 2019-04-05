package com.test.java2Blog;

public class StockBuySellMain {

	public static void main(String[] args) {
		int arr[]={14, 12, 70, 15, 99, 65, 21, 90};
		System.out.println("Maximum profit :"+calculateMaxProfit(arr));

	}

	public static int calculateMaxProfit(int[] arr)
	{
		int lowestPriceTillThatDay=arr[0];
		int maxProfit=Integer.MIN_VALUE;
		for (int element : arr) {
			int profit=0;
			if(element >lowestPriceTillThatDay)
			{
				profit=element-lowestPriceTillThatDay;
				if(profit > maxProfit)
				{
					maxProfit=profit;
				}
			}
			else
			{
				lowestPriceTillThatDay=element;
			}
		}
		return maxProfit;
	}

}

