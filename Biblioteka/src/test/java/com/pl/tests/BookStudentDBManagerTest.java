package com.pl.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Student;
import com.pl.services.BookStudentDBManager;
import com.pl.services.BookDBManager;
import com.pl.services.StudentDBManager;

public class BookStudentDBManagerTest {

	BookDBManager BookManager = new BookDBManager();
	StudentDBManager StudentManager = new StudentDBManager();
	BookStudentDBManager LinkedManager = new BookStudentDBManager();

	@Before
	public void setUp() throws Exception {
		BookManager.addBook(new Book("Tytul1", "Autor1"));
		BookManager.addBook(new Book("Tytul2", "Autor2"));
		BookManager.addBook(new Book("Tytul3", "Autor2"));

		StudentManager.addStudent(new Student("Imie1", "Nazwisko1"));
		StudentManager.addStudent(new Student("Imie2", "Nazwisko2"));
		LinkedManager.LendBookToStudent(
				StudentManager.findStudentByName("Imie1"),
				BookManager.findBookByAuthor("Autor2"));
	}

	@After
	public void tearDown() throws Exception {
		LinkedManager.deleteAllLendings();
		BookManager.deleteAllBooks();
		StudentManager.deleteAllStudents();
	}
	
	@Test
	public void testLendBookToStudent() {
		BookManager.addBook(new Book("Tytul4","Autor1"));
		LinkedManager.LendBookToStudent(StudentManager.findStudentByName("Imie1"), BookManager.findBookByName("Tytul4"));
		assertEquals(3, LinkedManager.getStudentBook(StudentManager.findStudentByName("Imie1")).size());
	}

	@Test
	public void testDeleteStudentLendings() {
		assertEquals(2, LinkedManager.getStudentBook(StudentManager.findStudentByName("Imie1")).size());
		LinkedManager.deleteStudentLendings(StudentManager.findStudentByName("Imie1"));
		assertEquals(0, LinkedManager.getStudentBook(StudentManager.findStudentByName("Imie1")).size());
	}

	@Test
	public void testDeleteAllLendings() {
		LinkedManager.deleteAllLendings();
		assertTrue(LinkedManager.getStudentBook(StudentManager.findStudentByName("Imie1")).isEmpty());
	}

	@Test
	public void testGetStudentBook() {
		assertEquals(2, LinkedManager.getStudentBook(StudentManager.findStudentByName("Imie1")).size());
	}

}
