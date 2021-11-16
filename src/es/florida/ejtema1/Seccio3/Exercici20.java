package es.florida.ejtema1.Seccio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exercici20 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("pelicules.xml"));
			Element raiz = document.getDocumentElement();
			System.out.println("Contenido XML " + raiz.getNodeName() + ":");
			NodeList nodeList = document.getElementsByTagName("pelicula");
			System.out.println("Nombre total de nodos (películes): " + nodeList.getLength());
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nodo = nodeList.item(i);
				Element elemento = (Element) nodo;
				System.out.println("");
				System.out.println("Id: " + elemento.getAttribute("id"));
				System.out.println("Titol: " + elemento.getElementsByTagName("titol").item(0).getTextContent());
				System.out.println("Director: " + elemento.getElementsByTagName("director").item(0).getTextContent());
				System.out.println("Puntuació: " + elemento.getElementsByTagName("puntuacio").item(0).getTextContent());
				System.out.println("Genere: " + elemento.getElementsByTagName("genere").item(0).getTextContent());
			}
	}

}
