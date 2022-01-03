package es.florida.AE6_NoSql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.florida.AE6_NoSql.Model;
import es.florida.AE6_NoSql.Vista;

public class Controlador {
	private Model model;
	private VistaTot vista;
	
	// M�tode: controlador
    // Descripci� m�tode: Agafa el model i la vista per a poder utilitzar els botons
    // Par�metros de entrada: Model i vista pasat per parametres
    // Par�metros de salida: No
	Controlador(Model model, VistaTot vista) {
		this.model = model;
		this.vista = vista;
		control();
	}
	
	// M�tode: control
    // Descripci� m�tode: Crea els actionListener dels botons buscar i reempla�ar, i mostra el contingut del fitxer en els   	   textArea
    // Par�metros de entrada: No
    // Par�metros de salida: No
	private void control() {
		
	}
	

}