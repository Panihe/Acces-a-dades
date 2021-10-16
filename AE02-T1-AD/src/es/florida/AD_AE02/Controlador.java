package es.florida.AD_AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import es.florida.AD_AE02.Model;
import es.florida.AD_AE02.Vista;

public class Controlador {
	private Model model;
	private Vista vista;
	private ActionListener actionListenerBuscar, actionListenerReemplaçar;
	private String fitxerLectura, fitxerEscritura;
	
	// Métode: controlador
    // Descripció métode: Agafa el model i la vista per a poder utilitzar els botons
    // Parámetros de entrada: Model i vista pasat per parametres
    // Parámetros de salida: No
	Controlador(Model model, Vista vista) {
		this.model = model;
		this.vista = vista;
		control();
	}
	
	// Métode: control
    // Descripció métode: Crea els actionListener dels botons buscar i reemplaçar, i mostra el contingut del fitxer en els   	   textArea
    // Parámetros de entrada: No
    // Parámetros de salida: No
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
		
		
		actionListenerReemplaçar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String textBuscar = vista.getTextFieldBuscar().getText();
				String textReemplaçar = vista.getTextFieldReemplazar().getText();
				model.reemplaçarText(textBuscar, textReemplaçar);
				mostrarFitxer(fitxerEscritura,2);
			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerReemplaçar);
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
