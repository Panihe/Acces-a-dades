package es.florida.ejtema1.Seccio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercici27 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicis_seccio4", "root", "");
			System.out.println("S'ha pogut fer la conexió correctament, amb la base de dades especificada");
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM pelicules";
			ResultSet rs = stmt.executeQuery(sql);
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error!, no s'ha pogut fer la conexió amb la base de dades especificada");
			e.printStackTrace();
		}
	}

}
