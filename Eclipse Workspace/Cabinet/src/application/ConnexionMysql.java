package application;

import java.sql.*;

public class ConnexionMysql {
	
	
	public Connection cn=null;
	public static Connection connexionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical?characterEncoding=latin1","root","");
			System.out.println("connection established");
			return cn;
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("connexion echouée");
			e.printStackTrace();
			return null;
		}
	}

	
}
