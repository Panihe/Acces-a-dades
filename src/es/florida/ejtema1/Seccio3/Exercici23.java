package es.florida.ejtema1.Seccio3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exercici23 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(new File("pelicules.xml"));
		Element raiz = document.getDocumentElement();
		System.out.println("Contenido XML " + raiz.getNodeName() + ":");
		NodeList nodeList = document.getElementsByTagName("pelicula");
		int nombreNodos = nodeList.getLength();
		System.out.println("Nombre total de nodos (películes): " + nombreNodos);
		ArrayList<Pelicula> llistaPeliculas = new ArrayList<Pelicula>(); // Opció 2: Guardar como llista (tamanyo no
																			// predefinido)
		for (int i = 0; i < nombreNodos; i++) {
			Node nodo = nodeList.item(i);
			Element elemento = (Element) nodo;
			int id = Integer.parseInt(elemento.getAttribute("id"));
			String titol = elemento.getElementsByTagName("titol").item(0).getTextContent();
			String director = elemento.getElementsByTagName("director").item(0).getTextContent();
			String puntuacio = elemento.getElementsByTagName("puntuacio").item(0).getTextContent();
			String genere = elemento.getElementsByTagName("genere").item(0).getTextContent();
			Pelicula novaPelicula = new Pelicula(id, titol, director, puntuacio, genere);
			llistaPeliculas.add(novaPelicula);
			System.out.println("Pelicula creada: " + novaPelicula.toString());

		}

		System.out.print("¿Desitja anyadir una nova pelicula (s/n)? ");
		Scanner sc = new Scanner(System.in);
		String resposta = sc.nextLine();
	
		while (resposta.equals("s")) {
			// id del últim llibre que hi ha en la llista
		    int idUltim = llistaPeliculas.size();
		    int anyadirId = idUltim + 1;
			System.out.print("Introdueix el titol: "); 
			String anyadirTitol = sc.nextLine();
			
			System.out.print("Introdueix el director: "); 
			String anyadirDirector = sc.nextLine();
			
			System.out.print("Introdueix la puntuació: "); 
			String anyadirPuntuacio = sc.nextLine();
			
			System.out.print("Introdueix el genere: "); 
			String anyadirGenere = sc.nextLine();
			
			Pelicula novaPelicula = new Pelicula(anyadirId, anyadirTitol, anyadirDirector, anyadirPuntuacio, anyadirGenere);
			llistaPeliculas.add(novaPelicula);

			System.out.println("Pelicula guardada en llista: " + novaPelicula.toString());

			System.out.print("¿Desitja anyadir una nova pelicula (s/n)? ");
			resposta = sc.nextLine();
		}
		sc.close();
	}

}
