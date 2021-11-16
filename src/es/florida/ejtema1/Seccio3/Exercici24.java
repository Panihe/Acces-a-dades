package es.florida.ejtema1.Seccio3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exercici24 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(new File("pelicules.xml"));
		Element raiz = document.getDocumentElement();
		System.out.println("Contenido XML " + raiz.getNodeName() + ":");
		NodeList nodeList = document.getElementsByTagName("pelicula");
		int nombreNodos = nodeList.getLength();
		System.out.println("Nombre total de nodos (pel�cules): " + nombreNodos);
		ArrayList<Pelicula> llistaPeliculas = new ArrayList<Pelicula>(); // Opci� 2: Guardar como llista (tamanyo no
																			// predefinido)
		for (int i = 0; i < nombreNodos; i++) {
			Node nodo = nodeList.item(i);
			Element elemento = (Element) nodo;
			int id = Integer.parseInt(elemento.getAttribute("id"));
			String titol = elemento.getElementsByTagName("titol").item(0).getTextContent();
			String director = elemento.getElementsByTagName("director").item(0).getTextContent();
			String puntuacio = elemento.getElementsByTagName("puntuacio").item(0).getTextContent();
			String genere = elemento.getElementsByTagName("genere").item(0).getTextContent();
			Pelicula novaPelicula = new Pelicula(id,titol, director, puntuacio, genere);
			llistaPeliculas.add(novaPelicula);
			System.out.println("Pelicula creada: " + novaPelicula.toString());

		}

		System.out.print("�Desitja anyadir una nova pelicula (s/n)? ");
		Scanner sc = new Scanner(System.in);
		String resposta = sc.nextLine();
	
		while (resposta.equals("s")) {
			// id del �ltim llibre que hi ha en la llista
			int idUltim = llistaPeliculas.size();
			int anyadirId = idUltim + 1;
			
			System.out.print("Introdueix el titol: "); 
			String anyadirTitol = sc.nextLine();
			
			System.out.print("Introdueix el director: "); 
			String anyadirDirector = sc.nextLine();
			
			System.out.print("Introdueix la puntuaci�: "); 
			String anyadirPuntuacio = sc.nextLine();
			
			System.out.print("Introdueix el genere: "); 
			String anyadirGenere = sc.nextLine();
			
			Pelicula novaPelicula = new Pelicula(anyadirId, anyadirTitol, anyadirDirector, anyadirPuntuacio, anyadirGenere);
			llistaPeliculas.add(novaPelicula);

			System.out.println("Pelicula guardada en llista: " + novaPelicula.toString());

			System.out.print("�Desitja anyadir una nova pelicula (s/n)? ");
			resposta = sc.nextLine();
		}
		sc.close();
		
		DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = dFact.newDocumentBuilder();
		Document doc = build.newDocument();
		Element nuevaRaiz = doc.createElement("peliculas");
		doc.appendChild(raiz);
		for (Pelicula pelicula : llistaPeliculas) {
			Element llibre = doc.createElement("pelicula");
			String id = String.valueOf(pelicula.getId());
			llibre.setAttribute("id", id);
			raiz.appendChild(llibre);

			Element crearTitol = doc.createElement("titol");
			crearTitol.appendChild(doc.createTextNode(String.valueOf(pelicula.getTitol())));
			llibre.appendChild(crearTitol);

			Element crearDirector = doc.createElement("director");
			crearDirector.appendChild(doc.createTextNode(String.valueOf(pelicula.getDirector())));
			llibre.appendChild(crearDirector);

			Element crearPuntuacio = doc.createElement("puntuacio");
			crearPuntuacio.appendChild(doc.createTextNode(String.valueOf(pelicula.getPuntuacio())));
			llibre.appendChild(crearPuntuacio);

			Element crearGenere = doc.createElement("genere");
			crearGenere.appendChild(doc.createTextNode(String.valueOf(pelicula.getGenere())));
			llibre.appendChild(crearGenere);

		}
		TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
		Transformer aTransformer = tranFactory.newTransformer();
		aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
		aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		FileWriter fw = new FileWriter("pelicules2.xml"); // Definir el nombre del fichero y guardar
		StreamResult result = new StreamResult(fw);
		aTransformer.transform(source, result);
		fw.close();
	

	}

}
