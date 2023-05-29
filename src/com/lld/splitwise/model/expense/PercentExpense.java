package com.lld.splitwise.model.expense;

import java.util.List;

import com.lld.splitwise.model.ExpenseMetadata;
import com.lld.splitwise.model.User;
import com.lld.splitwise.model.split.PercentSplit;
import com.lld.splitwise.model.split.Split;

public class PercentExpense extends Expense {
	public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
		super(amount, paidBy, splits, expenseMetadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof PercentSplit)) {
				return false;
			}
		}

		double totalPercent = 100;
		double sumSplitPercent = 0;
		for (Split split : getSplits()) {
			PercentSplit exactSplit = (PercentSplit) split;
			sumSplitPercent += exactSplit.getPercent();
		}

		if (totalPercent != sumSplitPercent) {
			return false;
		}

		return true;
	}
}