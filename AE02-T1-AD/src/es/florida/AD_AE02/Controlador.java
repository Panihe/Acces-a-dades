package es.florida.AD_AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.florida.AD_AE02.Model;
import es.florida.AD_AE02.Vista;

public class Controlador {
	private Model model;
	private Vista vista;
	private ActionListener actionListenerBuscar, actionListenerReempla�ar;
	private String fitxerLectura, fitxerEscritura;
	
	// M�tode: controlador
    // Descripci� m�tode: Agafa el model i la vista per a poder utilitzar els botons
    // Par�metros de entrada: Model i vista pasat per parametres
    // Par�metros de salida: No
	Controlador(Model model, Vista vista) {
		this.model = model;
		this.vista = vista;
		control();
	}
	
	// M�tode: control
    // Descripci� m�tode: Crea els actionListener dels botons buscar i reempla�ar, i mostra el contingut del fitxer en els   	   textArea
    // Par�metros de entrada: No
    // Par�metros de salida: No
	private void control() {
		fitxerLectura = model.fitxerLectura();
		fitxerEscritura = model.fitxerEscritura();
		
		mostrarFitxer(fitxerLectura, 1);
		
		actionListenerBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textBuscar = vista.getTextFieldBuscar().getText();
				model.buscarText(textBuscar);
			}
		};
		vista.getBtnBuscar().addActionListener(actionListenerBuscar);
		
		
		actionListenerReempla�ar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textBuscar = vista.getTextFieldBuscar().getText();
				String textReempla�ar = vista.getTextFieldReemplazar().getText();
				model.reempla�arText(textBuscar, textReempla�ar);
				mostrarFitxer(fitxerEscritura,2);
			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerReempla�ar);
	}
	
	private void mostrarFitxer(String fitxer, int numeroTextArea) {
		ArrayList<String> arrayLinees = model.contingutFitxer(fitxer);
		for(String linea : arrayLinees) {
			if(numeroTextArea == 1) {
				vista.getTextAreaOriginal().append(linea+"\n");
			}else {
				vista.getTextAreaModificado().append(linea+"\n");
			}
		}
	}


}
