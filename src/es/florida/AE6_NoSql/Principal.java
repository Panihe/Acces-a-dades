package es.florida.AE6_NoSql;

import es.florida.AE6_NoSql.Controlador;
import es.florida.AE6_NoSql.Model;
import es.florida.AE6_NoSql.Vista;

public class Principal {
	
	// M�tode: main
    // Descripci� m�tode: Crida al model i vista, i li retorne el controlador, model i vista
    // Par�metros de entrada: String[] args pasat per par�metre
    // Par�metros de salida: No
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		VistaTot vista = new VistaTot();
		Controlador controlador = new Controlador(model, vista);
	}

}
