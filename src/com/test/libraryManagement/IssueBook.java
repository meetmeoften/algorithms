package com.test.libraryManagement;

public class IssueBook {

	private Book book;
	private User user;
	Integer issueId;

	public IssueBook(Book book, User user) {
		this.book = book;
		this.user = user;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}




}
