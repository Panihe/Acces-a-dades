package es.florida.AE6_NoSql;

import static com.mongodb.client.model.Filters.eq;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Biblioteca {

	// M�tode: mostrarTitols
	// Descripci� m�tode: Mostra tots el titols de hi han en la biblioteca, es a
	// dir, en la base de dades
	// Par�metros de entrada: Session session pasat p�rametre
	// Par�metros de salida: No
	public static void mostrarTitols() {
		// Mostrar tots els t�tols de la biblioteca
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
		MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			//System.out.println("Titol " + obj.getInt("Id") + ": " + obj.getString("Titol"));
			System.out.println("Llibre " + obj.getInt("Id") + ": \n   Titol --> " + obj.getString("Titol"));
		}

		mongoClient.close();
	}

	// M�tode: mostrarLlibre
	// Descripci� m�tode: Mostra tots els atributs del llibre que se li passa el Id
	// que correspon al id de eixe llibre
	// Par�metros de entrada: Session session pasat p�rametre
	// Par�metros de salida: No
	public static void mostrarLlibre() {
		// Mostrar la informaci� detallada d�un llibre a partir del seu id
		Scanner sc = new Scanner(System.in);
		System.out.print("Introdueix un id per a mostrar la informaci� detallada del llibre que correspon a eixe id: ");
		int idMostrar = Integer.parseInt(sc.nextLine());

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
		MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

		Bson query = eq("Id", idMostrar);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		if (cursor == null) {
			System.out.println("No hi ha ningun llibre amb id = " + idMostrar);
		} else {
			while (cursor.hasNext()) {
				JSONObject obj = new JSONObject(cursor.next().toJson());
				System.out.print("Llibre " + obj.getInt("Id") + ": \n" + "   - Titol: " + obj.getString("Titol")
						+ " \n " + "  - Autor: " + obj.getString("Autor") + " \n " + "  - Any Naixement: "
						+ obj.getString("Any_naixement") + " \n " + "  - Any Publicacio: "
						+ obj.getString("Any_publicacio") + " \n " + "  - Editorial: " + obj.getString("Editorial")
						+ " \n " + "  - Nombre pagines: " + obj.getString("Nombre_pagines") + " \n ");
			}
		}

		mongoClient.close();

	}

	// M�tode: ultimId
	// Descripci� m�tode: Obt� el �ltim Id de la biblioteca, es a dir de la colecci�
	// Par�metros de entrada: No
	// Par�metros de salida: Devolveix el id
	private static int ultimId() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
		MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

		int id = 1;
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			id = obj.getInt("Id");
		}
		mongoClient.close();

		return id;
	}

	// M�tode: crearLlibre
	// Descripci� m�tode: Pregunta si desitja anyadir un llibre a la biblioteca, si
	// la resposta es s� aleshores permet a introduir un titol, autor, any de
	// naixement, any de publicacio, editorial i nombre de pagines, els valors que
	// se escriuen se li pasa com a parametre a un nou object anomenat llibreNou i
	// eice objecte se li pasa a Serializable id = session.save(llibreNou) i es
	// mostra un missatge de que s'ha creat el llibre correctament amb el id+1. I
	// torna a preguntar si es desitja crear un llibre si la resposta es si es torna
	// a repetir el procediment, si la resposta es no es mostra si vols triar altra
	// opci� si la resposta es si es mostra el men� si la resposta es no es
	// mostra un missatge se que s'ha tancat la biblioteca moltes gracies per
	// utilitzarla
	// Par�metros de entrada: Session session pasat p�rametre
	// Par�metros de salida: No

	public static void crearLlibre() {
		// Ac� les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);

		// Afegir un nou llibre a la biblioteca
		System.out.print("Desitja anyadir un llibre a la biblioteca (s/n)?: ");
		String respostaAnyadir = sc.nextLine().toLowerCase();

		while (respostaAnyadir.equals("s")) {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
			MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

//			System.out.print("Introdueix el id: ");
//			int anyadirId = Integer.parseInt(sc.nextLine());
			int anyadirId = ultimId() + 1;

			System.out.print("Introdueix el titol: ");
			String anyadirTitol = sc.nextLine();

			System.out.print("Introdueix el autor: ");
			String anyadirAutor = sc.nextLine();

			System.out.print("Introdueix el any de naixement: ");
			String anyadirAnyNaixement = sc.nextLine();

			System.out.print("Introdueix el any de publicacio: ");
			String anyadirAnyPublicacio = sc.nextLine();

			System.out.print("Introdueix la editorial: ");
			String anyadirEditorial = sc.nextLine();

			System.out.print("Introdueix el nombre de p�gines: ");
			String anyadirNombrePagines = sc.nextLine();

			Document doc = new Document();
			doc.append("Id", anyadirId);
			doc.append("Titol", anyadirTitol);
			doc.append("Autor", anyadirAutor);
			doc.append("Any_naixement", anyadirAnyNaixement);
			doc.append("Any_publicacio", anyadirAnyPublicacio);
			doc.append("Editorial", anyadirEditorial);
			doc.append("Nombre_pagines", anyadirNombrePagines);

			coleccion.insertOne(doc);

			System.out.println("Llibre " + anyadirTitol + " creat amb id: " + anyadirId);

			mongoClient.close();

			System.out.print("\nDesitja anyadir un llibre a la biblioteca (s/n)?: ");
			respostaAnyadir = sc.nextLine().toLowerCase();
		}
	}

	// M�tode: actualitzarllibre
	// Descripci� m�tode: Pregunta si desitja actualitzar un llibre a la biblioteca,
	// si la resposta es s� es mostra un missatge per a introduir el id, si el
	// llibre amb el id que s'ha posat no est� en la biblioteca. es a dir, en la
	// base de dades es mostra un missatge de que no hiha ning� llibre amb eixe id.
	// Pel el contrari, si la resposta es si es mostra un missatge de que si es vol
	// modificar el titol si la resposta es si permet al usuari introduir el nou
	// titol i es mostra un missatge de que s'ha modificat correctament i que ara el
	// titol es: el titoltou, si la resposta es no es mostra un missatge de que no
	// s'ha modificat el titol. Y aix� amb tots els atributs del llibre. Si s'han
	// actualitzat tots el atributs o alguns i altres no es mostra un missatge de
	// que s'han actualitzat correctament les dades del llibre que es volien
	// modificar.
	// Si no s'ha modificat ning� atribut es mostr� un de que no s'han actualitzat
	// les dades. I torna a preguntar si es desitja actualitzar un llibre si la
	// resposta es si
	// es torna a repetir el procediment, si la resposta es no es mostra si vols
	// triar altra
	// opci� si la resposta es si es mostra el men� si la resposta es no es
	// mostra un missatge se que s'ha tancat la biblioteca moltes gracies per
	// utilitzarla
	// Par�metros de entrada: Session session pasat p�rametre
	// Par�metros de salida: No
	public static void actualitzarLlibre() {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
		MongoCollection<Document> coleccion = database.getCollection("Biblioteca");
		
		// Ac� les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);
		
		boolean modificat = true;

		// Modificar atributs d�un llibre a partir del seu id
		System.out.print("Desitja actualitzar un llibre de la biblioteca (s/n)?: ");
		String respostaModificar = sc.nextLine().toLowerCase();
		
		while (respostaModificar.equals("s")) {
		
			System.out.print("Introdueix un id per a actualitzar les dades del llibre que correspon a eixe id: ");
			int idModificar = Integer.parseInt(sc.nextLine());
//			if (idModificar == null) {
//				System.out.println("No hi ha ningun llibre amb id = " + idModificar);
//		    }
			
			modificat = false;

			System.out.print("\nVols modificar el titol (s/n)?: ");
			String respostaModificarTitol = sc.nextLine().toLowerCase();

			if (respostaModificarTitol.equals("s")) {
				System.out.print("Introdueix el titol: ");
				String modificarTitol = sc.nextLine();
				coleccion.updateOne(eq("Id", idModificar), new Document("$set", new Document("Titol", modificarTitol)));
				System.out.println("S'ha modificat el titol, ara el titol �s: " + modificarTitol);
			} else {
				System.out.println("No s'ha modificat el titol");
			}

			System.out.print("\nVols modificar el autor (s/n)?: ");
			String respostaModificarAutor = sc.nextLine().toLowerCase();

			if (respostaModificarAutor.equals("s")) {
				modificat = true;
				System.out.print("Introdueix el autor: ");
				String modificarAutor = sc.nextLine();
				coleccion.updateOne(eq("Id", idModificar), new Document("$set", new Document("Autor", modificarAutor)));
				System.out.println("S'ha modificat el autor, ara el autor �s: " + modificarAutor);
			} else {
				System.out.println("No s'ha modificat el autor");
			}

			System.out.print("\nVols modificar el any de naixement (s/n)?: ");
			String respostaModificarAnyNaixement = sc.nextLine().toLowerCase();

			if (respostaModificarAnyNaixement.equals("s")) {
				modificat = true;
				System.out.print("Introdueix el any de naixement: ");
				String modificarAnyNaixement = sc.nextLine();
				coleccion.updateOne(eq("Id", idModificar), new Document("$set", new Document("Any_naixement", modificarAnyNaixement)));
				System.out.println(
						"S'ha modificat el any de naixement, ara el any de naixement �s: " + modificarAnyNaixement);
			} else {
				System.out.println("No s'ha modificat el any de naixement");
			}

			System.out.print("\nVols modificar el any de publicaci� (s/n)?: ");
			String respostaModificarAnyPublicacio = sc.nextLine().toLowerCase();

			if (respostaModificarAnyPublicacio.equals("s")) {
				modificat = true;
				System.out.print("Introdueix el any de publicaci�: ");
				String modificarAnyPublicacio = sc.nextLine();
				coleccion.updateOne(eq("Id", idModificar), new Document("$set", new Document("Any_publicacio", modificarAnyPublicacio)));
				System.out.println(
						"S'ha modificat el any de publicaci�, ara el any de publicaci� �s: " + modificarAnyPublicacio);
			} else {
				System.out.println("No s'ha modificat el any de publicaci�");
			}

			System.out.print("\nVols modificar la editorial (s/n)?: ");
			String respostaModificarEditorial = sc.nextLine().toLowerCase();

			if (respostaModificarEditorial.equals("s")) {
				modificat = true;
				System.out.print("Introdueix la editorial: ");
				String modificarEditorial = sc.nextLine();
				coleccion.updateOne(eq("Id", idModificar), new Document("$set", new Document("Editorial", modificarEditorial)));
				System.out.println("S'ha modificat la editorial, ara la editorial �s: " + modificarEditorial);
			} else {
				System.out.println("No s'ha modificat la editorial");
			}

			System.out.print("\nVols modificar el nombre de p�gines (s/n)?: ");
			String respostaModificarNombrePagines = sc.nextLine().toLowerCase();

			if (respostaModificarNombrePagines.equals("s")) {
				modificat = true;
				System.out.print("Introdueix el nombre de p�gines: ");
				String modificarNombrePagines = sc.nextLine();
				coleccion.updateOne(eq("Id", idModificar), new Document("$set", new Document("Nombre_pagines", modificarNombrePagines)));
				System.out.println(
						"S'ha modificat el nombre de p�gines, ara el nombre de p�gines �s: " + modificarNombrePagines);
			} else {
				System.out.println("No s'ha modificat el nombre de p�gines");
			}

			if (modificat == true) {
				System.out.println("\nS'han actualitzat les dades del llibre que es vol�en modificar");
			} else {
				System.out.println("\nNo s'han actualitzat les dades del llibre que es vol�en modificar");
			}

		}
		
		mongoClient.close();

		System.out.print("\nDesitja actualitzar un llibre de la biblioteca (s/n)?: ");
		respostaModificar = sc.nextLine().toLowerCase();
	}

	// M�tode: borrarLlibre
	// Descripci� m�tode: Pregunta si desitja esborrar un llibre de la biblioteca,
	// si la resposta es s� es mostra un missatge per a introduir el id, si el
	// llibre amb el id que s'ha posat no est� en la biblioteca. es a dir, en la
	// base de dades es mostra un missatge de que no s'ha pogut esborrar el llibre
	// per no hi ning� llibre amb eixe id. Pel el contrari, si el llibre amd eixe id
	// si est� en la bibliotexa es mostra un
	// missatge de que El llibre amb id: el id que s'a posat s'ha esborrat
	// correctament de la biblioteca . I torna a preguntar si es desitja esborrar un
	// llibre si la resposta es si es
	// torna a repetir el procediment, si la resposta es no es mostra si vols triar
	// altra opci� si la resposta es si es mostra el men� si la resposta es no es
	// mostra un missatge se que s'ha tancat la biblioteca moltes gracies per
	// utilitzarla
	// Par�metros de entrada: Session session pasat p�rametre
	// Par�metros de salida: No
	public static void borrarLlibre() {
		// Ac� les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);

		// Esborrar un llibre a partir del seu id
		System.out.print("Desitja esborrar un llibre de la biblioteca (s/n)? ");
		String respostaEsborrar = sc.nextLine().toLowerCase();

		while (respostaEsborrar.equals("s")) {
			System.out.print("Introdueix un id per a esborrar el llibre que correspon a eixe id: ");
			int idEsborrar = Integer.parseInt(sc.nextLine());

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
			MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

			try {
				coleccion.deleteOne(eq("Id", idEsborrar));

				System.out.println("El llibre amb id " + idEsborrar + " s'ha esborrat correctament de la biblioteca");
			} catch (Exception e) {
				System.out.println("No s'ha pogut esborrar el llibre amb id " + idEsborrar + " de la biblioteca");
			}

			mongoClient.close();

			System.out.print("\nDesitja esborrar un llibre de la biblioteca (s/n)? ");
			respostaEsborrar = sc.nextLine().toLowerCase();
		}
	}

	public static void main(String[] args) {
//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
//		MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

		Scanner sc = new Scanner(System.in);
		boolean repeteix = true;
		while (repeteix) {
			repeteix = false;

			System.out.println("Belvingut a la biblioteca");
			System.out.println("Que vols fer? ");
			System.out.println("\nTotes les opcions: \n");
			System.out.println("	- 1. Mostrar tots els t�tols de la biblioteca");
			System.out.println("	- 2. Mostrar informaci� detallada d�un llibre");
			System.out.println("	- 3. Crear nou llibre");
			System.out.println("	- 4. Actualitzar llibre");
			System.out.println("	- 5. Borrar llibre");
			System.out.println("	- 6. Tanca la biblioteca \n");

			System.out.print("Tria la opci� que vuigues vore: ");
			String opcio = sc.nextLine();

			String respostaOpcio = "";

			switch (opcio) {
			case "1":
				mostrarTitols();
				System.out.print("\nVols triar altra opci� (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "2":
				mostrarLlibre();
				System.out.print("\nVols triar altra opci� (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "3":
				crearLlibre();
				System.out.print("\nVols triar altra opci� (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "4":
				actualitzarLlibre();
				System.out.print("\nVols triar altra opci� (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "5":
				borrarLlibre();
				System.out.print("\nVols triar altra opci� (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "6":
				// Tanca la biblioteca, es a dir, s'en ix del men�
				System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				sc.close();
				System.exit(0);
				break;
			default:
				repeteix = true;
				System.out.println("Has triat una opci� que no est� configurada, per favor tria altra opci�");
			}

		}

		sc.close();

		// mongoClient.close();
	}

}
