package es.florida.AE01;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import java.util.Scanner;

public class AE01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Totes les opcions: " + "\n");
		System.out.println("	 - getInformacio");
		System.out.println("	 - creaCarpeta");
		System.out.println("	 - creaFitxer");
		System.out.println("	 - eliminaFitxer");
		System.out.println("	 - renomenaFitxer");
		
		System.out.print("\n" + "¿Quina opcio vols introduir?: ");
		String opcio = sc.nextLine();
		
		switch(opcio) {
			case "getInformacio": getInformacio(args[0]);
			break;
			case "creaCarpeta": creaCarpeta(args[0]);
			break;
			case "creaFitxer": creaFitxer(args[0]);
			break;
			case "eliminaFitxer": eliminaFitxer(args[0]);
			break;
			case "renomenaFitxer": renomenaFitxer(args[0]);
			break;
		}
		
		sc.close();
		
	}
	
	public static void getInformacio(String opcio) {
		File fitxer = new File(opcio);
		System.out.println("Informaciò del fitxer:");
		System.out.println("Nom del fitxer: " + fitxer.getName());
		System.out.println("Ruta completa del fitxer: " + fitxer.getAbsolutePath());
		System.out.println("Tipus del fitxer: ");
		if(fitxer.isFile() == true) {
			System.out.println("	- ¿És fitxer?: Sí");
		}else {
			System.out.println("	- ¿És fitxer?: No");
		}
		
		if(fitxer.isDirectory() == true) {
			System.out.println("	- ¿És directori?: Sí");
		}else {
			System.out.println("	- ¿És directori?: No");
		}
		Long data = fitxer.lastModified();
		Date date = new Date(data);
		System.out.println("Última fecha de modificación: " + date);
		
		if(fitxer.isHidden() == true) {
			System.out.println("¿El fitxer és ocult?: Sí");
		}else {
			System.out.println("¿El fitxer és ocult?: No");
		}
		
		if(fitxer.isFile()) {
			double resultatTamany = fitxer.length() / 1024 / 1024 / 1024;
			System.out.println("El tamany del fitxer és: " + resultatTamany + " GB");
		}
		
		if(fitxer.isDirectory()) {
			System.out.println("El directori conté: " + fitxer.list() + " fitxers");
			double resultatEspaiLliure = fitxer.getFreeSpace() / 1024 / 1024 / 1024;
			System.out.println("El espai disponible del directori és: " + resultatEspaiLliure + " GB");
			double resultatEspaiTotal = fitxer.getTotalSpace() / 1024 / 1024 / 1024;
			System.out.println("El espai total del directori és: " + resultatEspaiTotal + " GB");
			double EspaiOcupat = resultatEspaiTotal - resultatEspaiLliure; // Espai Total - lliure;
			System.out.println("El espai total del directori és: " + EspaiOcupat + " GB");
			
		}
		
	}
	
	public static void creaCarpeta(String opcio) {
		File directori = new File(opcio);
		
		try {
			if(directori.exists()) {
				System.out.println("Aquesta carpeta ja existeix");
				
			}else {
				if (directori.mkdirs()) {
					System.out.println("S'ha creat el fitxer correctament");
				}else {
					System.out.println("Error, no s'ha pogut crear el fitxer");
				}
				System.out.println("Aquesta carpeta encara no existeix");
			}
		}catch (SecurityException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void creaFitxer(String opcio) {
		File fitxerNou = new File(opcio);
		
		try {
			if(fitxerNou.exists()) {
				System.out.println("Aquest fitxer ja existeix");
				
			}else {
				if (fitxerNou.createNewFile()) {
					System.out.println("S'ha creat el fitxer correctament");
				}else {
					System.out.println("Error, no s'ha pogut crear el fitxer");
				}
				System.out.println("Aquest fitxer encara no existeix");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void eliminaFitxer(String opcio) {
		File fitxer = new File(opcio);
		
		try {
			if(fitxer.exists()) {
				System.out.println("Aquesta carpeta existeix");
				if (fitxer.delete()) {
					System.out.println("S'ha eliminat el fitxer correctament");
				}else {
					System.out.println("Error, no s'ha pogut eliminar el fitxer");
				}
			}else {
				System.out.println("Aquesta carpeta encara no existeix");
			}
		}catch(SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void renomenaFitxer(String opcio) {
		File fitxer = new File(opcio);
		File fitxerRenomenat = new File("fitxer");
		
		try {
			if(fitxer.exists()) {
				System.out.println("Aquesta carpeta existeix");
				if (fitxerRenomenat.renameTo(fitxerRenomenat)) {
					System.out.println("S'ha renombrat el fitxer correctament");
				}else {
					System.out.println("Error, no s'ha pogut renombrar el fitxer");
				}
			}else {
				System.out.println("Aquesta carpeta encara no existeix");
			}
		}catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
}
