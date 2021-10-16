package es.florida.AD_AE02;

import es.florida.AD_AE02.Controlador;
import es.florida.AD_AE02.Model;
import es.florida.AD_AE02.Vista;

public class Principal {
	
	// M�tode: main
    // Descripci� m�tode: Crida al model i vista, i li retorne el controlador, model i vista
    // Par�metros de entrada: String[] args pasat per par�metre
    // Par�metros de salida: No
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(model, vista);
	}

}
