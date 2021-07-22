package br.com.Piloto.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

	public Connection getConnection() {
		try {
			
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/backEnd", "postgres", "123456789");
			

		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}

}
