package com.lld.splitwise2.expense.split;

import com.lld.splitwise2.expense.ExpenseSplitType;

public class SplitFactory {

	public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {
		switch (splitType) {
		case EQUAL:
			return new EqualExpenseSplit();
		case UNEQUAL:
			return new UnequalExpenseSplit();
		case PERCENTAGE:
			return new PercentageExpenseSplit();
		default:
			return null;
		}
	}

}
