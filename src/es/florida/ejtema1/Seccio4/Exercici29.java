package es.florida.ejtema1.Seccio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import es.florida.ejtema1.Seccio4.Pelicula;

public class Exercici29 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicis_seccio4", "root", "");
			System.out.println("S'ha pogut fer la conexió correctament, amb la base de dades especificada \n");
			String sqlInsertar = "INSERT INTO pelicules (Titol, Director, Puntuacio, Genere) VALUES (?,?,?,?)";
			PreparedStatement psInsertar = con.prepareStatement(sqlInsertar);
			System.out.print("¿Desitja anyadir una nova pelicula (s/n)? ");
			Scanner sc = new Scanner(System.in);
			String resposta = sc.nextLine();
		
			while (resposta.equals("s")) {
				System.out.print("Introdueix el titol: "); 
				String anyadirTitol = sc.nextLine();
				
				System.out.print("Introdueix el director: "); 
				String anyadirDirector = sc.nextLine();
				
				System.out.print("Introdueix la puntuació: "); 
				String anyadirPuntuacio = sc.nextLine();
				
				System.out.print("Introdueix el génere: "); 
				String anyadirGenere = sc.nextLine();
				
				psInsertar.setString(1, anyadirTitol);
				psInsertar.setString(2, anyadirDirector);
				psInsertar.setString(3, anyadirPuntuacio);
				psInsertar.setString(4, anyadirGenere);
				int resultatInsertar = psInsertar.executeUpdate();
				Pelicula novaPelicula = new Pelicula(anyadirTitol, anyadirDirector, anyadirPuntuacio, anyadirGenere);
				if(resultatInsertar > 0) {
					// Preguntar resultatInsertar -> fila 1
					System.out.println("Pelicula guardada en la base de dades (fila " + resultatInsertar + "): " + novaPelicula.toString());
				}else {
					System.out.println("Error, no s'ha pogut fer la inserció de dades en la base de dades");
				}

				System.out.print("¿Desitja anyadir una nova pelicula (s/n)? ");
				resposta = sc.nextLine();
			}
			sc.close();
			
		} catch (SQLException e) {
			System.err.println("Error!, no s'ha pogut fer la conexió amb la base de dades especificada");
			e.printStackTrace();
		}
	}

}
