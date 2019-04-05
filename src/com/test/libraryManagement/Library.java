package com.test.libraryManagement;

import java.util.ArrayList;
import java.util.List;

public class Library {

	private final List<Book> bookList;
	private final List<User> userList;
	private final List<IssueBook> issueBookList;
	private static Library instance;

	static Object lock = new Object();

	private Library() {
		bookList = new ArrayList<>();
		userList = new ArrayList<>();
		issueBookList =  new ArrayList<>();
	}


	public static Library getInstance() {
		if(instance == null) {
			synchronized (lock) {
				if(instance == null) {
					instance = new Library();
				}
			}
		}
		return instance;
	}

	public void addBooksToLibrary(Book book) {
		bookList.add(book);
	}

	public void addUsersToLibrary(User user) {
		userList.add(user);
	}

	/**
	 * issue book to the user
	 * @param issueBook
	 */

	public void issueBook(User user, Book book) {
		IssueBook issueBook = new IssueBook(book, user);
		issueBookList.add(issueBook);
	}

	public void returnBook(User user, Book book) {
		for(IssueBook issueBook : issueBookList) {
			if(issueBook.getBook().getBookId() == book.getBookId() &&
					issueBook.getUser().getUserId() == issueBook.getUser().getUserId()) {
				issueBookList.remove(issueBook);
			}
		}
	}

	public List<IssueBook> getIssueBookList() {
		return issueBookList;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public List<User> getUserList() {
		return userList;
	}


}
