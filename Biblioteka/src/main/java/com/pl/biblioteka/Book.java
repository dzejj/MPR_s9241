package com.pl.biblioteka;

	public class Book implements IBook{
	
	public String name;
	public String author;
	
	public Book(String name, String author){
		this.name = name;
		this.author = author;
	}
	public Book(BookRepo name, Authors author){
		this.name = name.toString();
		this.author = author.toString();
	}
	
	public void printBook(){
		System.out.println("\t\tBook title: " + this.name +
								"; Author: " + this.author);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}

