package com.lld.splitwise2.expense;

import java.util.List;

import com.lld.splitwise2.BalanceSheetController;
import com.lld.splitwise2.expense.split.ExpenseSplit;
import com.lld.splitwise2.expense.split.Split;
import com.lld.splitwise2.expense.split.SplitFactory;
import com.lld.splitwise2.user.User;

public class ExpenseController {

	BalanceSheetController balanceSheetController;
	public ExpenseController(){
		balanceSheetController = new BalanceSheetController();
	}

	public Expense createExpense(String expenseId, String description, double expenseAmount,
			List<Split> splitDetails, ExpenseSplitType splitType, User paidByUser){

		ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
		expenseSplit.validateSplitRequest(splitDetails, expenseAmount);

		Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);

		balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);

		return expense;
	}

}
