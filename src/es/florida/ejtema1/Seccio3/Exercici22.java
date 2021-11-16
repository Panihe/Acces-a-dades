package es.florida.ejtema1.Seccio3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exercici22 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(new File("pelicules.xml"));
		Element raiz = document.getDocumentElement();
		System.out.println("Contenido XML " + raiz.getNodeName() + ":");
		NodeList nodeList = document.getElementsByTagName("pelicula");
		int nombreNodos = nodeList.getLength();
		System.out.println("Nombre total de nodos (películes): " + nombreNodos);
		Pelicula[] arrayPeliculas = new Pelicula[nombreNodos]; // Opció 1: Guardar como array (tamanyo fijo predefinido)
		ArrayList<Pelicula> llistaPeliculas = new ArrayList<Pelicula>(); // Opció 2: Guardar como llista (tamanyo no predefinido)
		for (int i = 0; i < nombreNodos; i++) {
			Node nodo = nodeList.item(i);
			Element elemento = (Element) nodo;
			int id = Integer.parseInt(elemento.getAttribute("id"));
			String titol = elemento.getElementsByTagName("titol").item(0).getTextContent();
			String director = elemento.getElementsByTagName("director").item(0).getTextContent();
			String puntuacio = elemento.getElementsByTagName("puntuacio").item(0).getTextContent();
			String genere = elemento.getElementsByTagName("genere").item(0).getTextContent();
			System.out.println("");
			System.out.println("Id: " + id);
			System.out.println("Titol: " + titol);
			System.out.println("Director: " + director);
			System.out.println("Puntuacio: " + puntuacio);
			System.out.println("Genere: " + genere);
			Pelicula novaPelicula = new Pelicula(id, titol, director, puntuacio, genere);
			arrayPeliculas[i] = novaPelicula;
			llistaPeliculas.add(novaPelicula);
			System.out.println("Pelicula creada: " + novaPelicula.toString());
			
		}

	}

}
