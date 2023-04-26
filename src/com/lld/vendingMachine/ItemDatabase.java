package com.lld.vendingMachine;

import java.util.HashMap;
import java.util.Map;

public class ItemDatabase {


	private Map<String, Item> items;

	public ItemDatabase() {
		items = new HashMap<>();
	}

	public void addItem(Item item) {
		items.put(item.getCode(), item);
	}

	public Item getItem(String code) {
		return items.get(code);
	}
}


