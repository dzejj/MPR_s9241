package com.pl.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Student;


public class CustomerTest {

	List<Book> bookList = new ArrayList<Book>();
	Student klient = new Student("imie", "nazwisko", bookList);
	Book ksiazka = new Book("nazwa", "autor");

	@Before
	public void setUp() throws Exception {
		klient.borrowBook(ksiazka);
	}

	@After
	public void tearDown() throws Exception {
		klient.returnBook(ksiazka);
	}

	@Test
	public void testCustomer() {
		assertTrue(klient.getName().equals("imie"));
		assertTrue(klient.getSurname().equals("nazwisko"));
		assertTrue(klient.getBookList().equals(bookList));
	}

	@Test
	public void testBorrowBook() {
		
		assertTrue(bookList.contains(ksiazka));
	}

	@Test
	public void testReturnBook() {
		klient.returnBook(ksiazka);
		assertFalse(bookList.contains(ksiazka));
	}

	@Test
	public void testHasBook() {
		klient.borrowBook(ksiazka);
		assertEquals(ksiazka, klient.hasBook(ksiazka));
	}

	@Test
	public void testFindAllBooksByName() {
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		klient.renameAllBooksByName(bookList, "nowaNazwa");
		assertSame("nowaNazwa", ksiazka.getName());
	}
/*
	@Test
	public void testFindAllBooksByAuthor() {
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		assertSame(bookList, klient.findAllBooksByAuthor("autor"));
	}

	@Test
	public void testRenameAllBooksByNameListOfBookString() {
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		assertSame(bookList, klient.findAllBooksByName("nazwa"));
	}

	@Test
	public void testRenameAllBooksByAuthorListOfBookString() {
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		klient.borrowBook(ksiazka);
		klient.renameAllBooksByAuthor(bookList, "nowyAutor");
		assertSame("nowyAutor", ksiazka.getAuthor());
	}

	@Test
	public void testRemoveAllBooksByList() {
		klient.removeAllBooksByList(bookList);
		assertNull(klient.hasBook(ksiazka));
	}
*/
	@Test
	public void testGetName() {
		assertSame("imie", klient.getName());
	}

	@Test
	public void testSetName() {
		klient.setName("noweImie");
		assertSame("noweImie", klient.getName());
	}

	@Test
	public void testGetSurname() {
		assertSame("nazwisko", klient.getSurname());
	}

	@Test
	public void testSetSurname() {
		klient.setSurname("noweNazwisko");
		assertSame("noweNazwisko", klient.getSurname());
	}

}
