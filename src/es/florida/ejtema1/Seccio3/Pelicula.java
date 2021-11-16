package es.florida.ejtema1.Seccio3;

public class Pelicula {
	int id;
	String titol, director, puntuacio, genere;
	
	Pelicula() {}
	
	public Pelicula(int id, String titol, String director, String puntuacio, String genere) {
		this.id = id;
		this.titol = titol;
		this.director = director;
		this.puntuacio = puntuacio;
		this.genere = genere;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitol() {
		return titol;
	}
	
	public void setTitol(String titol) {
		this.titol = titol;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getPuntuacio() {
		return puntuacio;
	}
	
	public void setPuntuacio(String puntuacio) {
		this.puntuacio = puntuacio;
	}

	public String getGenere() {
		return genere;
	}
	
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	public String toString() {
		String infoCompleta = "Objeto pelicula -> Id: " + id + " - Titol: " + titol + " - Director: " + director + " - Puntuació: " + puntuacio + " - Genere: " + genere;
		return infoCompleta;
	}
}
