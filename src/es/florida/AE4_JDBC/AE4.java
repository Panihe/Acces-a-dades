package es.florida.AE4_JDBC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AE4 {

	// Métode: main
	// Descripció métode: Llig el contingut del fitxer AE04_T1_4_JDBC_Dades.csv
		// llinea a llinea i dins de cada una de la llinea talla la llinea per el
		// caracter ";" i guarda el valors tallats per a poder insertalos en la taula
		// llibres de la base de dades.
		// També recorreix el array linea per a comprobar que si hi ha algun camp buit
		// que en la base de dades aparega eixe camp amb el text "N.C".
		// També conta quants registres hi han en la tabla llibres i si el nombre de
		// registres es menor a 1 que fatja el wile de linea != null i inserte les dades
		// a la base de datos.
		// També llig qualsevol consulta que s'escriga per teclat i depenent de quin
		// tipus de consulta (SELECT, INSERT, UPDATE, DELETE) que fatja la execució que
		// estiga definida
	// Parámetros de entrada: String[] args pasat per paràmetres
	// Parámetros de salida: No
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
		File fitxer = new File("AE04_T1_4_JDBC_Dades.csv");
		try {
			FileReader fr = new FileReader(fitxer, StandardCharsets.ISO_8859_1);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			linea = br.readLine();
			String titol = "";
			String autor = "";
			String anyNaixement = "";
			String anyPublicacio = "";
			String editorial = "";
			String nombrePagines = "";

			String sqlNombreFiles = "SELECT COUNT(Id) FROM llibres";
			Statement stmtNombreFiles = con.createStatement();
			ResultSet rsNombreFiles = stmtNombreFiles.executeQuery(sqlNombreFiles);
			int count;
			while (rsNombreFiles.next()) {
				count = rsNombreFiles.getInt(1);

				if (count < 1) {
					while (linea != null) {
						String[] lineas = linea.split(";");
						for (int i = 0; i < lineas.length; i++) {
							if (lineas[i].equals("")) {
								lineas[i] = "N.C";
							} else {
								titol = lineas[0];
								autor = lineas[1];
								anyNaixement = lineas[2];
								anyPublicacio = lineas[3];
								editorial = lineas[4];
								nombrePagines = lineas[5];
							}
						}

						String sqlInsertar = "INSERT INTO llibres (Titol, Autor, Any_naixement, Any_publicacio, Editorial, Nombre_pagines) VALUES (?,?,?,?,?,?)";
						PreparedStatement psInsertar = con.prepareStatement(sqlInsertar);

						psInsertar.setString(1, titol);
						psInsertar.setString(2, autor);
						psInsertar.setString(3, anyNaixement);
						psInsertar.setString(4, anyPublicacio);
						psInsertar.setString(5, editorial);
						psInsertar.setString(6, nombrePagines);
						int resultadoInsertar = psInsertar.executeUpdate();

						linea = br.readLine();
					}
					br.close();
					fr.close();

				}

			}

			System.out.println("La crida s'ha agregat amb exit a la base de dades \n");

			String sqlAnyNaixement = "SELECT Titol, Autor, Any_publicacio FROM llibres WHERE Any_naixement < 1950";
			String sqlEditorial = "SELECT Editorial FROM llibres WHERE Any_publicacio > 2000;";
			Statement stmt = con.createStatement();
			ResultSet rsAnyNaixement = stmt.executeQuery(sqlAnyNaixement);
			while (rsAnyNaixement.next()) {
				System.out.println("Titol: " + rsAnyNaixement.getString(1) + " - Autor: " + rsAnyNaixement.getString(2)
						+ " - Any de publicació: " + rsAnyNaixement.getString(3));
			}

			System.out.println("\n");

			ResultSet rsEditorial = stmt.executeQuery(sqlEditorial);
			while (rsEditorial.next()) {
				System.out.println("Editorial: " + rsEditorial.getString(1));
			}

			/* Ampliació - Qualsevol consulta SQL */

			Scanner sc = new Scanner(System.in);

			System.out.println("\n");
			System.out.print("Introdueix un tipus de consulta (SELECT, INSERT, UPDATE, DELETE): ");
			String sqlTeclat = sc.nextLine();
			StringTokenizer tokens = new StringTokenizer(sqlTeclat); // Obté el primer token que introdueix el usuari
																		// per teclat
			String tipusConsulta = tokens.nextToken().toUpperCase();

			switch (tipusConsulta) {
			case "SELECT":
				Statement stmtSelectTeclat = con.createStatement();
				ResultSet rsSelectTeclat = stmtSelectTeclat.executeQuery(sqlTeclat);
				int nombreFiles = rsSelectTeclat.getMetaData().getColumnCount();
				while (rsSelectTeclat.next()) {
					for (int i = 1; i <= nombreFiles; i++) {
						System.out.print(rsSelectTeclat.getString(i));
						if (i < nombreFiles) {
							System.out.print(" - ");
						}
					}
					System.out.print(" \n ");
				}
				break;
			case "INSERT":
				PreparedStatement psInsertar = con.prepareStatement(sqlTeclat);
				System.out.print("¿Realment desitja insertar el llibre en la base de dade (s/n)? ");
				String confirmacioInsertar = sc.nextLine();
				int resultatInsertar = 0;
				if (confirmacioInsertar.equals("s")) {
					resultatInsertar = psInsertar.executeUpdate();
				}
				if (resultatInsertar > 0) {
					System.out.println("El llibre s'ha anyadit correctament en la base de dades");
				} else {
					System.err.print("Error, no s'ha pogut anyadir el llibre en la base de dades");
				}
				break;
			case "UPDATE":
				PreparedStatement psActualitzar = con.prepareStatement(sqlTeclat);
				System.out.print("¿Realment desitja actualitzar el llibre en la base de dade (s/n)? ");
				String confirmacioActualitzar = sc.nextLine();
				int resultatActualitzar = 0;
				if (confirmacioActualitzar.equals("s")) {
					resultatActualitzar = psActualitzar.executeUpdate();
				}
				if (resultatActualitzar > 0) {
					System.out.println("El llibre s'ha actualitzat correctament en la base de dades");
				} else {
					System.err.print("Error, no s'ha pogut actualitzar el llibre en la base de dades");
				}
				break;
			case "DELETE":
				PreparedStatement psEliminar = con.prepareStatement(sqlTeclat);
				System.out.print("¿Realment desitja eliminar el llibre de la base de dade (s/n)? ");
				String confirmacioEliminar = sc.nextLine();
				int resultatEliminar = 0;
				if (confirmacioEliminar.equals("s")) {
					resultatEliminar = psEliminar.executeUpdate();
				}
				if (resultatEliminar > 0) {
					System.out.println("El llibre s'ha eliminat correctament en la base de dades");
				} else {
					System.err.print("Error, no s'ha pogut eliminar el llibre en la base de dades");
				}
				break;
			}

			rsAnyNaixement.close();
			rsEditorial.close();
			sc.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("Error, La crida no ha pogut ser agregada a la base de dades");
			e.printStackTrace();
		}

	}
}
