package es.florida.AD_AE02;

import java.awt.LinearGradientPaint;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Model {

	private String fitxer_lectura;
	private String fitxer_escritura;

	Model() {
		fitxer_lectura = "AE02_T1_2_Streams_Groucho.txt";
		fitxer_escritura = "AE02_T1_2_Streams_Groucho_copia.txt";
	}
	
	// Métode: contingutFitxer
	// Descripció métode: Llig el fitxerLectura, que es AE02_T1_2_Streams_Groucho.txt, i retorna el contingut del fitxer.
	// Parámetros de entrada: String fitxer pasat per parámetre
	// Parámetros de salida: Return contingutFitxer
	public ArrayList<String> contingutFitxer(String fitxer) {
		ArrayList<String> contingutFitxer = new ArrayList<String>();
		File f = new File(fitxer);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				contingutFitxer.add(linea);
				linea = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return contingutFitxer;
	}
	
	// Métode: fitxerEscritura
	// Descripció métode: Retorna el fitxer de escritura, que es AE02_T1_2_Streams_Groucho_copia.txt 
	// Parámetros de entrada: No
	// Parámetros de salida: Return fitxer_escritura
	public String fitxerEscritura() {
		return fitxer_escritura;
	}

	// Métode: fitxerLectura
    // Descripció métode: Retorna el fitxer de lectura, que es AE02_T1_2_Streams_Groucho.txt 
    // Parámetros de entrada: No
    // Parámetros de salida: Return fitxer_escritura
	public String fitxerLectura() {
		return fitxer_lectura;
	}

	// Métode: buscarText
    // Descripció métode: Llig el fitxer de lectura, fa una busqueda de una paraula i mostra quantes vegades apareix la paraula buscada en el fitxer en un missatge d'informació
    // Parámetros de entrada: String buscarText pasat per parámetre
    // Parámetros de salida: No
	public void buscarText(String buscarText) {
		File f = new File(fitxerLectura());
		try {
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			int contador = 0;
			int i = 0;
		
//			while (linea != null) {
				//int indice = -1;
//				while (linea.indexOf(buscarText, indice) != -1) {
//					indice = contador++;				
//				}
//				linea = br.readLine();
//			}
			
//			String[] palabras = null; 
//			while (linea != null) {
//				palabras = linea.split(" ");
//				for (String palabra : palabras) {
//					if(palabra.equals(buscarText)) {
//						contador++;
//					}
//				}
//				linea = br.readLine();
//			}
			
			String[] palabras = null; 
			while (linea != null) {
				palabras = linea.split(" ");
				for (String palabra : palabras) {
					Pattern pattern = Pattern.compile("\\b"+buscarText+"\\b");
					Matcher matcher = pattern.matcher(palabra);
					if(matcher.find()) {
						contador++;
					}
				}
				linea = br.readLine();
			}
			
			String vegades = (contador < 2) ? "vegada" : "vegades";

			JOptionPane.showMessageDialog(new JFrame(),
					"La paraula " + buscarText + " s'ha trobat " + contador + " " + vegades + " en el fitxer", linea,
					JOptionPane.INFORMATION_MESSAGE);

			br.close();
			fr.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Métode: reemplaçarText
    // Descripció métode: Llig el fitxer de lectura, reemplaça una paraula pasada per parámetre per la paraula que d'ha buscat i fa una copia del contigut amb la paraula ja reemplaçada en un altre fitxer i mostra el text amb la paraula reemplaçada en el textArea de baix
    // Parámetros de entrada: String buscarText i reemplaçarText pasat per parámetre
    // Parámetros de salida: No
	public void reemplaçarText(String buscarText, String reemplaçarText) {
		File fitxerOrigen = new File(fitxerLectura());
		File fitxerCopia = new File(fitxerEscritura());
		try {
			FileReader fr = new FileReader(fitxerOrigen);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(fitxerCopia);
			BufferedWriter bw = new BufferedWriter(fw);
			String linea = br.readLine();
			
			while (linea != null) {
				//System.out.print(buscarText);
				// Aquesta linea te reemplaça de la paraula buscada totes les paraules del text que coinciden per la paraula especificada per a reemplaçar, incluides les paraules que contenen la paraula que es va a reemplaçar
				//linea = linea.replaceAll(buscarText, reemplaçarText);
				
				// Aquesta linea fa el mateix que la de dalt peró amb una diferencia, reemplaça totes les paraules menys les paraules que contenen la paraula que es va a reemplaçar
				linea = linea.replaceAll("\\b"+buscarText+"\\b", reemplaçarText);
				//System.out.print(reemplaçarText);
				bw.write(linea + "\r\n");
				linea = br.readLine();
			}

			br.close();
			bw.close();
			fr.close();
			fw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
