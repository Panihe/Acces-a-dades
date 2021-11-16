package es.florida.ejtema1.Seccio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Exercici18 {

	public static void main(String[] args) {
		File fitxer = new File("pelicules.xml");
		try {
			FileReader fr = new FileReader(fitxer, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while(linea != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
			br.close();
			fr.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		

	}

}
