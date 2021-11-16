package es.florida.ejtema1.Seccio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exercici30 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicis_seccio4", "root", "");
			System.out.println("S'ha pogut fer la conexió correctament, amb la base de dades especificada \n");
			
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM pelicules";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.format("%3s%30s%20s%15s%20s%2s", "Id", "Titol", "Director", "Puntuació", "Género","\n");
			System.out.format("%3s%30s%20s%15s%20s%2s", "==", "=====", "========", "=========", "======","\n");
			while (rs.next()) {
				//System.out.println("Id: " + rs.getString(1) + " - Titol: " + rs.getString(2) + " - Director: " + rs.getString(3) + " - Puntuació: " + rs.getString(4) + " - Génere: " + rs.getString(5));
				System.out.format("%3s%30s%20s%15s%20s%2s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),"\n");
			}
			
			System.out.print("Introdueix un id per a modificar les dades: ");
			Scanner sc = new Scanner(System.in);
			String idActualitzar = sc.nextLine();
		
			System.out.print("Introdueix el titol: "); 
			String actualitzarTitol = sc.nextLine();
				
			System.out.print("Introdueix el director: "); 
			String actualitzarDirector = sc.nextLine();
			
			System.out.print("Introdueix la puntuació: "); 
			String actualitzarPuntuacio = sc.nextLine();
				
			System.out.print("Introdueix el génere: "); 
			String actualitzarGenere = sc.nextLine();
				
			PreparedStatement psActualitzar = con.prepareStatement("UPDATE pelicules SET Titol = '" + actualitzarTitol + "', Director = '" + actualitzarDirector + "', Puntuacio = '" + actualitzarPuntuacio  + "', Genere = '" + actualitzarGenere + "' WHERE Id = " + idActualitzar);
			
			int resultatActualitzar = 0;
			resultatActualitzar = psActualitzar.executeUpdate();
			if(resultatActualitzar > 0) {
				// Preguntar resultatInsertar -> fila 1
				Pelicula novaPelicula = new Pelicula(actualitzarTitol, actualitzarDirector, actualitzarPuntuacio, actualitzarGenere);
				System.out.println("Pelicula actualitzada en la base de dades (fila " + resultatActualitzar + "): " + novaPelicula.toString());
			}else {
				System.out.println("Error, no s'ha pogut fer la actualització de dades en la base de dades");
			}

			psActualitzar.close();
			sc.close();
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			System.err.println("Error!, no s'ha pogut fer la conexió amb la base de dades especificada");
			e.printStackTrace();
		}
	}

}
