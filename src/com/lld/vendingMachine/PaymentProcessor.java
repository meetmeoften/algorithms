package com.lld.vendingMachine;

public class PaymentProcessor {

	private float balance;

	public void addPayment(Payment payment) {
		balance += payment.getAmount();
	}

	public void cancelTransaction() {
		balance = 0;
	}

	public void processPayment(float amount) {
		balance -= amount;
	}

	public float getBalance() {
		return balance;
	}


}
