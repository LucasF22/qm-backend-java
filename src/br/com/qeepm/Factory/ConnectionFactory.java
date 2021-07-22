package br.com.qeepm.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnetion(){
		 try {
			 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/backEnd", "postgres", "123456789");
			 System.out.println("Conectado!");
		 }catch (SQLException e) {
			 
			 throw new RuntimeException(e);
		 }
		return null;
	}
}
