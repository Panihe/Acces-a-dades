package es.florida.ejtema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercici14 {

	public static void crearFichero() {
		File fichero = new File("exercici14.txt");

		try {
			if (!fichero.exists()) {
				System.out.println("El fitxer especificat encara no existeix en el directori.");
				System.out.println("Es va a crear el fitxer.");

				if (fichero.createNewFile()) {
					System.out.println("El fitxer s'ha creat correctament");
				}

			} else {
				System.out.println("El fitxer especificat ya existeix en el directori");
			}
		} catch (IOException e) {
			System.err.println("El fitxer especificat no s'ha creat");
		}
	}

	public static void escribirTexto() {
		File fichero = new File("exercici14.txt");

		try {
			FileWriter fw = new FileWriter(fichero);
			fw.write("Aquesta linea pertany al exercici14.txt");
			fw.write("\r\n"); // Salto de línea
			fw.write("Aixó és altre text de exercici14.txt");
			fw.write("\r\n"); // Salto de línea
			fw.write("Copiat en els dos fitxers");
			fw.close();
		} catch (IOException e) {
			System.err.print("Error, no s'ha pogut escriure ningú text en el fitxer");
		}
	}
	
	public static void copiarFichero(String ficheroOrigen, String ficheroCopia) {
		try {
			FileReader fr = new FileReader(ficheroOrigen);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(ficheroCopia);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String linea = br.readLine();
			
			while (linea != null) {
				bw.write(linea + "\r\n");
				linea = br.readLine();
			}
			
			br.close();
			bw.close();
			
		} catch (Exception e) {
			
		}
	}
	
	public static void leerTexto() throws InterruptedException {
		File fichero = new File("exercici14.txt");

		try {
			System.out.println("El contingut del fitxer es: ");

			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			
			while (linea != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
		
			fr.close();
			br.close();

		} catch (FileNotFoundException ffe) {
			System.err.println("Error, " + ffe);
		} catch (IOException ioe) {

			System.err.println("Error, " + ioe);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		crearFichero();
		escribirTexto();
		copiarFichero("exercici14.txt", "exercici14_copia.txt");
		leerTexto();
	}

}
