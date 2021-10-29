package es.florida.AE3_AD_XML;

import java.util.ArrayList;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import es.florida.AE3_AD_XML.Llibre;

public class Biblioteca {

	static ArrayList<Llibre> llibres = new ArrayList<Llibre>();

	// M�tode: recuperarTots
	// Descripci� m�tode: Llig el contingut del fitxer biblioteca.xml i obt� el valors de tots el llibre i els guarda en una variable cadascun dels valors, despr�s eixos valors els afegeix al objecte llib i eixe objecte el afegeix al arrayList de llibres.
	// Par�metros de entrada: No
	// Par�metros de salida: Return ArrayList llibres
	public static ArrayList<Llibre> recuperarTots() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("biblioteca.xml"));
			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("llibre");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("");
				Element eElement = (Element) node;
				int id = Integer.parseInt(eElement.getAttribute("id"));
				String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
				String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
				String any_publicacio = eElement.getElementsByTagName("any_publicacio").item(0).getTextContent();
				String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
				String nombre_pagines = eElement.getElementsByTagName("nombre_pagines").item(0).getTextContent();
				Llibre llib = new Llibre(id, titol, autor, any_publicacio, editorial, nombre_pagines);
				llibres.add(llib);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return llibres;
	}

	// M�tode: recuperarLlibre
	// Descripci� m�tode: Obt� un objecte llibre pasant-li un id de un llibre, introduit per teclat
	// Par�metros de entrada: int identificador pasat per par�metre
	// Par�metros de salida: Return llib
	public static Llibre recuperarLlibre(int identificador) {
		ArrayList<Llibre> llibres = recuperarTots();
		for (Llibre llib : llibres) {
			if (identificador == llib.getIdentificador()) {
				return llib;
			}
		}
		return null;
	}
	
	// M�tode: mostarLlibre
	// Descripci� m�tode: Mostra per pantalla tots els camps de un llibre a partir del id del llibre obtingut en recuperarLlibre
	// Par�metros de entrada: Objecte llibre pasat per par�metre
	// Par�metros de salida: No
	public static void mostrarLlibre(Llibre llibre) {
		System.out.println("Llibre " + llibre.getIdentificador());
		System.out.println("Titol: " + llibre.getTitol());
		System.out.println("Autor: " + llibre.getAutor());
		System.out.println("Any de publicaci�: " + llibre.getAny_Publicacio());
		System.out.println("Editorial: " + llibre.getEditorial());
		System.out.println("Llibre: " + llibre.getNombre_pagines());
	}
	
	// M�tode: crearLlibre
	// Descripci� m�tode: Llig tots els valors del arraylist, crea tants llibres nous com vuiges amb el mateixos camps i depr�s transforma le dades a format xml i els afegeix al fixter biblioteca.xml 
	// Par�metros de entrada: No
	// Par�metros de salida: No
	public static void crearLlibre() {
		recuperarTots();

		Scanner sc = new Scanner(System.in);

		System.out.print("�Desitja anyadir un llibre a la biblioteca (s/n)? ");
		String resposta = sc.nextLine();
		do {
			// id del �ltim llibre que hi ha en la llista
			int idUltim = llibres.size();
			int anyadirId = idUltim + 1;

			System.out.print("Introdueix el titol: ");
			String anyadirTitol = sc.nextLine();

			System.out.print("Introdueix el autor: ");
			String anyadirAutor = sc.nextLine();

			System.out.print("Introdueix el any de publicacio: ");
			String anyadirAnyPublicacio = sc.nextLine();

			System.out.print("Introdueix la editorial: ");
			String anyadirEditorial = sc.nextLine();

			System.out.print("Introdueix el nombre de p�gines: ");
			String anyadirNombrePagines = sc.nextLine();

			Llibre nouLlib = new Llibre(anyadirId, anyadirTitol, anyadirAutor, anyadirAnyPublicacio, anyadirEditorial,
					anyadirNombrePagines);
			llibres.add(nouLlib);

			writeXmlFile();

			System.out.println("El llibre s'ha creat correctament en la biblioteca");

			System.out.print("�Desitja anyadir un llibre a la biblioteca (s/n)? ");
			resposta = sc.nextLine();

		} while (resposta.equals("s"));
	}

	// M�tode: writeXmlFile
	// Descripci� m�tode: Obt� les dades del arraylist de llibres i les transforma a format xml
	// Par�metros de entrada: No
	// Par�metros de salida: No
	public static void writeXmlFile() {
		try {
			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();
			Element raiz = doc.createElement("llibreria");
			doc.appendChild(raiz);
			for (Llibre llib : llibres) {
				Element llibre = doc.createElement("llibre");
				String id = String.valueOf(llib.getIdentificador());
				llibre.setAttribute("id", id);
				raiz.appendChild(llibre);

				Element crearTitol = doc.createElement("titol");
				crearTitol.appendChild(doc.createTextNode(String.valueOf(llib.getTitol())));
				llibre.appendChild(crearTitol);

				Element crearAutor = doc.createElement("autor");
				crearAutor.appendChild(doc.createTextNode(String.valueOf(llib.getAutor())));
				llibre.appendChild(crearAutor);

				Element crearAnyPublicacio = doc.createElement("any_publicacio");
				crearAnyPublicacio.appendChild(doc.createTextNode(String.valueOf(llib.getAny_Publicacio())));
				llibre.appendChild(crearAnyPublicacio);

				Element crearEditorial = doc.createElement("editorial");
				crearEditorial.appendChild(doc.createTextNode(String.valueOf(llib.getEditorial())));
				llibre.appendChild(crearEditorial);

				Element crearNombrePagines = doc.createElement("nombre_pagines");
				crearNombrePagines.appendChild(doc.createTextNode(String.valueOf(llib.getNombre_pagines())));
				llibre.appendChild(crearNombrePagines);
			}
			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fw = new FileWriter("biblioteca.xml"); // Definir el nombre del fichero y guardar
				StreamResult result = new StreamResult(fw);
				aTransformer.transform(source, result);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (TransformerException ex) {
			System.out.println("Error escrivint el document");
		} catch (ParserConfigurationException ex) {
			System.out.println("Error escrivint el document");
		}
	}

	// M�tode: actualitzaLlibre
	// Descripci� m�tode: Actualitza el contingut d'un llibre pasant-li el id del llibre que vols modificar la informaci�
	// Par�metros de entrada: int identeficador pasat per p�rametre
	// Par�metros de salida: No
	public static void actualitzaLlibre(int identificador) {
		ArrayList<Llibre> llibres = recuperarTots();
		Scanner sc = new Scanner(System.in);
		for (Llibre llib : llibres) {
			if (identificador == llib.getIdentificador()) {

				System.out.print("Introdueix el id: ");
				llibres.get(identificador - 1).setIdentificador(Integer.parseInt(sc.nextLine()));

				System.out.print("Introdueix el titol: ");
				llibres.get(identificador - 1).setTitol(sc.nextLine());

				System.out.print("Introdueix el autor: ");
				llibres.get(identificador - 1).setAutor(sc.nextLine());

				System.out.print("Introdueix el any de publicaci�: ");
				llibres.get(identificador - 1).setAny_publicacio(sc.nextLine());

				System.out.print("Introdueix la editorial: ");
				llibres.get(identificador - 1).setEditorial(sc.nextLine());

				System.out.print("Introdueix el nombre de p�gines: ");
				llibres.get(identificador - 1).setNombre_pagines(sc.nextLine());

				writeXmlFile();

				System.out.println("El llibre s'ha actualizat correctament en la biblioteca");

			}
		}

		sc.close();
	}

	// M�tode: borrarLlibre
	// Descripci� m�tode: Borra un llibre pasant-li el id del llibre que vols borrar
	// Par�metros de entrada: int identificador
	// Par�metros de salida: No
	public static void borrarLlibre(int identificador) {
		ArrayList<Llibre> llibres = recuperarTots();
		llibres.remove(identificador - 1);
		writeXmlFile();
		System.out.println("El llibre s'ha eliminat correctament de la biblioteca");
	}
	
	
	// M�tode: main
	// Descripci� m�tode: Depenent de que opcio del men� que pressiones, fa una crida a la funci� determinada para eixa opci�.
	// Par�metros de entrada: String[] args pasat per p�rametre
	// Par�metros de salida: No
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Totes les opcions: \n");
		System.out.println("	- 1. Mostrar tots els t�tols de la biblioteca");
		System.out.println("	- 2. Mostrar informaci� detallada d�un llibre");
		System.out.println("	- 3. Crear nou llibre");
		System.out.println("	- 4. Actualitzar llibre");
		System.out.println("	- 5. Borrar llibre");
		System.out.println("	- 6. Tanca la biblioteca \n");

		System.out.print("Pressiona la opci� que vuigues vore: ");
		String opcio = sc.nextLine();

		switch (opcio) {
		case "1":
			ArrayList<Llibre> llibres = recuperarTots();
			for (Llibre llib : llibres) {
				System.out.println("Titol del llibre " + llib.getIdentificador() + ": " + llib.getTitol());
			}
			break;
		case "2":
			System.out.print("Introdueix un identificador per a poder mostrar la informaci� d'aquest llibre: ");
			mostrarLlibre(recuperarLlibre(Integer.parseInt(sc.nextLine())));
			break;
		case "3":
			crearLlibre();
			break;
		case "4":
			System.out.print("Introdueix un identificador per a poder actualitzar la informaci� d'aquest llibre: ");
			actualitzaLlibre(Integer.parseInt(sc.nextLine()));
			break;
		case "5":
			System.out.print("Introdueix un identificador per a poder eliminar la informaci� d'aquest llibre: ");
			borrarLlibre(Integer.parseInt(sc.nextLine()));
			break;
		case "6":
			// Tanca la biblioteca, es a dir, s'en ix del men� 
			System.exit(0);
			System.out.println("S'ha tancat la biblioteca");
			break;
		}

		sc.close();
	}
}
