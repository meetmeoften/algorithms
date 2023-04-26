package com.algoexpert.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedListUsingArrayList {

	List<Integer> list;

	public MyLinkedListUsingArrayList() {
		list = new ArrayList<>();
	}

	public int get(int index) {
		if (index >= list.size()) {
			return -1;
		}
		return list.get(index);
	}

	public void addAtHead(int val) {
		list.add(0, val);
	}

	public void addAtTail(int val) {
		list.add(list.size(), val);
	}

	public void addAtIndex(int index, int val) {
		if (index <= list.size()) {
			list.add(index, val);
		}
	}

	public void deleteAtIndex(int index) {
		if (index < list.size()) {
			list.remove(index);
		}
	}


	public static void main(String[] args) {
		MyLinkedListUsingArrayList li = new MyLinkedListUsingArrayList();

		li.addAtHead(1);
		li.addAtHead(2);
		li.addAtTail(4);
		li.addAtIndex(2, 3);
		li.get(2);
		li.deleteAtIndex(3);
	}

}
