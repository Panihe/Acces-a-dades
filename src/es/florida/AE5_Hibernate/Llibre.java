package es.florida.AE5_Hibernate;

public class Llibre {
	private int id, nombre_pagines;
	private String titol, autor, any_naixement, any_publicacio, editorial;
	
	Llibre() {}

	// M�tode: Llibre
	// Descripci� m�tode: Obt� tots els valors de id, titol, autor, any_naixement, any_publicacio, editorial, i nombre_pagines del llibres
	// Par�metros de entrada: int identificador, String titol, String autor, String any_naixement, String any_publicacio, String editorial e int nombre_pagines pasat p�rametre
	// Par�metros de salida: No
	public Llibre(int id, String titol, String autor, String any_naixement, String any_publicacio, String editorial, 
			int nombre_pagines) {
		this.id = id;
		this.titol = titol;
		this.autor = autor;
		this.any_naixement = any_naixement;
		this.any_publicacio = any_publicacio;
		this.editorial = editorial;
		this.nombre_pagines = nombre_pagines;
	}
	
	// M�tode: Llibre
	// Descripci� m�tode: Obt� tots els valors de titol, autor, any_publicacio, editorial, i nombre_pagines del llibres
	// Par�metros de entrada: int identificador, String titol, String autor, String any_publicacio i String editorial pasat p�rametre
	// Par�metros de salida: No
	public Llibre(String titol, String autor, String any_naixement, String any_publicacio, String editorial,
			int nombre_pagines) {
		this.titol = titol;
		this.autor = autor;
		this.any_naixement = any_naixement;
		this.any_publicacio = any_publicacio;
		this.editorial = editorial;
		this.nombre_pagines = nombre_pagines;
	}
	
	// M�tode: getIdentificador
    // Descripci� m�tode: Obt� el identificador
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna el identificador
	public int getId() {
		return this.id;
	}

	// M�tode: setIdentificador
    // Descripci� m�tode: Estableix el identificador obtingut en getIdentificador
    // Par�metros de entrada: int identificador pasat per p�rametres
    // Par�metros de salida: No
	public void setId(int id) {
		this.id = id;
	}

	// M�tode: getTitol
    // Descripci� m�tode: Obt� el titol
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna el titol
	public String getTitol() {
		return this.titol;
	}

	// M�tode: setIdentificador
    // Descripci� m�tode: Estableix el titol obtingut en getTitol
    // Par�metros de entrada: String titol pasat per p�rametre
    // Par�metros de salida: No
	public void setTitol(String titol) {
		this.titol = titol;
	}

	// M�tode: getAutor
    // Descripci� m�tode: Obt� el Autor
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna el autor
	public String getAutor() {
		return this.autor;
	}

	// M�tode: setAutor
    // Descripci� m�tode: Estableix el autor obtingut en getAutor
    // Par�metros de entrada: String autor pasat per p�rametre
    // Par�metros de salida: No
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	// M�tode: getAny_Naixement
    // Descripci� m�tode: Obt� el any de naixement
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna el any de naixement
	public String getAny_naixement() {
		return this.any_naixement;
	}

	// M�tode: setAny_naixement
    // Descripci� m�tode: Estableix el any de publicaci� obtingut en getAny_naixement
    // Par�metros de entrada: String any_naixment pasat per p�rametre
    // Par�metros de salida: No
	public void setAny_naixement(String any_naixement) {
		this.any_naixement = any_naixement;
	}

	// M�tode: getAny_Publicacio
    // Descripci� m�tode: Obt� el any de publicaci�
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna el any de publicaci�
	public String getAny_publicacio() {
		return this.any_publicacio;
	}

	// M�tode: setAny_Publicacio
    // Descripci� m�tode: Estableix el any de publicaci� obtingut en getAny_publicacio
    // Par�metros de entrada: String any_publicacio pasat per p�rametre
    // Par�metros de salida: No
	public void setAny_publicacio(String any_publicacio) {
		this.any_publicacio = any_publicacio;
	}

	// M�tode: getEditorial
    // Descripci� m�tode: Obt� la editorial
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna la editorial
	public String getEditorial() {
		return this.editorial;
	}

	// M�tode: setEditorial
    // Descripci� m�tode: Estableix la editorial obtinguda en getEditorial
    // Par�metros de entrada: String editorial pasat per p�rametre
    // Par�metros de salida: No
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	// M�tode: getNombre_pagines
    // Descripci� m�tode: Obt� el nombre de pagines 
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna el nombre de pagines
	public int getNombre_pagines() {
		return this.nombre_pagines;
	}

	// M�tode: setNombre_pagines
    // Descripci� m�tode: Estableix el nombre de pagines obtingudes en getNombre_pagines
    // Par�metros de entrada: String nombre_pagines pasat per p�rametre
    // Par�metros de salida: No
	public void setNombre_pagines(int nombre_pagines) {
		this.nombre_pagines = nombre_pagines;
	}
	
	// M�tode: toString
    // Descripci� m�tode: Mostra un missatge per pantalla amb la informaci� completa de cada llibre
    // Par�metros de entrada: No
    // Par�metros de salida: Retorna infoComplete, que cont� la informaci� completa de cada llibre
	public String toString() {
		String infoCompleta = "Objeto pelicula -> Id: " + getId() + "Titol: " + getTitol() + "Autor: " + getAutor() + "Any naixement: " + getAny_naixement() + "Any publicacio: " + getAny_publicacio() + "Editorial: " + getEditorial() + "Nombre pagines: " + getNombre_pagines();
		return infoCompleta;
	}
}
