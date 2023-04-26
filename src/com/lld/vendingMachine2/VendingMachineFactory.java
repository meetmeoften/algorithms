package com.lld.vendingMachine2;

public class VendingMachineFactory {

	public static VendingMachine createVendingMachine() {
		return new VendingMachineImpl();
	}
}
