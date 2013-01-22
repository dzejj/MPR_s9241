package com.pl.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.pl.biblioteka.*;

public class BookStudentDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement LendBookToStudentStmt;
	private PreparedStatement deleteAllLendingsStmt;
	private PreparedStatement deleteStudentLendingsStmt;
	private PreparedStatement getStudentBookStmt;

	public BookStudentDBManager() {
		try {
			Properties props = new Properties();

			try {
				props.load(ClassLoader
						.getSystemResourceAsStream("com/pl/reso/jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			conn = DriverManager.getConnection(props.getProperty("url"));

			stmt = conn.createStatement();
			boolean StudentBookTableExists = false;

			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);

			while (rs.next()) {
				if ("StudentBook".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					StudentBookTableExists = true;
					break;
				}
			}

			if (!StudentBookTableExists) {
				stmt.executeUpdate("CREATE TABLE StudentBook(student_id bigint, book_id bigint, CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student (id),"
						+ " CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES books (id))");
			}

			LendBookToStudentStmt = conn
					.prepareStatement("INSERT INTO StudentBook (student_id, book_id) VALUES (?, ?)");

			deleteStudentLendingsStmt = conn
					.prepareStatement("DELETE FROM StudentBook WHERE student_id = ?");

			deleteAllLendingsStmt = conn
					.prepareStatement("DELETE FROM StudentBook");

			getStudentBookStmt = conn
					.prepareStatement("SELECT books.name, books.author FROM books,"
							+ " StudentBook WHERE student_id = ? and book_id = books.id");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void LendBookToStudent(List<Integer> StudentList,
			List<Integer> BookList) {
		try {
			for (Integer studentID : StudentList) {
				for (Integer bookID : BookList) {
					LendBookToStudentStmt.setInt(1, studentID);
					LendBookToStudentStmt.setInt(2, bookID);
					LendBookToStudentStmt.executeUpdate();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void LendBookToStudentV2(Integer studentID, Integer bookID) {
		try {
			LendBookToStudentStmt.setInt(1, studentID);
			LendBookToStudentStmt.setInt(2, bookID);
			LendBookToStudentStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteStudentLendings(List<Integer> StudentList) {
		try {
			for (Integer studentID : StudentList) {
				deleteStudentLendingsStmt.setInt(1, studentID);
				deleteStudentLendingsStmt.executeUpdate();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteAllLendings() {
		try {
			deleteAllLendingsStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Book> getStudentBook(List<Integer> StudentList) {
		List<Book> BookList = new ArrayList<Book>();
		try {
			for (Integer studentID : StudentList) {
				getStudentBookStmt.setInt(1, studentID);
				ResultSet rs = getStudentBookStmt.executeQuery();
				while (rs.next()) {
					BookList.add(new Book(rs.getString("name"), rs
							.getString("author")));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return BookList;
	}

}