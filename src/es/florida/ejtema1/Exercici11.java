package es.florida.ejtema1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercici11 {

	public static void crearFichero() {
		File fichero = new File("exercici11.txt");

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
		File fichero = new File("exercici11.txt");

		try {
			FileWriter fw = new FileWriter(fichero);
			fw.write(
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eros dui, sollicitudin vel arcu nec, faucibus viverra quam. Vestibulum nec lectus arcu. Vivamus ac ligula sit amet augue pulvinar blandit. Vivamus id dolor tempor, faucibus nulla et, auctor arcu. Pellentesque nec nibh vitae metus gravida feugiat a eget urna. Nullam rutrum sollicitudin lorem non porttitor. Etiam porta mauris dolor, faucibus malesuada quam mattis eget. Nunc vestibulum sapien purus, quis condimentum magna fringilla sit amet. Cras ut velit consequat, scelerisque purus mattis, hendrerit sapien. Aliquam erat volutpat.\r\n"
							+ "\r\n"
							+ "Quisque pellentesque est et quam tempor, sed mattis purus rhoncus. Curabitur elementum porta urna, in eleifend mauris lacinia non. Ut at gravida ligula, in volutpat quam. Aenean rutrum magna eu lectus blandit, id maximus magna mattis. Nulla semper in quam at hendrerit. Quisque pretium, eros in blandit gravida, ante libero pulvinar metus, condimentum ultricies enim tortor vitae arcu. Mauris quis turpis lacinia, congue libero et, dictum lacus. Morbi at lectus id libero condimentum congue in nec turpis. Donec nisi risus, scelerisque nec sollicitudin in, dictum ac magna. Pellentesque semper augue et sapien tempor imperdiet.\r\n"
							+ "\r\n"
							+ "Etiam vitae magna auctor, pretium ligula et, scelerisque ex. Phasellus luctus nulla in velit gravida, at lobortis est ultrices. Nulla tempus tincidunt massa vitae tristique. Etiam a aliquam libero. Cras pharetra viverra magna, facilisis gravida velit ultrices sit amet. Ut iaculis mi sed metus malesuada, eu varius nibh tempor. Maecenas porta pulvinar metus in ullamcorper. Maecenas egestas mi a ex rutrum, eu consequat sapien auctor. Curabitur et ex consectetur, interdum lectus nec, faucibus leo. Aenean nec sapien dignissim, tempor nibh ac, volutpat eros. Quisque molestie odio sed velit tristique dictum. Etiam vel mauris imperdiet, cursus dui ullamcorper, dictum ipsum. Etiam non tincidunt ex, eu tristique quam. Integer sed imperdiet eros. Donec imperdiet lobortis accumsan. Curabitur eu risus quis nisl");
			fw.close();
		} catch (IOException e) {
			System.out.print("Error, no s'ha pogut escriure ningú text en el fitxer");
		}
	}
	
	public static void leerTexto() {
		File fichero = new File("exercici11.txt");

		try {

			System.out.println("El contingut del fitxer es: ");

			FileReader fr = new FileReader(fichero);
			//BufferedReader br = new BufferedReader(fr);

			int numChar = fr.read();
			int n = 0;

			Scanner sc = new Scanner(System.in);
		
			System.out.print("Introdueix un número per a mostrar un número de caracteres per pantalla: ");
			int num = Integer.parseInt(sc.nextLine());
			
			while (numChar != -1) {
				if(n != num) {
					System.out.print((char)numChar);
					numChar = fr.read();
					n++;
				}else {
					System.out.print("\r\nPressiona qualsevol tecla per a mostrar més text del fitxer: ");
					String charKeyboard = sc.nextLine();
					n = 0;
				}	
			}
			
			System.out.println("\r\n"); // Salto de línea
			System.out.println("S'ha llegit tot el fitxer");
			
			fr.close();
			//br.close();

		} catch (FileNotFoundException ffe) {
			System.err.println("Error, " + ffe);
		} catch (IOException ioe) {

			System.err.println("Error, " + ioe);
		}
	}

	public static void main(String[] args) {
		crearFichero();
		escribirTexto();
		leerTexto();
	}

}
