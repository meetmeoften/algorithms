package com.test.libraryManagement;

public class Book {

	private String title;
	private Integer bookId;
	private Integer quantity;
	private String author;
	private String publisher;

	public Book(String bookName, Integer bookId) {
		this.title = bookName;
		this.bookId = bookId;
	}

	public String getBookName() {
		return title;
	}

	public void setBookName(String bookName) {
		this.title = bookName;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

}
