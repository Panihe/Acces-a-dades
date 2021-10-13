package es.florida.ejtema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercici10 {
	
	public static void crearFichero() {
        File fichero = new File("exercici10.txt");

        try {
            if(!fichero.exists()) {
                System.out.println("El fitxer especificat encara no existeix en el directori.");
                System.out.println("Es va a crear el fitxer.");

                if(fichero.createNewFile()) {
                    System.out.println("El fitxer s'ha creat correctament");
                }

            }else {
            System.out.println("El fitxer especificat ya existeix en el directori");
            }
        }catch(IOException e) {
            System.err.println("El fitxer especificat no s'ha creat");
        }

    }
	
	public static void escribirTexto() {
		File fichero = new File("exercici10.txt");
		
		try {
			FileWriter fw = new FileWriter(fichero);
			fw.write("Hola que tal?");
			fw.write("\r\n"); // Salto de línea
			fw.write("Otro texto más");
			fw.write("\r\n"); // Salto de línea
			fw.close();
		}catch (IOException e) {
			System.out.print("Error, no s'ha pogut escriure ningú text en el fitxer");
		}
	}
	
	public static void introducirTexto() {
		File fichero = new File("exercici10.txt");
		
		try {
			FileWriter fw = new FileWriter(fichero, true);
			fw.write("Bien");
			fw.write("\r\n"); // Salto de línea
			fw.write("¿Y tú?");
			fw.write("\r\n"); // Salto de línea
			fw.write("Mal");
			fw.close();
		}catch (IOException e) {
			System.out.print("Error, no s'ha pogut introduir ningú text en el fitxer");
		}
	}
	
	public static void leerTexto() throws InterruptedException {
        File fichero = new File("exercici10.txt");
        try {
        	
            Scanner sc = new Scanner(System.in);
        	
        	System.out.print("A quina velocitat vols que es mostren els caracters dels fitxer: ");
        	int velocitatMostrarCaracter = Integer.parseInt(sc.nextLine());
        	
        	System.out.println("El contingut del fitxer es: ");
        	
//            FileReader fr = new FileReader(fichero);
//            BufferedReader br = new BufferedReader(fr);
//
//            int numChar;
//            while((numChar = br.read()) != -1) {
//              System.out.println(""+(char)numChar);
//              Thread.sleep(velocitatMostrarCaracter);
//            }
//            
//            fr.close();
//            br.close();
            
            FileReader fr = new FileReader(fichero);
            //BufferedReader br = new BufferedReader(fr);
  
            int numChar = fr.read();
            while(numChar != -1) {
              System.out.print((char)numChar);
              numChar = fr.read();
              Thread.sleep(velocitatMostrarCaracter);
            }
            
            fr.close();
            //br.close();

        }catch (FileNotFoundException ffe) {
            System.err.println("Error, "+ ffe);
        } catch (IOException ioe) {

            System.err.println("Error, "+ ioe);
        }
    }
	
	public static void main(String[] args) throws InterruptedException {
		crearFichero();
		escribirTexto();
		introducirTexto();
		leerTexto();
	}
	
	

}
