package com.pl.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pl.biblioteka.Book;
import com.pl.biblioteka.Student;
import com.pl.services.StudentDBManager;

public class StudentDBManagerTest {

	StudentDBManager StudentManager = new StudentDBManager();
	@Before
	public void setUp() throws Exception {
		StudentManager.addStudent(new Student("Imie", "Nazwisko"));
	}

	@After
	public void tearDown() throws Exception {
		StudentManager.deleteAllStudents();
	}

	@Test
	public void testAddStudent() {
		StudentManager.addStudent(new Student("Imie2","Nazwisko2"));
		assertEquals(2, StudentManager.getAllStudents().size());
	}

	@Test
	public void testGetAllStudents() {
		assertEquals(1, StudentManager.getAllStudents().size());
	}

	@Test
	public void testDeleteAllStudents() {
		StudentManager.deleteAllStudents();
		assertTrue(StudentManager.getAllStudents().isEmpty());
	}

	@Test
	public void testFindStudentByName() {
		StudentManager.addStudent(new Student("Imie","Nazwisko2"));
		assertEquals(2, StudentManager.findStudentByName("Imie").size());
	}

	@Test
	public void testFindStudentBySurname() {
		StudentManager.addStudent(new Student("Imie2","Nazwisko"));
		StudentManager.addStudent(new Student("Imie2","Nazwisko2"));
		assertEquals(2, StudentManager.findStudentBySurname("Nazwisko").size());
	}

	@Test
	public void testDeleteStudent() {
		StudentManager.addStudent(new Student("Imie2","Nazwisko"));
		StudentManager.addStudent(new Student("Imie2","Nazwisko2"));
		StudentManager.deleteStudent(StudentManager.findStudentByName("Imie"));
		assertEquals(2, StudentManager.getAllStudents().size());
	}

}
