package com.lld.splitwise2.expense.split;

import java.util.List;

public interface ExpenseSplit {

	public void validateSplitRequest(List<Split> splitList, double totalAmount);

}
