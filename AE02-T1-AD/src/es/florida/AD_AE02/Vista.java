package es.florida.AD_AE02;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class Vista {

	private JFrame frame;
	private JTextField textField_Buscar;
	private JTextField textField_Reemplazar;
	private JButton btnBuscar;
	private JButton btnReemplazar;
	private JTextArea textArea_Original;
	private JTextArea textArea_Modificado;

	
	public Vista() {
		initialize();
	}
	
	// Métode: initialize
    // Descripció métode: Es crea el textarea de dalt, els camps de text de buscar i reemplaçar, els botons de buscar i reemplaçar, i el textarea de baix 
    // Parámetros de entrada: No
    // Parámetros de salida: No
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 799, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_Original = new JScrollPane();
		scrollPane_Original.setBounds(22, 20, 743, 222);
		frame.getContentPane().add(scrollPane_Original);
		
		textArea_Original = new JTextArea();
		textArea_Original.setLineWrap(true);
		textArea_Original.setRows(12);
		scrollPane_Original.setColumnHeaderView(textArea_Original);
		scrollPane_Original.getViewport().setView(textArea_Original);
		
		textField_Buscar = new JTextField();
		textField_Buscar.setBounds(32, 252, 177, 27);
		frame.getContentPane().add(textField_Buscar);
		textField_Buscar.setColumns(10);
		
		textField_Reemplazar = new JTextField();
		textField_Reemplazar.setColumns(10);
		textField_Reemplazar.setBounds(421, 252, 177, 27);
		frame.getContentPane().add(textField_Reemplazar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setBounds(219, 252, 120, 27);
		frame.getContentPane().add(btnBuscar);
		
		btnReemplazar = new JButton("Reemplaçar");
		btnReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReemplazar.setBounds(608, 252, 120, 27);
		frame.getContentPane().add(btnReemplazar);
		
		JScrollPane scrollPane_Modificado = new JScrollPane();
		scrollPane_Modificado.setBounds(22, 300, 743, 222);
		frame.getContentPane().add(scrollPane_Modificado);
		
		textArea_Modificado = new JTextArea();
		textArea_Modificado.setLineWrap(true);
		textArea_Modificado.setRows(12);
		scrollPane_Modificado.setColumnHeaderView(textArea_Modificado);
		scrollPane_Modificado.getViewport().setView(textArea_Modificado);
		
		this.frame.setVisible(true);
	}
	
	// Métode: getBtnBuscar
    // Descripció métode: Obté el botó de buscar
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el botó de buscar
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	// Métode: getBtnReemplazar
    // Descripció métode: Obté el botó de reemplaça
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el botó de reemplaçar
	public JButton getBtnReemplazar() {
		return btnReemplazar;
	}
	
	// Métode: getTextFielsBuscar
    // Descripció métode: Obté el camp de text de buscar
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el camp de text de buscar
	public JTextField getTextFieldBuscar() {
		return textField_Buscar;
	}
	
	// Métode: getTextFieldReemplazar
    // Descripció métode: Obté el camp de text de reemplaçar
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el camp de text de reemplaçar
	public JTextField getTextFieldReemplazar() {
		return textField_Reemplazar;
	}
	
	// Métode: getTextAreaOriginal
    // Descripció métode: Obté el textArea de dalt
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el textArea de baix
	public JTextArea getTextAreaOriginal() {
		return textArea_Original;
	}
	
	// Métode: getTextAreaModificado
    // Descripció métode: Obté el textArea de baix
    // Parámetros de entrada: No
    // Parámetros de salida: Retorna el textArea de baix
	public JTextArea getTextAreaModificado() {
		return textArea_Modificado;
	}

}

