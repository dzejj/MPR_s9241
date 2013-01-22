package com.pl.tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Student;
import com.pl.services.BookDBManager;

public class BookDBManagerTest {
	
	BookDBManager BookManager = new BookDBManager();
	
	@Before
	public void setUp() throws Exception {
		BookManager.addBook(new Book("Tytul","Autor"));
	}

	@After
	public void tearDown() throws Exception {
		BookManager.deleteAllBooks();
	}

	@Test
	public void testAddBook() {
		BookManager.addBook(new Book("Autor_Tytulu","Tytuł_Autora"));
		assertEquals(2, BookManager.getAllBooks().size());
	}

	@Test
	public void testGetAllBooks() {
		assertEquals(1, BookManager.getAllBooks().size());
	}

	@Test
	public void testDeleteAllBooks() {
		BookManager.deleteAllBooks();
		assertTrue(BookManager.getAllBooks().isEmpty());
	}

	@Test
	public void testFindBookByName() {
		BookManager.addBook(new Book("Tytul","Autor2"));
		assertEquals(2, BookManager.findBookByName("Tytul").size());
	}

	@Test
	public void testFindBookByAuthor() {
		BookManager.addBook(new Book("Tytul2","Autor"));
		BookManager.addBook(new Book("Tytul2","Autor2"));
		assertEquals(2, BookManager.findBookByAuthor("Autor").size());
	}

	@Test
	public void testDeleteBook() {
		BookManager.addBook(new Book("Tytul2","Autor"));
		BookManager.addBook(new Book("Tytul2","Autor2"));
		BookManager.deleteBook(BookManager.findBookByName("Tytul"));
		assertEquals(2, BookManager.getAllBooks().size());
	}

}
