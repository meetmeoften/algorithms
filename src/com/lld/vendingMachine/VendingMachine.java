package com.lld.vendingMachine;

import com.lld.vendingMachine.Dispenser.Display;
import com.lld.vendingMachine.PaymentAcceptor.CardReader;
import com.lld.vendingMachine.PaymentAcceptor.CoinAcceptor;

public class VendingMachine {

	private ItemDatabase itemDatabase;
	private PaymentProcessor paymentProcessor;
	private Dispenser dispenser;
	private Display display;
	private CoinAcceptor coinAcceptor;
	private CardReader cardReader;

	public VendingMachine() {
		itemDatabase = new ItemDatabase();
		paymentProcessor = new PaymentProcessor();
		dispenser = new Dispenser();
		// display = new Display();
		// coinAcceptor = new CoinAcceptor();
		// cardReader = new CardReader();
	}

	public void addItem(Item item) {
		itemDatabase.addItem(item);
	}

	public void selectItem(String itemCode) {
		Item item = itemDatabase.getItem(itemCode);
		if (item == null) {
			display.displayMessage("Invalid item code");
			return;
		}
		if (item.getQuantity() == 0) {
			display.displayMessage("Item out of stock");
			return;
		}
		display.displayPrice(item.getPrice());
	}

	public void insertCoin(Coin coin) {
		paymentProcessor.addPayment(coin);
		display.displayBalance(paymentProcessor.getBalance());
	}

	public void insertCard(Card card) {
		paymentProcessor.addPayment(card);
		display.displayBalance(paymentProcessor.getBalance());
	}

	public void cancelTransaction() {
		paymentProcessor.cancelTransaction();
		display.displayMessage("Transaction canceled");
	}

	public void completeTransaction(String itemCode) {
		Item item = itemDatabase.getItem(itemCode);
		if (item == null) {
			display.displayMessage("Invalid item code");
			return;
		}
		if (item.getQuantity() == 0) {
			display.displayMessage("Item out of stock");
			return;
		}
		if (paymentProcessor.getBalance() < item.getPrice()) {
			display.displayMessage("Insufficient funds");
			return;
		}
		paymentProcessor.processPayment(item.getPrice());
		// item.decrementQuantity();
		dispenser.dispenseItem(item);
		display.displayMessage("Transaction complete");
	}
}
