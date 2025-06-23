package com.lld.splitwise2.expense;

import java.util.ArrayList;
import java.util.List;

import com.lld.splitwise2.expense.split.Split;
import com.lld.splitwise2.user.User;

public class Expense {

	String expenseId;
	String description;
	double expenseAmount;
	User paidByUser;
	ExpenseSplitType splitType;
	List<Split> splitDetails = new ArrayList<>();

	public Expense(String expenseId, double expenseAmount, String description,
			User paidByUser, ExpenseSplitType splitType, List<Split> splitDetails) {

		this.expenseId = expenseId;
		this.expenseAmount = expenseAmount;
		this.description = description;
		this.paidByUser = paidByUser;
		this.splitType = splitType;
		this.splitDetails.addAll(splitDetails);

	}

}
