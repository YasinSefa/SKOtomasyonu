package Helper;

import java.sql.DriverManager;

import java.sql.Connection;


import java.sql.SQLException;

public class DBConnection {
	Connection c = null;
	
	public DBConnection() {}
	
	public Connection connDb() {
		try {
			this.c=DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool?useSSL=true", "yasin", "12345");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
