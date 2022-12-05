package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
//import control.DatabaseConnection;

public class DatabaseConnection {
	static DatabaseConnection databaseConnection;
	private Statement statement;

	private DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/maildatabase", "root",
					"Ranjithkumar@1705");
			statement = connect.createStatement();

		} catch (Exception e) {
			System.out.println("Database Not Connected ! ");
			e.printStackTrace();
		}
	}

	static public Statement getStatement() {
		if (databaseConnection == null) {
			databaseConnection = new DatabaseConnection();
		}
		return databaseConnection.statement;

	}
}
//public class DatabaseConnection {
