package es.florida.AE5_Hibernate;

public class Llibre {
	private int id, nombre_pagines;
	private String titol, autor, any_naixement, any_publicacio, editorial;
	
	Llibre() {}

	// Métode: Llibre
	// Descripció métode: Obté tots els valors de id, titol, autor, any_naixement, any_publicacio, editorial, i nombre_pagines del llibres
	// Parámetros de entrada: int identificador, String titol, String autor, String any_naixement, String any_publicacio, String editorial e int nombre_pagines pasat párametre
	// Parámetros de salida: No
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
	
	// Métode: Llibre
	// Descripció métode: Obté tots els valors de titol, autor, any_publicacio, editorial, i nombre_pagines del llibres
	// Parámetros de entrada: int identificador, String titol, String autor, String any_publicacio i String editorial pasat párametre
	// Parámetros de salida: No
	public Llibre(String titol, String autor, String any_naixement, String any_publicacio, String editorial,
			int nombre_pagines) {
		this.titol = titol;
		this.autor = autor;
		this.any_naixement = any_naixement;
		this.any_publicacio = any_publicacio;
		this.editorial = editorial;
		this.nombre_pagines = nombre_pagines;
	}
	
	// Métode: getIdentificador
    // Descripció métode: Obté el identificador
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el identificador
	public int getId() {
		return this.id;
	}

	// Métode: setIdentificador
    // Descripció métode: Estableix el identificador obtingut en getIdentificador
    // Parámetros de entrada: int identificador pasat per párametres
    // Parámetros de salida: No
	public void setId(int id) {
		this.id = id;
	}

	// Métode: getTitol
    // Descripció métode: Obté el titol
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el titol
	public String getTitol() {
		return this.titol;
	}

	// Métode: setIdentificador
    // Descripció métode: Estableix el titol obtingut en getTitol
    // Parámetros de entrada: String titol pasat per párametre
    // Parámetros de salida: No
	public void setTitol(String titol) {
		this.titol = titol;
	}

	// Métode: getAutor
    // Descripció métode: Obté el Autor
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el autor
	public String getAutor() {
		return this.autor;
	}

	// Métode: setAutor
    // Descripció métode: Estableix el autor obtingut en getAutor
    // Parámetros de entrada: String autor pasat per párametre
    // Parámetros de salida: No
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	// Métode: getAny_Naixement
    // Descripció métode: Obté el any de naixement
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el any de naixement
	public String getAny_naixement() {
		return this.any_naixement;
	}

	// Métode: setAny_naixement
    // Descripció métode: Estableix el any de publicació obtingut en getAny_naixement
    // Parámetros de entrada: String any_naixment pasat per párametre
    // Parámetros de salida: No
	public void setAny_naixement(String any_naixement) {
		this.any_naixement = any_naixement;
	}

	// Métode: getAny_Publicacio
    // Descripció métode: Obté el any de publicació
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el any de publicació
	public String getAny_publicacio() {
		return this.any_publicacio;
	}

	// Métode: setAny_Publicacio
    // Descripció métode: Estableix el any de publicació obtingut en getAny_publicacio
    // Parámetros de entrada: String any_publicacio pasat per párametre
    // Parámetros de salida: No
	public void setAny_publicacio(String any_publicacio) {
		this.any_publicacio = any_publicacio;
	}

	// Métode: getEditorial
    // Descripció métode: Obté la editorial
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna la editorial
	public String getEditorial() {
		return this.editorial;
	}

	// Métode: setEditorial
    // Descripció métode: Estableix la editorial obtinguda en getEditorial
    // Parámetros de entrada: String editorial pasat per párametre
    // Parámetros de salida: No
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	// Métode: getNombre_pagines
    // Descripció métode: Obté el nombre de pagines 
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el nombre de pagines
	public int getNombre_pagines() {
		return this.nombre_pagines;
	}

	// Métode: setNombre_pagines
    // Descripció métode: Estableix el nombre de pagines obtingudes en getNombre_pagines
    // Parámetros de entrada: String nombre_pagines pasat per párametre
    // Parámetros de salida: No
	public void setNombre_pagines(int nombre_pagines) {
		this.nombre_pagines = nombre_pagines;
	}
	
	// Métode: toString
    // Descripció métode: Mostra un missatge per pantalla amb la informació completa de cada llibre
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna infoComplete, que conté la informació completa de cada llibre
	public String toString() {
		String infoCompleta = "Objeto pelicula -> Id: " + getId() + "Titol: " + getTitol() + "Autor: " + getAutor() + "Any naixement: " + getAny_naixement() + "Any publicacio: " + getAny_publicacio() + "Editorial: " + getEditorial() + "Nombre pagines: " + getNombre_pagines();
		return infoCompleta;
	}
}
