package es.florida.ejtema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercici13 {

	public static void crearFichero() {
		File fichero = new File("exercici13.txt");

		try {
			if (!fichero.exists()) {
				System.out.println("El fitxer especificar encara no existeix en el directori.");
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
		File fichero = new File("exercici13.txt");

		try {
			FileWriter fw = new FileWriter(fichero);
			fw.write("Aquesta linea pertany al exercici13.txt");
			fw.write("\r\n"); // Salto de línea
			fw.write("Aixó és altre text de exercici13.txt");
			fw.close();
		} catch (IOException e) {
			System.out.print("Error, no s'ha pogut escriure ningú text en el fitxer");
		}
	}

	public static void leerTexto() throws InterruptedException {
		File fichero = new File("exercici13.txt");

		try {
			Scanner sc = new Scanner(System.in);
        	
        	System.out.print("A quina velocitat vols que es mostren les linies del fitxer: ");
        	int velocitatMostrarLinea = Integer.parseInt(sc.nextLine());
        	
			System.out.println("El contingut del fitxer es: ");

			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			
			while (linea != null) {
				System.out.println(linea);
				linea = br.readLine();
				Thread.sleep(velocitatMostrarLinea);
			}
		
			System.out.println("\r\n"); // Salto de línea
			System.out.println("S'ha llegit tot el fitxer");
			
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
		leerTexto();
	}

}
