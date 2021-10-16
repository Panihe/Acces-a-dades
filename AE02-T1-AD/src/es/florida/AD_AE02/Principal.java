package es.florida.AD_AE02;

import es.florida.AD_AE02.Controlador;
import es.florida.AD_AE02.Model;
import es.florida.AD_AE02.Vista;

public class Principal {
	
	// Métode: main
    // Descripció métode: Crida al model i vista, i li retorne el controlador, model i vista
    // Parámetros de entrada: String[] args pasat per parámetre
    // Parámetros de salida: No
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(model, vista);
	}

}
