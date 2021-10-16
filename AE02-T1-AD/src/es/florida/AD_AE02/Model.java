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
	
	// M�tode: contingutFitxer
	// Descripci� m�tode: Llig el fitxerLectura, que es AE02_T1_2_Streams_Groucho.txt, i retorna el contingut del fitxer.
	// Par�metros de entrada: String fitxer pasat per par�metre
	// Par�metros de salida: Return contingutFitxer
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
	
	// M�tode: fitxerEscritura
	// Descripci� m�tode: Retorna el fitxer de escritura, que es AE02_T1_2_Streams_Groucho_copia.txt 
	// Par�metros de entrada: No
	// Par�metros de salida: Return fitxer_escritura
	public String fitxerEscritura() {
		return fitxer_escritura;
	}

	// M�tode: fitxerLectura
    // Descripci� m�tode: Retorna el fitxer de lectura, que es AE02_T1_2_Streams_Groucho.txt 
    // Par�metros de entrada: No
    // Par�metros de salida: Return fitxer_escritura
	public String fitxerLectura() {
		return fitxer_lectura;
	}

	// M�tode: buscarText
    // Descripci� m�tode: Llig el fitxer de lectura, fa una busqueda de una paraula i mostra quantes vegades apareix la paraula buscada en el fitxer en un missatge d'informaci�
    // Par�metros de entrada: String buscarText pasat per par�metre
    // Par�metros de salida: No
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

	// M�tode: reempla�arText
    // Descripci� m�tode: Llig el fitxer de lectura, reempla�a una paraula pasada per par�metre per la paraula que d'ha buscat i fa una copia del contigut amb la paraula ja reempla�ada en un altre fitxer i mostra el text amb la paraula reempla�ada en el textArea de baix
    // Par�metros de entrada: String buscarText i reempla�arText pasat per par�metre
    // Par�metros de salida: No
	public void reempla�arText(String buscarText, String reempla�arText) {
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
				// Aquesta linea te reempla�a de la paraula buscada totes les paraules del text que coinciden per la paraula especificada per a reempla�ar, incluides les paraules que contenen la paraula que es va a reempla�ar
				//linea = linea.replaceAll(buscarText, reempla�arText);
				
				// Aquesta linea fa el mateix que la de dalt per� amb una diferencia, reempla�a totes les paraules menys les paraules que contenen la paraula que es va a reempla�ar
				linea = linea.replaceAll("\\b"+buscarText+"\\b", reempla�arText);
				//System.out.print(reempla�arText);
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
