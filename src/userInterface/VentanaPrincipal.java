package userInterface;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import utils.ImprimirOrden;

import java.awt.Font;
import java.util.Hashtable;
import java.util.Queue;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaPrincipal {

	public JFrame frame;
	public Hashtable<Integer,Queue<String>> ordenMaterias;
	int MPS;
	String title;


	/**
	 * Create the application.
	 */
	public VentanaPrincipal(String title,Hashtable<Integer,Queue<String>> ordenMaterias, int MPS) {
		this.title = title;
		this.ordenMaterias = ordenMaterias;
		this.MPS = MPS;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setSize(502, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 23, 398, 281);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(204, 255, 255));
		scrollPane.setViewportView(textArea);
		
		ImprimirOrden.textAreaImprimirOrdenTopologico(textArea, ordenMaterias, MPS);
		
		JButton RegresarButton = new JButton("Regresar");
		RegresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				VentanaInicio vInicio = new VentanaInicio();
				vInicio.frame.setVisible(true);
			}
		});
		RegresarButton.setBounds(43, 353, 89, 23);
		panel.add(RegresarButton);
		
		JButton Pdf_Button = new JButton("Guardar como PDF");
		Pdf_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImprimirOrden.imprimirOrdenTopologicoPdf(textArea, title);
			}
		});
		Pdf_Button.setBounds(295, 353, 148, 23);
		panel.add(Pdf_Button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 204, 204));
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel(title);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	
	
}
