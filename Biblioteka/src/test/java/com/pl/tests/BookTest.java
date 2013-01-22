package com.pl.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;


public class BookTest {

	Book ksiazka = new Book("tytul", "autor");
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBookStringString() {
		assertTrue(ksiazka.name.equals("tytul"));
		assertTrue(ksiazka.author.equals("autor"));
	}

	@Test
	public void testGetName() {
		assertTrue(ksiazka.getName().equals("tytul"));
		
	}

	@Test
	public void testSetName() {
		ksiazka.setName("nowyTytul");
		assertTrue(ksiazka.name.equals("nowyTytul"));
	}

	@Test
	public void testGetAuthor() {
		assertTrue(ksiazka.getAuthor().equals("autor"));
	}

	@Test
	public void testSetAuthor() {
		ksiazka.setAuthor("nowyAutor");
		assertTrue(ksiazka.author.equals("nowyAutor"));
	}

}
