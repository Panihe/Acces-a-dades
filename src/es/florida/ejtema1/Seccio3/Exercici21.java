package es.florida.ejtema1.Seccio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Exercici21 {

	public static void main(String[] args) throws IOException {
		File fitxerClase = new File("./src/es/florida/ejtema1/Seccio3/pelicula.java");
		FileReader fr = new FileReader(fitxerClase);
		BufferedReader br = new BufferedReader(fr);
		String linea = br.readLine();
		while (linea != null) {
			System.out.println(linea);
			linea = br.readLine();
		}
		br.close();
		fr.close();
	}

}
