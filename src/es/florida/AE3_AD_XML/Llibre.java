package es.florida.AE3_AD_XML;

import org.w3c.dom.NodeList;

public class Llibre {

	private int identificador;
	private String titol;
	private String autor;
	private String any_publicacio;
	private String editorial;
	private String nombre_pagines;

	// Métode: Llibre
	// Descripció métode: Obté tots els valors de id, titol, autor, any_publicacio, editorial, i nombre_pagines del llibres
	// Parámetros de entrada: int identificador, String titol, String autor, String any_publicacio i String editorial pasat párametre
	// Parámetros de salida: No
	public Llibre(int identificador, String titol, String autor, String any_publicacio, String editorial,
			String nombre_pagines) {
		this.identificador = identificador;
		this.titol = titol;
		this.autor = autor;
		this.any_publicacio = any_publicacio;
		this.editorial = editorial;
		this.nombre_pagines = nombre_pagines;
	}
	
	// Métode: getIdentificador
    // Descripció métode: Obté el identificador
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el identificador
	public int getIdentificador() {
		return this.identificador;
	}

	// Métode: setIdentificador
    // Descripció métode: Estableix el identificador obtingut en getIdentificador
    // Parámetros de entrada: int identificador pasat per párametres
    // Parámetros de salida: No
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
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

	// Métode: getAny_Publicacio
    // Descripció métode: Obté el any de publicació
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el any de publicació
	public String getAny_Publicacio() {
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
	public String getNombre_pagines() {
		return this.nombre_pagines;
	}

	// Métode: setNombre_pagines
    // Descripció métode: Estableix el nombre de pagines obtingudes en getNombre_pagines
    // Parámetros de entrada: String nombre_pagines pasat per párametre
    // Parámetros de salida: No
	public void setNombre_pagines(String nombre_pagines) {
		this.nombre_pagines = nombre_pagines;
	}

}
