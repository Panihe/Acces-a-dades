package es.florida.AE5_Hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {

	// Métode: mostrarTitols
	// Descripció métode: Mostra tots el titols de hi han en la biblioteca, es a
	// dir, en la base de dades
	// Parámetros de entrada: Session session pasat párametre
	// Parámetros de salida: No

	public static void mostrarTitols(Session session) {

		session.beginTransaction();

		// Mostrar tots els títols de la biblioteca
		System.out.print("");
		List listaLlibres = new ArrayList();
		listaLlibres = session.createQuery("FROM Llibre").list();
		for (Object obj : listaLlibres) {
			Llibre llibTots = (Llibre) obj;
			System.out.println("Titol " + llibTots.getId() + ": " + llibTots.getTitol());
		}

		// Commit de la transacció
		session.getTransaction().commit();

	}

	// Métode: mostrarLlibre
	// Descripció métode: Mostra tots els atributs del llibre que se li passa el Id
	// que correspon al id de eixe llibre
	// Parámetros de entrada: Session session pasat párametre
	// Parámetros de salida: No

	public static void mostrarLlibre(Session session) {
		session.beginTransaction();

		// Ací les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);

		// Mostrar la informació detallada d’un llibre a partir del seu id
		System.out.print("Introdueix un id per a mostrar la informació detallada del llibre que correspon a eixe id: ");
		int idMostrar = Integer.parseInt(sc.nextLine());
		Llibre llibreId = (Llibre) session.get(Llibre.class, idMostrar);
		if (llibreId == null) {
			System.out.println("No hi ha ningun llibre amb id = " + idMostrar);
		} else {
			System.out.println("Llibre " + llibreId.getId() + ": ");
			System.out.println("  - Titol: " + llibreId.getTitol());
			System.out.println("  - Autor: " + llibreId.getAutor());
			System.out.println("  - Any naixement: " + llibreId.getAny_naixement());
			System.out.println("  - Any publicacio: " + llibreId.getAny_publicacio());
			System.out.println("  - Editorial: " + llibreId.getEditorial());
			System.out.println("  - Nombre pagines: " + llibreId.getNombre_pagines());
		}

		// Commit de la transacció
		session.getTransaction().commit();
	}

	// Métode: crearLlibre
	// Descripció métode: Pregunta si desitja anyadir un llibre a la biblioteca, si
	// la resposta es sí aleshores permet a introduir un titol, autor, any de
	// naixement, any de publicacio, editorial i nombre de pagines, els valors que
	// se escriuen se li pasa com a parametre a un nou object anomenat llibreNou i
	// eice objecte se li pasa a Serializable id = session.save(llibreNou) i es
	// mostra un missatge de que s'ha creat el llibre correctament amb el id+1. I
	// torna a preguntar si es desitja crear un llibre si la resposta es si es torna
	// a repetir el procediment, si la resposta es no es mostra si vols triar altra
	// opció si la resposta es si es mostra el menú si la resposta es no es
	// mostra un missatge se que s'ha tancat la biblioteca moltes gracies per
	// utilitzarla
	// Parámetros de entrada: Session session pasat párametre
	// Parámetros de salida: No

	public static void crearLlibre(Session session) {

		// Ací les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);

		// Afegir un nou llibre a la biblioteca
		System.out.print("Desitja anyadir un llibre a la biblioteca (s/n)?: ");
		String respostaAnyadir = sc.nextLine().toLowerCase();

		while (respostaAnyadir.equals("s")) {
			session.beginTransaction();

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

			System.out.print("Introdueix el nombre de págines: ");
			int anyadirNombrePagines = Integer.parseInt(sc.nextLine());

			Llibre llibreNou = new Llibre(anyadirTitol, anyadirAutor, anyadirAnyNaixement, anyadirAnyPublicacio,
					anyadirEditorial, anyadirNombrePagines);

			Serializable id = session.save(llibreNou);

			System.out.println("Llibre " + anyadirTitol + " creat amb id: " + id);

			// Commit de la transacció
			session.getTransaction().commit();

			System.out.print("\nDesitja anyadir un llibre a la biblioteca (s/n)?: ");
			respostaAnyadir = sc.nextLine().toLowerCase();
		}
	}

	// Métode: actualitzarllibre
	// Descripció métode: Pregunta si desitja actualitzar un llibre a la biblioteca,
	// si la resposta es sí es mostra un missatge per a introduir el id, si el
	// llibre amb el id que s'ha posat no está en la biblioteca. es a dir, en la
	// base de dades es mostra un missatge de que no hiha ningú llibre amb eixe id.
	// Pel el contrari, si la resposta es si es mostra un missatge de que si es vol
	// modificar el titol si la resposta es si permet al usuari introduir el nou
	// titol i es mostra un missatge de que s'ha modificat correctament i que ara el
	// titol es: el titoltou, si la resposta es no es mostra un missatge de que no
	// s'ha modificat el titol. Y així amb tots els atributs del llibre. Si s'han
	// actualitzat tots el atributs o alguns i altres no es mostra un missatge de
	// que s'han actualitzat correctament les dades del llibre que es volien
	// modificar.
	// Si no s'ha modificat ningú atribut es mostrá un de que no s'han actualitzat
	// les dades. I torna a preguntar si es desitja actualitzar un llibre si la
	// resposta es si
	// es torna a repetir el procediment, si la resposta es no es mostra si vols
	// triar altra
	// opció si la resposta es si es mostra el menú si la resposta es no es
	// mostra un missatge se que s'ha tancat la biblioteca moltes gracies per
	// utilitzarla
	// Parámetros de entrada: Session session pasat párametre
	// Parámetros de salida: No
	public static void actualitzarLlibre(Session session) {
		// Ací les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);

		boolean modificat = true;

		// Modificar atributs d’un llibre a partir del seu id
		System.out.print("Desitja actualitzar un llibre de la biblioteca (s/n)?: ");
		String respostaModificar = sc.nextLine().toLowerCase();

		while (respostaModificar.equals("s")) {
			session.beginTransaction();

			System.out.print("Introdueix un id per a actualitzar les dades del llibre que correspon a eixe id: ");
			int idModificar = Integer.parseInt(sc.nextLine());
			Llibre llibreModificar = (Llibre) session.load(Llibre.class, idModificar);
			if (llibreModificar == null) {
				System.out.println("No hi ha ningun llibre amb id = " + idModificar);
			} else {
				modificat = false;

				System.out.print("\nVols modificar el titol (s/n)?: ");
				String respostaModificarTitol = sc.nextLine().toLowerCase();

				if (respostaModificarTitol.equals("s")) {
					System.out.print("Introdueix el titol: ");
					String modificarTitol = sc.nextLine();
					llibreModificar.setTitol(modificarTitol);
					System.out.println("S'ha modificat el titol, ara el titol és: " + modificarTitol);
				} else {
					System.out.println("No s'ha modificat el titol");
				}

				System.out.print("\nVols modificar el autor (s/n)?: ");
				String respostaModificarAutor = sc.nextLine().toLowerCase();

				if (respostaModificarAutor.equals("s")) {
					modificat = true;
					System.out.print("Introdueix el autor: ");
					String modificarAutor = sc.nextLine();
					llibreModificar.setAutor(modificarAutor);
					System.out.println("S'ha modificat el autor, ara el autor és: " + modificarAutor);
				} else {
					System.out.println("No s'ha modificat el autor");
				}

				System.out.print("\nVols modificar el any de naixement (s/n)?: ");
				String respostaModificarAnyNaixement = sc.nextLine().toLowerCase();

				if (respostaModificarAnyNaixement.equals("s")) {
					modificat = true;
					System.out.print("Introdueix el any de naixement: ");
					String modificarAnyNaixement = sc.nextLine();
					llibreModificar.setAny_naixement(modificarAnyNaixement);
					System.out.println(
							"S'ha modificat el any de naixement, ara el any de naixement és: " + modificarAnyNaixement);
				} else {
					System.out.println("No s'ha modificat el any de naixement");
				}

				System.out.print("\nVols modificar el any de publicació (s/n)?: ");
				String respostaModificarAnyPublicacio = sc.nextLine().toLowerCase();

				if (respostaModificarAnyPublicacio.equals("s")) {
					modificat = true;
					System.out.print("Introdueix el any de publicació: ");
					String modificarAnyPublicacio = sc.nextLine();
					llibreModificar.setAny_publicacio(modificarAnyPublicacio);
					System.out.println("S'ha modificat el any de publicació, ara el any de publicació és: "
							+ modificarAnyPublicacio);
				} else {
					System.out.println("No s'ha modificat el any de publicació");
				}

				System.out.print("\nVols modificar la editorial (s/n)?: ");
				String respostaModificarEditorial = sc.nextLine().toLowerCase();

				if (respostaModificarEditorial.equals("s")) {
					modificat = true;
					System.out.print("Introdueix la editorial: ");
					String modificarEditorial = sc.nextLine();
					llibreModificar.setEditorial(modificarEditorial);
					System.out.println("S'ha modificat la editorial, ara la editorial és: " + modificarEditorial);
				} else {
					System.out.println("No s'ha modificat la editorial");
				}

				System.out.print("\nVols modificar el nombre de pàgines (s/n)?: ");
				String respostaModificarNombrePagines = sc.nextLine().toLowerCase();

				if (respostaModificarNombrePagines.equals("s")) {
					modificat = true;
					System.out.print("Introdueix el nombre de pàgines: ");
					int modificarNombrePagines = Integer.parseInt(sc.nextLine());
					llibreModificar.setNombre_pagines(modificarNombrePagines);
					System.out.println("S'ha modificat el nombre de pàgines, ara el nombre de pàgines és: "
							+ modificarNombrePagines);
				} else {
					System.out.println("No s'ha modificat el nombre de pàgines");
				}

				session.update(llibreModificar);
				if (modificat == true) {
					System.out.println("\nS'han actualitzat les dades del llibre que es volíen modificar");
				} else {
					System.out.println("\nNo s'han actualitzat les dades del llibre que es volíen modificar");
				}

			}

			// Commit de la transacció
			session.getTransaction().commit();

			System.out.print("\nDesitja actualitzar un llibre de la biblioteca (s/n)?: ");
			respostaModificar = sc.nextLine().toLowerCase();
		}
	}

	// Métode: borrarLlibre
	// Descripció métode: Pregunta si desitja esborrar un llibre de la biblioteca,
	// si la resposta es sí es mostra un missatge per a introduir el id, si el
	// llibre amb el id que s'ha posat no está en la biblioteca. es a dir, en la
	// base de dades es mostra un missatge de que no s'ha pogut esborrar el llibre
	// per no hi ningú llibre amb eixe id. Pel el contrari, si el llibre amd eixe id si está en la bibliotexa es mostra un
	// missatge de que El llibre amb id: el id que s'a posat s'ha esborrat
	// correctament de la biblioteca . I torna a preguntar si es desitja esborrar un llibre si la resposta es si es
	// torna a repetir el procediment, si la resposta es no es mostra si vols triar
	// altra opció si la resposta es si es mostra el menú si la resposta es no es
	// mostra un missatge se que s'ha tancat la biblioteca moltes gracies per
	// utilitzarla
	// Parámetros de entrada: Session session pasat párametre
	// Parámetros de salida: No
	public static void borrarLlibre(Session session) {
		// Ací les operacio/ns CRUD (crear, llegir, actualitzar, borrar)
		Scanner sc = new Scanner(System.in);

		// Esborrar un llibre a partir del seu id
		System.out.print("Desitja esborrar un llibre de la biblioteca (s/n)? ");
		String respostaEsborrar = sc.nextLine().toLowerCase();

		while (respostaEsborrar.equals("s")) {
			session.beginTransaction();

			System.out.print("Introdueix un id per a esborrar el llibre que correspon a eixe id: ");
			int idEsborrar = Integer.parseInt(sc.nextLine());

			try {
				Llibre llibreEsborrar = new Llibre();
				llibreEsborrar.setId(idEsborrar);
				session.delete(llibreEsborrar);

				// Commit de la transacció
				session.getTransaction().commit();
				session.clear();

				System.out.println("El llibre amb id " + idEsborrar + " s'ha esborrat correctament de la biblioteca");
			} catch (Exception e) {
				System.out.println("No s'ha pogut esborrar el llibre amb id " + idEsborrar + " de la biblioteca");
			}

			System.out.print("\nDesitja esborrar un llibre de la biblioteca (s/n)? ");
			respostaEsborrar = sc.nextLine().toLowerCase();
		}
	}

	// Métode: main
	// Descripció métode: Fa una configuració de la configuració que hem configurat
		// en el fitxer de configuració de hibernate i obri una nova sessió. Mostra el
		// menú amb les funcionalitats i mostra un missatge de triar una opció, segons
		// el numero que s'introdusca es mostrará un opció, es a dir, si s¡ontroduiex el
		// 1 es mostrará la funcionalitat 1, mostrar tots el titols de la biblioteca. I
		// així amb totes les opcions. Si s'introdueix una opció que no esta configurada
		// es mostra un missatge de que s'ha triat una opció que no está configurada,
		// per favor tria altra opció
	// Parámetros de entrada: String[] args pasat párametre
	// Parámetros de salida: No
	public static void main(String[] args) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();

		Scanner sc = new Scanner(System.in);
		boolean repeteix = true;
		while (repeteix) {
			repeteix = false;

			System.out.println("Belvingut a la biblioteca");
			System.out.println("Que vols fer? ");
			System.out.println("\nTotes les opcions: \n");
			System.out.println("	- 1. Mostrar tots els títols de la biblioteca");
			System.out.println("	- 2. Mostrar informació detallada d’un llibre");
			System.out.println("	- 3. Crear nou llibre");
			System.out.println("	- 4. Actualitzar llibre");
			System.out.println("	- 5. Borrar llibre");
			System.out.println("	- 6. Tanca la biblioteca \n");

			System.out.print("Tria la opció que vuigues vore: ");
			String opcio = sc.nextLine();

			String respostaOpcio = "";

			switch (opcio) {
			case "1":
				mostrarTitols(session);
				System.out.print("\nVols triar altra opció (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "2":
				mostrarLlibre(session);
				System.out.print("\nVols triar altra opció (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "3":
				crearLlibre(session);
				System.out.print("\nVols triar altra opció (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "4":
				actualitzarLlibre(session);
				System.out.print("\nVols triar altra opció (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "5":
				borrarLlibre(session);
				System.out.print("\nVols triar altra opció (s/n)?: ");
				respostaOpcio = sc.nextLine().toLowerCase();
				if (respostaOpcio.equals("s")) {
					repeteix = true;
				} else {
					System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				}
				break;
			case "6":
				// Tanca la biblioteca, es a dir, s'en ix del menú
				System.out.println("S'ha tancat la biblioteca\nMoltes gracies per utilitzarla");
				sc.close();
				System.exit(0);
				break;
			default:
				repeteix = true;
				System.out.println("Has triat una opció que no está configurada, per favor tria altra opció");
			}

		}

		sc.close();
		// Tanca de la sessió
		session.close();

	}

}
