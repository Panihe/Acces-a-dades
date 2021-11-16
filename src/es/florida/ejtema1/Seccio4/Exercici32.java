package es.florida.ejtema1.Seccio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Exercici32 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicis_seccio4", "root", "");
			System.out.println("S'ha pogut fer la conexi� correctament, amb la base de dades especificada \n");
			
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM pelicules";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.format("%3s%30s%20s%15s%20s%2s", "Id", "Titol", "Director", "Puntuaci�", "G�nero","\n");
			System.out.format("%3s%30s%20s%15s%20s%2s", "==", "=====", "========", "=========", "======","\n");
			while (rs.next()) {
				//System.out.println("Id: " + rs.getString(1) + " - Titol: " + rs.getString(2) + " - Director: " + rs.getString(3) + " - Puntuaci�: " + rs.getString(4) + " - G�nere: " + rs.getString(5));
				System.out.format("%3s%30s%20s%15s%20s%2s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),"\n");
			}
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("�Que vols fer (actualitzar o eliminar)? ");
			String resposta = sc.nextLine().toLowerCase();
			
			if(resposta.equals("actualitzar")) {
				System.out.print("Introdueix un id per a actualitzar les dades: ");
				
				String idActualitzar = sc.nextLine();
			
				System.out.print("Introdueix el titol: "); 
				String actualitzarTitol = sc.nextLine();
					
				System.out.print("Introdueix el director: "); 
				String actualitzarDirector = sc.nextLine();
				
				System.out.print("Introdueix la puntuaci�: "); 
				String actualitzarPuntuacio = sc.nextLine();
					
				System.out.print("Introdueix el g�nere: "); 
				String actualitzarGenere = sc.nextLine();
					
				PreparedStatement psActualitzar = con.prepareStatement("UPDATE pelicules SET Titol = '" + actualitzarTitol + "', Director = '" + actualitzarDirector + "', Puntuacio = '" + actualitzarPuntuacio  + "', Genere = '" + actualitzarGenere + "' WHERE Id = " + idActualitzar);
				
				System.out.print("�Realment desitja actualitzar la pelicula en la base de dade (s/n)? ");
				String confirmacioActualitzar = sc.nextLine();
				int resultatActualitzar = 0;
				if(confirmacioActualitzar.equals("s")) {
					resultatActualitzar = psActualitzar.executeUpdate();
				}
				if(resultatActualitzar > 0) {
					// Preguntar resultatInsertar -> fila 1
					Pelicula novaPelicula = new Pelicula(actualitzarTitol, actualitzarDirector, actualitzarPuntuacio, actualitzarGenere);
					System.out.println("Pelicula actualitzada en la base de dades (fila " + resultatActualitzar + "): " + novaPelicula.toString());
				}else {
					System.out.println("Error, no s'ha pogut fer la actualitzaci� de dades en la base de dades");
				}
				
				psActualitzar.close();
			}else if(resposta.equals("eliminar")) {
				System.out.print("Introdueix un id per a eliminar les dades: ");
				String idEliminar = sc.nextLine();
				PreparedStatement psEliminar = con.prepareStatement("DELETE FROM pelicules WHERE Id = " + idEliminar);
				
				System.out.print("�Realment desitja eliminar la pelicula en la base de dade (s/n)? ");
				String confirmacioEliminar = sc.nextLine();
				int resultatEliminar = 0;
				if(confirmacioEliminar.equals("s")) {
					resultatEliminar = psEliminar.executeUpdate();
				}
				if(resultatEliminar > 0) {
					// Preguntar resultatInsertar -> fila 1
					System.out.println("Pelicula eliminada de la base de dades (fila " + idEliminar + ")");
				}else {
					System.out.println("Error, no s'ha pogut eliminarla pelicula de la base de dades");
				}
				
				
				psEliminar.close();
			}else {
				System.err.println("Error, text no v�lid");
			}
		
			sc.close();
			rs.close();
			con.close();
			
		} catch (SQLException e) {
			System.err.println("Error!, no s'ha pogut fer la conexi� amb la base de dades especificada");
			e.printStackTrace();
		}
	}
}
