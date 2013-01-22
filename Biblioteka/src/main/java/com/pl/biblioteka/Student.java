package com.pl.biblioteka;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class Student {

	private static Logger myLogger = Logger.getLogger(Student.class); // ???

	String name;
	String surname;
	List<Book> BookList;

	public Student(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.BookList = new ArrayList<Book>();
	}
	
	public Student(String name, String surname, List<Book> BookList) {
		this.name = name;
		this.surname = surname;
		this.BookList = BookList;
	}

	public void borrowBook(Book k) {
		BookList.add(k);
		myLogger.info("You have borrowed a book by " + k.author + ", called "
				+ k.name);
	}

	public void returnBook(Book k) {
		BookList.remove(k);
		myLogger.info("You have returned a book by " + k.author + ", called "
				+ k.name);
	}

	public Book hasBook(Book k) {
		for (Book q : BookList) {
			if (k.equals(q)) {
				return q;
			}
		}
		return null;
	}

	public void printBookList() {
		for (Book k : BookList)
			k.printBook();
	}

	public List<Book> findAllBooksByName(String name) {
		List<Book> results = new ArrayList<Book>();
		for (Book k : BookList) {
			if (k.getName().equals(name)) {
				results.add(k);
			}
		}
		return results;
	}

	public List<Book> findAllBooksByAuthor(String author) {
		List<Book> results = new ArrayList<Book>();
		for (Book k : BookList) {
			if (k.getAuthor().equals(author)) {
				results.add(k);
			}
		}
		return results;
	}

	public void removeAllBooksByList(List<Book> tmpBookList) {
		for (Book k : BookList) {
			for (Book tmpK : tmpBookList) {
				if (k.equals(tmpK)) {
					BookList.remove(k);
				}
			}

		}
	}

	public void renameAllBooksByName(List<Book> bookList, String newName) {
		for (Book k : bookList) {
			k.setName(newName);
		}
	}

	public void renameAllBooksByAuthor(List<Book> bookList, String newAuthor) {
		for (Book k : bookList) {
			k.setName(newAuthor);
		}
	}

	public void printStudent() {
		System.out.println("\tStudent_");
		System.out
				.println("\tName: " + this.name + " Surname: " + this.surname);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Book> getBookList() {
		return BookList;
	}

	public void setBookList(List<Book> bookList) {
		BookList = bookList;
	}

}
