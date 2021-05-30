package userInterface;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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
	private JScrollPane scrll;
	public Hashtable<Integer,Queue<String>> ordenMaterias;
	int MPS;
	String title;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaPrincipal window = new VentanaPrincipal();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		frame.setBounds(100, 100, 502, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		textAreaImprimirOrdenTopologico(textArea, ordenMaterias, MPS);
		
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
	
	public static void textAreaImprimirOrdenTopologico(JTextArea textArea, Hashtable<Integer,Queue<String>> ordenMaterias, int MPS) {
		
		textArea.append("Como cursar materias: ");	

		int semestre = 1;
		for (int i = 1; i < ordenMaterias.size(); i++) {

			textArea.append("\n");	
			textArea.append("\t" + semestre + "° Semestre \n");	
			for (int j = 0; j < MPS && !ordenMaterias.get(i).isEmpty(); j++) {
				textArea.append("--> " + ordenMaterias.get(i).poll() + "\n");

			}
			semestre++;
		}

		while(!ordenMaterias.get(0).isEmpty()) {

			textArea.append("\n");	
			textArea.append("\t" + semestre + "° Semestre \n");	
			for (int j = 0; j < MPS && !ordenMaterias.get(0).isEmpty(); j++) {
				textArea.append("--> " + ordenMaterias.get(0).poll() + "\n");
			}

			semestre++;

		}
	}
	
	
}
