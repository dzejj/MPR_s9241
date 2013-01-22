package com.pl.biblioteka;

import org.apache.log4j.*;

import com.pl.services.*;
import java.util.*;

public class Main {

	public static void cleanDB(BookDBManager BookMng,
		StudentDBManager CustMng, BookStudentDBManager LnkdMng) {
		LnkdMng.deleteAllLendings();
		BookMng.deleteAllBooks();
		CustMng.deleteAllStudents();
	}

//	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		PropertyConfigurator
				.configure("src/resources/java/com/pl/reso/Log4j.properties");

		Student Student1 = new Student("Wacław", "Wacławski");
		Student Student2 = new Student("Basia", "Skarbek");

		List<Student> StudentList = new ArrayList<Student>();
		StudentList.add(Student1);
		StudentList.add(Student2);

		Book Biblia = new Book("Nowy Testament", "God");
		Book Elementarz = new Book("Elementarz", "Pan Obok");
		Book Podrecznik = new Book("Do Matematyki", "Soviet");
		List<Book> BookList = new ArrayList<Book>();
		BookList.add(Podrecznik);
		BookList.add(Elementarz);
		BookList.add(Biblia);
		// ----------------------------------------------------------------------------------------------------

		StudentDBManager StudentManager = new StudentDBManager();

		StudentManager.addStudent(Student1);
		StudentManager.addStudent(Student2);
		StudentManager.addStudent(new Student("Mariusz", "Michalski"));
		StudentManager.addStudent(new Student("Anita", "Wlodaracz"));
		System.out.println("\n Wszyscy Studenci: \n");
		for (Student student : StudentManager.getAllStudents()) {
			System.out.println("Name: " + student.getName() + " Surname: " + student.getSurname());
		}
		/*
		 * !USUWANIE!
		 * StudentManager.deleteStudent(StudentManager.findStudentByName
		 * ("Inny")); for (Student Student :
		 * StudentManager.getAllStudents()) {
		 * System.out.println(student.getName()); }
		 */

		BookDBManager BookManager = new BookDBManager();

		BookManager.addBook(Biblia);
		BookManager.addBook(Elementarz);
		BookManager.addBook(Podrecznik);
		System.out.println("\n Wszystkie Książki: \n" );
		for (Book book : BookManager.getAllBooks()) {
			System.out.println("Name: " + book.getName() + "\nAuthor: "
					+ book.getAuthor() + "\n");
		}
		
		// ----------------------------------------------------------------------------------------------------

		BookStudentDBManager LinkedManager = new BookStudentDBManager();
		List<Integer> StudentID = StudentManager.findStudentByName("Jakis");
		List<Integer> BookID = BookManager.findBookByName("Elementarz");
		
		for(Integer ID : StudentID ){
			System.out.println("Student: " + ID);
		}
		for(Integer ID : BookID ){
			System.out.println("Book: " + ID);
		}
		
		LinkedManager.LendBookToStudent(StudentID, BookID);
		
		for (Book book : LinkedManager.getStudentBook(StudentManager
				.findStudentByName("Jakis"))) {
			System.out.println("\nName: " + book.getName() + "\nAuthor: "+ book.getAuthor());
		}

		// -warunki-do-klas-anonimowych-------------------------------------------------------------------------
		System.out.println("Tytul dluzszy niz 25 znakow");
		BookManager.printBookWithCondition(BookManager.getAllBooks(),
				new Condition() {
					@Override
					public boolean getCondition(Book book) {
						if (book.getName().length() > 25)
							return true;
						return false;
					}

					@Override
					public boolean getCondition(Student student) {
						return false;
					}
				});
		System.out.println("Imie dluzsze niz 4 znaki");
		StudentManager.printStudentWithCondition(
				StudentManager.getAllStudents(), new Condition() {
					@Override
					public boolean getCondition(Book book) {
						return false;
					}

					@Override
					public boolean getCondition(Student student) {
						if (student.getName().length() > 4)
							return true;
						return false;
					}
				});
		
		
	}
}