package es.florida.AE6_NoSql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;
import java.awt.Font;

import static com.mongodb.client.model.Filters.eq;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VistaTot extends JFrame {

	private JPanel contentPane;

	// Métode: dialegMostrarTotsTitols
	// Descripció métode: Obri un dialeg amb un textArea amb tots el titols de la
	// biblioteca, es a dir de le la base de dades.
	// Parámetros de entrada: String resultat pasat párametre
	// Parámetros de salida: No
	public static void dialegMostrarTotsTitols(String resultat) {
		JDialog dialegTotsTitols = new JDialog(new JFrame());
		dialegTotsTitols.setBounds(100, 100, 767, 550);
		dialegTotsTitols.getContentPane().setLayout(null);

		JLabel totsTitols = new JLabel("Tots el titols de la biblioteca");
		totsTitols.setFont(new Font("Roboto", Font.PLAIN, 18));
		totsTitols.setForeground(Color.WHITE);
		totsTitols.setHorizontalAlignment(SwingConstants.CENTER);
		totsTitols.setBounds(249, 33, 284, 36);
		Border margin = new EmptyBorder(20, 0, 10, 0);
		Border border = totsTitols.getBorder();
		totsTitols.setBorder(new CompoundBorder(border, margin));
		dialegTotsTitols.getContentPane().add(totsTitols);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 100, 675, 340);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
				BorderFactory.createEmptyBorder(15, 15, 5, 5)));
		textArea.setVisible(true);
		textArea.setText(resultat);
		dialegTotsTitols.getContentPane().add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(35, 100, 675, 340);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setVisible(true);
		dialegTotsTitols.getContentPane().add(scrollPane);

		dialegTotsTitols.getContentPane().setBackground(new Color(33, 33, 33));
		dialegTotsTitols.setVisible(true);
		dialegTotsTitols.setLocationRelativeTo(null);
	}

	// Métode: mostrarTitols
	// Descripció métode: Mostra un JOptionPane amb un missatge de desitja mostrar
	// tots el titols de la biblioteca si la resposta es que si recorreix tots el
	// llibres i els guarda en una variable i cirda al
	// dialegMostrarTotsTitols(resultat)
	// Parámetros de entrada: Session pasat párametre
	// Parámetros de salida: No
	public static void mostrarTitols() {

		String desig = "";

		desig = JOptionPane.showInputDialog(null, "Desitja mostrar tots els titols de la biblioteca (s/n)?: ",
				"Confirmació mostrar tots el titols", JOptionPane.QUESTION_MESSAGE);

		if (desig.equals("s")) {

			// Mostrar tots els títols de la biblioteca
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
			MongoCollection<Document> coleccion = database.getCollection("Biblioteca");
			
			MongoCursor<Document> cursor = coleccion.find().iterator();
			String resultat = "";
			while (cursor.hasNext()) {
				JSONObject obj = new JSONObject(cursor.next().toJson());
				//System.out.println("Titol " + obj.getInt("Id") + ": " + obj.getString("Titol"));
				resultat += "Llibre " + obj.getInt("Id") + ": \n   Titol --> " + obj.getString("Titol") + "\n\n";
			}
			dialegMostrarTotsTitols(resultat);
		
			mongoClient.close();
		}
	}

	// Métode: dialegMostrarInformacioLlibre
	// Descripció métode: Obri un dialog amb un camp de text per a poder introduir
	// un id, un botó per a mostra la infromació del llibre i un textArea amb tota
	// la informació del llibre
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public static void dialegMostrarInformacioLlibre() {
		String resultat;
		JDialog dialegInformacioLlibre = new JDialog(new JFrame());

		dialegInformacioLlibre.setBounds(100, 100, 767, 550);
		dialegInformacioLlibre.getContentPane().setLayout(null);

		JLabel informacioLlibre = new JLabel("Informació detallada d'un llibre");
		informacioLlibre.setFont(new Font("Roboto", Font.PLAIN, 18));
		informacioLlibre.setForeground(Color.WHITE);
		informacioLlibre.setHorizontalAlignment(SwingConstants.CENTER);
		informacioLlibre.setBounds(245, 33, 284, 36);
		Border margin = new EmptyBorder(20, 0, 10, 0);
		Border border = informacioLlibre.getBorder();
		informacioLlibre.setBorder(new CompoundBorder(border, margin));
		dialegInformacioLlibre.getContentPane().add(informacioLlibre);

		JLabel introdueixId = new JLabel(
				"Introdueix un id per a mostrar la informaci\u00F3 detallada del llibre que correspon a eixe id: ");
		introdueixId.setFont(new Font("Roboto", Font.PLAIN, 14));
		introdueixId.setForeground(Color.WHITE);
		introdueixId.setBounds(100, 99, 581, 36);
		dialegInformacioLlibre.getContentPane().add(introdueixId);

		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblId.setBounds(202, 160, 72, 40);
		dialegInformacioLlibre.getContentPane().add(lblId);

		JTextField textFieldId = new JTextField();
		textFieldId.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldId.setBounds(271, 160, 80, 40);
		textFieldId.setColumns(10);
		dialegInformacioLlibre.getContentPane().add(textFieldId);

		JTextArea textArea = new JTextArea();
		textArea.setRows(10);
		textArea.setBounds(229, 225, 294, 240);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setBorder(null);
		textArea.setBorder(BorderFactory.createCompoundBorder(textArea.getBorder(),
				BorderFactory.createEmptyBorder(15, 15, 5, 5)));

		JButton btnId = new JButton("Mostrar informació");
		btnId.setBackground(new Color(100, 184, 141));
		btnId.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MongoClient mongoClient = new MongoClient("localhost", 27017);
					MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
					MongoCollection<Document> coleccion = database.getCollection("Biblioteca");
					
					int idMostrar = Integer.parseInt(textFieldId.getText());

					Bson query = eq("Id", idMostrar);
					MongoCursor<Document> cursor = coleccion.find(query).iterator();
					String resultat = "";
					if (cursor == null) {
						textFieldId.setText("");
						resultat += "No hi ha ningun llibre amb id = " + idMostrar;
						JOptionPane.showMessageDialog(new JFrame(), "No hi ha ningun llibre amb id = " + idMostrar, "ERROR", JOptionPane.ERROR_MESSAGE);
					} else {
						while (cursor.hasNext()) {
							JSONObject obj = new JSONObject(cursor.next().toJson());
							resultat += "Llibre " + obj.getInt("Id") + ": \n" + "   - Titol: " + obj.getString("Titol")
									+ " \n " + "  - Autor: " + obj.getString("Autor") + " \n " + "  - Any Naixement: "
									+ obj.getString("Any_naixement") + " \n " + "  - Any Publicacio: "
									+ obj.getString("Any_publicacio") + " \n " + "  - Editorial: " + obj.getString("Editorial")
									+ " \n " + "  - Nombre pagines: " + obj.getString("Nombre_pagines") + " \n ";
						}
					}
					
					textArea.setText(resultat);

					textFieldId.setText("");

					mongoClient.close();
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(new JFrame(),
							nfe + "\nEl Id que s'ha posat no es correcte, tens que posar un número", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnId.setBounds(383, 160, 140, 40);
		dialegInformacioLlibre.getContentPane().add(btnId);

		dialegInformacioLlibre.getContentPane().add(textArea);
		dialegInformacioLlibre.getContentPane().setBackground(new Color(33, 33, 33));
		dialegInformacioLlibre.setVisible(true);
		dialegInformacioLlibre.setLocationRelativeTo(null);
	}

	// Métode: mostrarLlibre
	// Descripció métode: Fa una pregunta de si desitja mostrar la informació d'un
	// llibre, si la resposta es si cirda al dialegMostrarInformacioLlibre();
	// Parámetros de entrada: Session session pasat per parametre
	// Parámetros de salida: No
	public static void mostrarLlibre() {

		String desig = "";

		desig = JOptionPane.showInputDialog(null, "Desitja mostrar la informació d'un llibre de la biblioteca (s/n)?: ",
				"Confirmació mostrar informació d'un llibre", JOptionPane.QUESTION_MESSAGE);

		if (desig.equals("s")) {
			dialegMostrarInformacioLlibre();
		}

	}
	
	// Métode: ultimId
	// Descripció métode: Obté el últim Id de la biblioteca, es a dir de la colecció
	// Parámetros de entrada: No
	// Parámetros de salida: Devolveix el id
	private static int ultimId() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
		MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

		int id = 1;
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			id = obj.getInt("Id");
		}
		mongoClient.close();

		return id;
	}

	// Métode: dialegAfegirLlibre
	// Descripció métode: Obri un dialeg amb uns camps de text per a introduir la
	// informacio del llibre que volem afegir
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public static void dialegAfegirLlibre() {
		JDialog dialegAfegirLlibre = new JDialog(new JFrame());

		dialegAfegirLlibre.setBounds(100, 100, 767, 550);
		dialegAfegirLlibre.getContentPane().setLayout(null);

		JLabel afegirLlibre = new JLabel("Afegir llibre");
		afegirLlibre.setFont(new Font("Roboto", Font.PLAIN, 18));
		afegirLlibre.setForeground(Color.WHITE);
		afegirLlibre.setHorizontalAlignment(SwingConstants.CENTER);
		afegirLlibre.setBounds(245, 33, 284, 36);
		Border margin = new EmptyBorder(20, 0, 10, 0);
		Border border = afegirLlibre.getBorder();
		afegirLlibre.setBorder(new CompoundBorder(border, margin));
		dialegAfegirLlibre.add(afegirLlibre);

		JLabel lblTitol = new JLabel("Titol");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setForeground(Color.WHITE);
		lblTitol.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblTitol.setBounds(115, 103, 100, 40);
		dialegAfegirLlibre.add(lblTitol);

		JTextField textFieldTitol = new JTextField();
		textFieldTitol.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldTitol.setBounds(275, 104, 300, 40);
		textFieldTitol.setColumns(10);
		dialegAfegirLlibre.add(textFieldTitol);

		JLabel lblAutor = new JLabel("Autor ");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblAutor.setBounds(119, 156, 100, 40);
		dialegAfegirLlibre.add(lblAutor);

		JTextField textFieldAutor = new JTextField();
		textFieldAutor.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(275, 156, 300, 40);
		dialegAfegirLlibre.add(textFieldAutor);

		JLabel lblAnyNaixement = new JLabel("Any Naixement");
		lblAnyNaixement.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyNaixement.setForeground(Color.WHITE);
		lblAnyNaixement.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblAnyNaixement.setBounds(139, 208, 125, 40);
		dialegAfegirLlibre.add(lblAnyNaixement);

		JTextField textFieldAnyNaixement = new JTextField();
		textFieldAnyNaixement.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldAnyNaixement.setColumns(10);
		textFieldAnyNaixement.setBounds(275, 208, 300, 40);
		dialegAfegirLlibre.add(textFieldAnyNaixement);

		JLabel lblAnyPublicacio = new JLabel("Any Publicaci\u00F3");
		lblAnyPublicacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyPublicacio.setForeground(Color.WHITE);
		lblAnyPublicacio.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblAnyPublicacio.setBounds(135, 262, 125, 40);
		dialegAfegirLlibre.add(lblAnyPublicacio);

		JTextField textFieldAnyPublicacio = new JTextField();
		textFieldAnyPublicacio.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldAnyPublicacio.setColumns(10);
		textFieldAnyPublicacio.setBounds(275, 262, 300, 40);
		dialegAfegirLlibre.add(textFieldAnyPublicacio);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setForeground(Color.WHITE);
		lblEditorial.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblEditorial.setBounds(124, 314, 100, 40);
		dialegAfegirLlibre.add(lblEditorial);

		JTextField textFieldEditorial = new JTextField();
		textFieldEditorial.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBounds(275, 315, 300, 40);
		dialegAfegirLlibre.add(textFieldEditorial);

		JLabel lblNombrepagines = new JLabel("Nombre p\u00E0gines");
		lblNombrepagines.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombrepagines.setForeground(Color.WHITE);
		lblNombrepagines.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblNombrepagines.setBounds(137, 367, 125, 40);
		dialegAfegirLlibre.add(lblNombrepagines);

		JTextField textFieldNombrePagines = new JTextField();
		textFieldNombrePagines.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldNombrePagines.setColumns(10);
		textFieldNombrePagines.setBounds(275, 367, 300, 40);
		dialegAfegirLlibre.add(textFieldNombrePagines);

		JButton btnId = new JButton("Anyadir llibre");
		btnId.setBackground(new Color(100, 184, 141));
		btnId.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoClient mongoClient = new MongoClient("localhost", 27017);
				MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
				MongoCollection<Document> coleccion = database.getCollection("Biblioteca");
				
				int anyadirId = ultimId() + 1;
				String anyadirTitol = textFieldTitol.getText();
				String anyadirAutor = textFieldAutor.getText();
				String anyadirAnyNaixement = textFieldAnyNaixement.getText();
				String anyadirAnyPublicacio = textFieldAnyPublicacio.getText();
				String anyadirEditorial = textFieldEditorial.getText();
				String anyadirNombrePagines = textFieldNombrePagines.getText();

				Document doc = new Document();
				doc.append("Id", anyadirId);
				doc.append("Titol", anyadirTitol);
				doc.append("Autor", anyadirAutor);
				doc.append("Any_naixement", anyadirAnyNaixement);
				doc.append("Any_publicacio", anyadirAnyPublicacio);
				doc.append("Editorial", anyadirEditorial);
				doc.append("Nombre_pagines", anyadirNombrePagines);

				coleccion.insertOne(doc);
				
				mongoClient.close();

				JOptionPane.showMessageDialog(new JFrame(),
						"El llibre amb el titol " + anyadirTitol + " s'ha creat correctament amb id: " + anyadirId, null,
						JOptionPane.INFORMATION_MESSAGE);

				String desig = "";

				desig = JOptionPane.showInputDialog(null, "Desitja afegir altre llibre a la biblioteca (s/n)?: ",
						"Confirmació afegir un llibre", JOptionPane.QUESTION_MESSAGE);
				if (desig.equals("s")) {
					textFieldTitol.setText(null);
					textFieldAutor.setText(null);
					textFieldAnyNaixement.setText(null);
					textFieldAnyPublicacio.setText(null);
					textFieldEditorial.setText(null);
					textFieldNombrePagines.setText(null);

				} else {
					dialegAfegirLlibre.dispose();
				}

			}
		});
		btnId.setBounds(323, 449, 140, 36);
		dialegAfegirLlibre.add(btnId);

		dialegAfegirLlibre.getContentPane().setBackground(new Color(33, 33, 33));
		dialegAfegirLlibre.setVisible(true);
		dialegAfegirLlibre.setLocationRelativeTo(null);
	}

	// Métode: crearLlibre
	// Descripció métode: Fa una pregunta de si desitja afegit un llibre, si la
	// resposta es si cirda al dialegAfegirLlibre();
	// Parámetros de entrada: Session session pasat per parametre
	// Parámetros de salida: No
	public static void crearLlibre() {
		String desig = "";

		desig = JOptionPane.showInputDialog(null, "Desitja afegir un llibre a la biblioteca (s/n)?: ",
				"Confirmació afegir un llibre", JOptionPane.QUESTION_MESSAGE);
		if (desig.equals("s")) {
			dialegAfegirLlibre();
		}
	}

	// Métode: dialegActualitzarLlibre
	// Descripció métode: Obri un dialeg amb un camp de text per a introduir el id
	// del llibre que volem actualitzar les dades i els camp de text per a introduir
	// la nova informació del llibre
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public static void dialegActualitzarLlibre() {
		JDialog dialegActualitzarLlibre = new JDialog(new JFrame());

		dialegActualitzarLlibre.setBounds(100, 100, 767, 550);
		dialegActualitzarLlibre.getContentPane().setLayout(null);

		JLabel lblActualitzarLlibre = new JLabel("Actualitzar llibre");
		lblActualitzarLlibre.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblActualitzarLlibre.setForeground(Color.WHITE);
		lblActualitzarLlibre.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualitzarLlibre.setBounds(249, 33, 284, 36);
		Border margin = new EmptyBorder(20, 0, 10, 0);
		Border border = lblActualitzarLlibre.getBorder();
		lblActualitzarLlibre.setBorder(new CompoundBorder(border, margin));
		dialegActualitzarLlibre.getContentPane().add(lblActualitzarLlibre);

		JLabel introdueixId = new JLabel(
				"Introdueix un id per a actualitzar la informaci\u00F3 detallada del llibre que correspon a eixe id: ");
		introdueixId.setFont(new Font("Roboto", Font.PLAIN, 14));
		introdueixId.setForeground(Color.WHITE);
		introdueixId.setBounds(51, 82, 563, 36);
		dialegActualitzarLlibre.add(introdueixId);

		JTextField textFieldId = new JTextField();
		textFieldId.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldId.setBounds(610, 81, 80, 40);
		textFieldId.setColumns(10);
		dialegActualitzarLlibre.add(textFieldId);

		JLabel lblTitol = new JLabel("Titol");
		lblTitol.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitol.setForeground(Color.WHITE);
		lblTitol.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblTitol.setBounds(116, 130, 100, 40);
		dialegActualitzarLlibre.add(lblTitol);

		JTextField textFieldTitol = new JTextField();
		textFieldTitol.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldTitol.setBounds(276, 131, 300, 40);
		textFieldTitol.setColumns(10);
		dialegActualitzarLlibre.add(textFieldTitol);

		JLabel lblAutor = new JLabel("Autor ");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblAutor.setBounds(120, 183, 100, 40);
		dialegActualitzarLlibre.add(lblAutor);

		JTextField textFieldAutor = new JTextField();
		textFieldAutor.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(276, 183, 300, 40);
		dialegActualitzarLlibre.add(textFieldAutor);

		JLabel lblAnyNaixement = new JLabel("Any Naixement");
		lblAnyNaixement.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyNaixement.setForeground(Color.WHITE);
		lblAnyNaixement.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblAnyNaixement.setBounds(140, 235, 125, 40);
		dialegActualitzarLlibre.add(lblAnyNaixement);

		JTextField textFieldAnyNaixement = new JTextField();
		textFieldAnyNaixement.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldAnyNaixement.setColumns(10);
		textFieldAnyNaixement.setBounds(276, 235, 300, 40);
		dialegActualitzarLlibre.add(textFieldAnyNaixement);

		JLabel lblAnyPublicacio = new JLabel("Any Publicaci\u00F3");
		lblAnyPublicacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnyPublicacio.setForeground(Color.WHITE);
		lblAnyPublicacio.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblAnyPublicacio.setBounds(136, 289, 125, 40);
		dialegActualitzarLlibre.add(lblAnyPublicacio);

		JTextField textFieldAnyPublicacio = new JTextField();
		textFieldAnyPublicacio.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldAnyPublicacio.setColumns(10);
		textFieldAnyPublicacio.setBounds(276, 289, 300, 40);
		dialegActualitzarLlibre.add(textFieldAnyPublicacio);

		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditorial.setForeground(Color.WHITE);
		lblEditorial.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblEditorial.setBounds(125, 341, 100, 40);
		dialegActualitzarLlibre.add(lblEditorial);

		JTextField textFieldEditorial = new JTextField();
		textFieldEditorial.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBounds(276, 342, 300, 40);
		dialegActualitzarLlibre.add(textFieldEditorial);

		JLabel lblNombrepagines = new JLabel("Nombre p\u00E0gines");
		lblNombrepagines.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombrepagines.setForeground(Color.WHITE);
		lblNombrepagines.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblNombrepagines.setBounds(138, 394, 125, 40);
		dialegActualitzarLlibre.add(lblNombrepagines);

		JTextField textFieldNombrePagines = new JTextField();
		textFieldNombrePagines.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldNombrePagines.setColumns(10);
		textFieldNombrePagines.setBounds(276, 394, 300, 40);
		dialegActualitzarLlibre.add(textFieldNombrePagines);

		JButton btnActualitzar = new JButton("Actualitzar llibre");
		btnActualitzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoClient mongoClient = new MongoClient("localhost", 27017);
				MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
				MongoCollection<Document> coleccion = database.getCollection("Biblioteca");

				int id = Integer.parseInt(textFieldId.getText());
				String actualitzarTitol = textFieldTitol.getText();
				String actualitzarAutor = textFieldAutor.getText();
				String actualitzarAnyNaixement = textFieldAnyNaixement.getText();
				String actualitzarAnyPublicacio = textFieldAnyPublicacio.getText();
				String actualitzarEditorial = textFieldEditorial.getText();
				String actualitzarNombrePagines = textFieldNombrePagines.getText();

				Bson filtreId = eq("Id", id);
				if (filtreId == null) {
					JOptionPane.showMessageDialog(new JFrame(), "No hi ha ningun llibre amb id = " + id,
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					coleccion.updateOne(filtreId, new Document("$set", new Document("Titol", actualitzarTitol)));
					coleccion.updateOne(filtreId, new Document("$set", new Document("Autor", actualitzarAutor)));
					actualitzarAnyNaixement = actualitzarAnyNaixement.equals("") ? "N.C" : actualitzarAnyNaixement;
					coleccion.updateOne(filtreId, new Document("$set", new Document("Any_naixement", actualitzarAnyNaixement)));
					coleccion.updateOne(filtreId, new Document("$set", new Document("Any_publicacio", actualitzarAnyPublicacio)));
					coleccion.updateOne(filtreId, new Document("$set", new Document("Editorial", actualitzarEditorial)));
					coleccion.updateOne(filtreId, new Document("$set", new Document("Nombre_pagines", actualitzarNombrePagines)));
					
					JOptionPane.showMessageDialog(new JFrame(), "S'han actualitzat les dades del llibre amb id: " + id,
							null, JOptionPane.INFORMATION_MESSAGE);

					String desig = "";

					desig = JOptionPane.showInputDialog(null,
							"Desitja actualitzar altre llibre de la biblioteca (s/n)?: ",
							"Confirmació afegir un llibre", JOptionPane.QUESTION_MESSAGE);
					if (desig.equals("s")) {
						textFieldId.setText(null);
						textFieldTitol.setText(null);
						textFieldAutor.setText(null);
						textFieldAnyNaixement.setText(null);
						textFieldAnyPublicacio.setText(null);
						textFieldEditorial.setText(null);
						textFieldNombrePagines.setText(null);
					} else {
						dialegActualitzarLlibre.dispose();
					}
				}
			}
		});
		btnActualitzar.setBackground(new Color(100, 184, 141));
		btnActualitzar.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnActualitzar.setBounds(321, 456, 140, 36);
		dialegActualitzarLlibre.add(btnActualitzar);

		dialegActualitzarLlibre.getContentPane().setBackground(new Color(33, 33, 33));
		dialegActualitzarLlibre.setVisible(true);
		dialegActualitzarLlibre.setLocationRelativeTo(null);
	}

	// Métode: actualitzarLlibre
	// Descripció métode: Fa una pregunta de si desitja actulitzar les dades d'un
	// llibre, si la resposta es si cirda al dialegActualitzarLlibre()
	// Parámetros de entrada: Session session pasat per parametre
	// Parámetros de salida: No
	public static void actualitzarLlibre() {
		String desig = "";

		desig = JOptionPane.showInputDialog(null, "Desitja actualitzar les dades d'un llibre de la biblioteca (s/n)?: ",
				"Confirmació afegir un llibre", JOptionPane.QUESTION_MESSAGE);
		if (desig.equals("s")) {
			dialegActualitzarLlibre();
		}
	}

	// Métode: dialegEsborrarLlibre
	// Descripció métode: Obri un dialeg amb un camp de text per a introduir el id
	// del llibre que volem esborrar de la biblioteca
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public static void dialegEsborrarLlibre() {
		JDialog dialegEsborrarLlibre = new JDialog(new JFrame());

		dialegEsborrarLlibre.setBounds(100, 100, 767, 550);
		dialegEsborrarLlibre.getContentPane().setLayout(null);

		JLabel esborrarLlibre = new JLabel("Esborrar un llibre");
		esborrarLlibre.setFont(new Font("Roboto", Font.PLAIN, 18));
		esborrarLlibre.setForeground(Color.WHITE);
		esborrarLlibre.setHorizontalAlignment(SwingConstants.CENTER);
		esborrarLlibre.setBounds(245, 33, 284, 36);
		Border margin = new EmptyBorder(20, 0, 10, 0);
		Border border = esborrarLlibre.getBorder();
		esborrarLlibre.setBorder(new CompoundBorder(border, margin));
		dialegEsborrarLlibre.getContentPane().add(esborrarLlibre);

		JLabel introdueixId = new JLabel("Introdueix un id per a esborrar el llibre que correspon a eixe id: ");
		introdueixId.setFont(new Font("Roboto", Font.PLAIN, 14));
		introdueixId.setForeground(Color.WHITE);
		introdueixId.setBounds(202, 107, 474, 36);
		dialegEsborrarLlibre.getContentPane().add(introdueixId);

		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblId.setBounds(301, 186, 72, 40);
		dialegEsborrarLlibre.getContentPane().add(lblId);

		JTextField textFieldEsborrarId = new JTextField();
		textFieldEsborrarId.setFont(new Font("Roboto", Font.PLAIN, 12));
		textFieldEsborrarId.setBounds(373, 188, 80, 40);
		textFieldEsborrarId.setColumns(10);
		dialegEsborrarLlibre.getContentPane().add(textFieldEsborrarId);

		JButton btnId = new JButton("Esborrar llibre");
		btnId.setBackground(new Color(100, 184, 141));
		btnId.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoClient mongoClient = new MongoClient("localhost", 27017);
				MongoDatabase database = mongoClient.getDatabase("DAM_MongoDB");
				MongoCollection<Document> coleccion = database.getCollection("Biblioteca");
				
				int idEsborrar = Integer.parseInt(textFieldEsborrarId.getText());

				try {
					try {
						Bson filtreId = eq("Id", idEsborrar);
						coleccion.deleteOne(filtreId);
						
						dialegEsborrarLlibre();
						
						// correctament de la biblioteca");
						JOptionPane.showMessageDialog(new JFrame(),
								"El llibre amb id " + idEsborrar + " s'ha esborrat correctament de la biblioteca", null,
								JOptionPane.INFORMATION_MESSAGE);
						
						String desig = "";

						desig = JOptionPane.showInputDialog(null,
								"Desitja esborrar altre llibre de la biblioteca (s/n)?: ",
								"Confirmació afegir un llibre", JOptionPane.QUESTION_MESSAGE);
						if (desig.equals("s")) {
							textFieldEsborrarId.setText(null);
						} else {
							dialegEsborrarLlibre.dispose();
						}
						
					} catch (Exception e2) {
						// System.out.println("No s'ha pogut esborrar el llibre amb id " + idEsborrar +
						// " de la biblioteca");
				
						JOptionPane.showMessageDialog(new JFrame(),
								"No s'ha pogut esborrar el llibre amb id " + idEsborrar + " de la biblioteca \n",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(new JFrame(),
							nfe + "\nEl Id que s'ha posat no es correcte, tens que posar un número", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} catch (IllegalStateException ilex) {
					JOptionPane.showMessageDialog(new JFrame(),
							ilex + "\nEl Id que s'ha posat no es correcte, tens que posar un número", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				
				mongoClient.close();
			}
		});
		btnId.setBounds(323, 270, 140, 36);
		dialegEsborrarLlibre.getContentPane().add(btnId);

		dialegEsborrarLlibre.getContentPane().setBackground(new Color(33, 33, 33));
		dialegEsborrarLlibre.setVisible(true);
		dialegEsborrarLlibre.setLocationRelativeTo(null);
	}

	// Métode: borrarLlibre
	// Descripció métode: Fa una pregunta de si desitja esborrar un llibre, si la
	// resposta es si crida al dialegEsborrarLlibre();
	// Parámetros de entrada: Session session pasat per parametre
	// Parámetros de salida: No
	public static void borrarLlibre() {
		String confirmacio = "";

		confirmacio = JOptionPane.showInputDialog(null, "Desitja esborrar un llibre de la biblioteca (s/n)?: ", "Confirmació esborrar un llibre", JOptionPane.QUESTION_MESSAGE);

		if (confirmacio.equals("s")) {
			dialegEsborrarLlibre();
		}
	}

	// Métode: borrarLlibre
	// Descripció métode: Fa una pregunta de si desitja tancar la biblioteca, si la
	// resposta es si mostra un missatge S'ha tancat la biblioteca\nMoltes gracies
	// per utilitzarla i es tanca la aplicació
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public static void tancarBiblioteca() {
		String confirmacio = "";

		confirmacio = JOptionPane.showInputDialog(null, "Desitja tancar la biblioteca (s/n)?: ", "Confirmació tancar biblioteca", JOptionPane.QUESTION_MESSAGE);

		if (confirmacio.equals("s")) {
			JOptionPane.showMessageDialog(new JFrame(), "S'ha tancat la biblioteca\nMoltes gracies per utilitzarla", null, JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	/**
	 * Create the frame.
	 */

	// Métode: vista
	// Descripció métode: Crida a visualitzar
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public VistaTot() {
		Visualitzar();
	}

	// Métode: visualitzar
	// Descripció métode: Açí es la vista principal de la aplicació, es a dir, el
	// menú amb el botons de cadascuna de les funcionalitats
	// Parámetros de entrada: No
	// Parámetros de salida: No
	public void Visualitzar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AE6 - MongoDB - Menú de la biblioteca");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Biblioteca.class.getResource("/img/icon-mongodb.png"))); // ICONA DEL PROGRAMA
		setBounds(100, 100, 767, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(33, 33, 33));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titolBenvingut = new JLabel("Benvingut a la biblioteca");
		titolBenvingut.setFont(new Font("Roboto Medium", Font.PLAIN, 21));
		titolBenvingut.setHorizontalAlignment(SwingConstants.CENTER);
		titolBenvingut.setForeground(Color.WHITE);
		titolBenvingut.setBounds(245, 33, 284, 36);
		contentPane.add(titolBenvingut);

		JLabel opcio = new JLabel("Quina opci\u00F3 vols fer?");
		opcio.setFont(new Font("Roboto", Font.PLAIN, 18));
		opcio.setForeground(Color.WHITE);
		opcio.setHorizontalAlignment(SwingConstants.CENTER);
		opcio.setBounds(286, 81, 201, 16);
		contentPane.add(opcio);

		JButton btnTotsTitols = new JButton("Mostrar tots els titols");

		btnTotsTitols.setBackground(new Color(100, 184, 141));
		btnTotsTitols.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnTotsTitols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTitols();
			}
		});
		btnTotsTitols.setBorder(null);
		btnTotsTitols.setFocusable(false);
		btnTotsTitols.setFocusPainted(false);
		btnTotsTitols.setBounds(286, 129, 201, 38);
		contentPane.add(btnTotsTitols);

		JButton btnInformacioDetallada = new JButton("Mostrar informaci\u00F3 ");
		btnInformacioDetallada.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnInformacioDetallada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLlibre();
			}
		});
		btnInformacioDetallada.setFocusable(false);
		btnInformacioDetallada.setFocusPainted(false);
		btnInformacioDetallada.setBorder(null);
		btnInformacioDetallada.setBackground(new Color(100, 184, 141));
		btnInformacioDetallada.setBounds(286, 186, 201, 38);
		contentPane.add(btnInformacioDetallada);

		JButton btnAfegirLlibre = new JButton("Afegir llibre");
		btnAfegirLlibre.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnAfegirLlibre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearLlibre();
			}
		});
		btnAfegirLlibre.setFocusable(false);
		btnAfegirLlibre.setFocusPainted(false);
		btnAfegirLlibre.setBorder(null);
		btnAfegirLlibre.setBackground(new Color(100, 184, 141));
		btnAfegirLlibre.setBounds(286, 243, 201, 38);
		contentPane.add(btnAfegirLlibre);

		JButton btnActualitzarLlibre = new JButton("Actualitzar llibre");
		btnActualitzarLlibre.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnActualitzarLlibre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualitzarLlibre();
			}
		});
		btnActualitzarLlibre.setFocusable(false);
		btnActualitzarLlibre.setFocusPainted(false);
		btnActualitzarLlibre.setBorder(null);
		btnActualitzarLlibre.setBackground(new Color(100, 184, 141));
		btnActualitzarLlibre.setBounds(286, 300, 201, 38);
		contentPane.add(btnActualitzarLlibre);

		JButton btnEsborrarLlibre = new JButton("Esborrar llibre");
		btnEsborrarLlibre.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnEsborrarLlibre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarLlibre();
			}
		});
		btnEsborrarLlibre.setFocusable(false);
		btnEsborrarLlibre.setFocusPainted(false);
		btnEsborrarLlibre.setBorder(null);
		btnEsborrarLlibre.setBackground(new Color(100, 184, 141));
		btnEsborrarLlibre.setBounds(286, 357, 201, 38);
		contentPane.add(btnEsborrarLlibre);

		JButton btnTancarBiblioteca = new JButton("Tancar biblioteca");
		btnTancarBiblioteca.setFont(new Font("Roboto", Font.PLAIN, 14));
		btnTancarBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tancarBiblioteca();
			}
		});
		btnTancarBiblioteca.setFocusable(false);
		btnTancarBiblioteca.setFocusPainted(false);
		btnTancarBiblioteca.setBorder(null);
		btnTancarBiblioteca.setBackground(new Color(100, 184, 141));
		btnTancarBiblioteca.setBounds(286, 414, 201, 38);
		contentPane.add(btnTancarBiblioteca);

		JLabel Alumne = new JLabel("Alumne: Pau Nicol\u00E1s Hern\u00E1ndez");
		Alumne.setHorizontalAlignment(SwingConstants.LEFT);
		Alumne.setFont(new Font("Roboto Light", Font.PLAIN, 12));
		Alumne.setForeground(Color.WHITE);
		Alumne.setBounds(18, 475, 217, 16);
		contentPane.add(Alumne);

	}



	/**
	 * Launch the application.
	 */

	// Métode: main
	// Descripció métode: Crida a configuracio i crea un frame i el fa visible
	// Parámetros de entrada: String[] args
	// Parámetros de salida: No
	public static void main(String[] args) {
		try {
			// Set cross-platform Java L&F (also called "Nimbus")
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		VistaTot frame = new VistaTot();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
