package es.florida.AE6_NoSql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.florida.AE6_NoSql.Model;
import es.florida.AE6_NoSql.Vista;

public class Controlador {
	private Model model;
	private VistaTot vista;
	
	// Métode: controlador
    // Descripció métode: Agafa el model i la vista per a poder utilitzar els botons
    // Parámetros de entrada: Model i vista pasat per parametres
    // Parámetros de salida: No
	Controlador(Model model, VistaTot vista) {
		this.model = model;
		this.vista = vista;
		control();
	}
	
	// Métode: control
    // Descripció métode: Crea els actionListener dels botons buscar i reemplaçar, i mostra el contingut del fitxer en els   	   textArea
    // Parámetros de entrada: No
    // Parámetros de salida: No
	private void control() {
		
	}
	

}