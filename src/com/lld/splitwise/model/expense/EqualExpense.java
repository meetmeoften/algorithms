package com.lld.splitwise.model.expense;

import java.util.List;

import com.lld.splitwise.model.ExpenseMetadata;
import com.lld.splitwise.model.User;
import com.lld.splitwise.model.split.EqualSplit;
import com.lld.splitwise.model.split.Split;

public class EqualExpense extends Expense {
	public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
		super(amount, paidBy, splits, expenseMetadata);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof EqualSplit)) {
				return false;
			}
		}

		return true;
	}
}
