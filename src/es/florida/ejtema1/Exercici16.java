package es.florida.ejtema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Exercici16 {

	public static void crearFichero() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		File fichero = new File("exercici16_" + dateFormat.format(date) + ".txt");
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
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		File fichero = new File("exercici16_" + dateFormat.format(date) + ".txt");
		try {
			Scanner sc = new Scanner(System.in);

			FileWriter fw = new FileWriter(fichero);
			String text = "";
			do {
				System.out.print("Introdueix un text: ");
				text = sc.nextLine();
				if (!text.equals("exit")) {
					fw.write(text);
					fw.write("\n");
				}
			} while (!text.equals("exit"));

			sc.close();
			fw.close();
		} catch (IOException ex) {
			System.err.println("Error, no s'ha pogut escriure ning� text en el fitxer");
		}

	}

	public static void main(String[] args) {
		crearFichero();
		escribirTexto();
	}

}
