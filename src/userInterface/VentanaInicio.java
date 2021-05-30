package userInterface;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import proyecto.*;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Queue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class VentanaInicio {

	public JFrame frame;
	public String carreraCsv;
	public String carreraName;
	
	private ButtonGroup bg = new ButtonGroup();

	private GrafoMaterias grafoMaterias;
	private Hashtable<Integer,Queue<String>> ordenMaterias;
	
	VentanaPrincipal vPrincipal;
	
	/**
	 * Create the application.
	 */
	public VentanaInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Proyecto Final");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.setBounds(300, 100, 300, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(51, 204, 204));
		frame.getContentPane().add(panel_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel(".. y como las curso?");
		panel_3.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		JLabel lblNewLabel_4 = new JLabel("A01228344 / A01229673");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		frame.getContentPane().add(lblNewLabel_4, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Selecciona o carga una carrera:");
		lblNewLabel_5.setBounds(10, 31, 272, 34);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JRadioButton rdbtnITE = new JRadioButton("ITE");
		rdbtnITE.setBackground(new Color(255, 255, 255));
		rdbtnITE.setSelected(true);
		rdbtnITE.setBounds(0, 83, 282, 23);
		panel_1.add(rdbtnITE);
		rdbtnITE.setHorizontalAlignment(SwingConstants.CENTER);
		bg.add(rdbtnITE);
		
		JRadioButton rdbtnISC = new JRadioButton("ISC");
		rdbtnISC.setBackground(new Color(255, 255, 255));
		rdbtnISC.setBounds(0, 109, 282, 23);
		panel_1.add(rdbtnISC);
		rdbtnISC.setHorizontalAlignment(SwingConstants.CENTER);
		bg.add(rdbtnISC);
		
		JRadioButton rdbtnIMT = new JRadioButton("IMT");
		rdbtnIMT.setBackground(new Color(255, 255, 255));
		rdbtnIMT.setBounds(0, 135, 282, 23);
		panel_1.add(rdbtnIMT);
		rdbtnIMT.setHorizontalAlignment(SwingConstants.CENTER);
		bg.add(rdbtnIMT);
		
		JLabel lblNewLabel_1 = new JLabel("Materias a cursar por semestre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 179, 264, 14);
		panel_1.add(lblNewLabel_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(6), new Integer(0), null, new Integer(1)));
		spinner.setBounds(117, 211, 46, 20);
		panel_1.add(spinner);
		
		JButton SeleccionarButton = new JButton("Seleccionar");
		SeleccionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(rdbtnITE.isSelected()) {
					carreraCsv = "IMT_invertido.csv";
					carreraName = "ITE";
				}else if(rdbtnISC.isSelected()){
					carreraCsv = "IMT_invertido.csv";
					carreraName = "ISC";
				}else if(rdbtnIMT.isSelected()){
					carreraCsv = "IMT.csv";
					carreraName = "IMT";
				}
				
				try {
					grafoMaterias = ProyectoMain.CsvToGrafo(carreraCsv);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int MPS = (Integer) spinner.getValue();
				ordenMaterias = grafoMaterias.ordenTopologicoIdeal(MPS);
				
				frame.dispose();
				
				vPrincipal = new VentanaPrincipal(carreraName, ordenMaterias, MPS);
				vPrincipal.frame.setVisible(true);
				
			}
		});
		SeleccionarButton.setBounds(58, 256, 185, 34);
		panel_1.add(SeleccionarButton);
		
		JButton CargarButton = new JButton("Cargar Plan de Estudios");
		CargarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		CargarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File","csv");
				chooser.setFileFilter(filter);
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					//TODO: Send the path value.
					carreraCsv = chooser.getSelectedFile().getAbsolutePath();
					try {
						grafoMaterias = ProyectoMain.CsvToGrafo(carreraCsv);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					int MPS = (Integer) spinner.getValue();
					ordenMaterias = grafoMaterias.ordenTopologicoIdeal(MPS);
					
					frame.dispose();
					
					String title = "Carrera cargada manualmente";
					vPrincipal = new VentanaPrincipal(title, ordenMaterias, MPS);
					vPrincipal.frame.setVisible(true);
				}
			}
		});
		CargarButton.setBounds(58, 301, 185, 34);
		panel_1.add(CargarButton);
		
		
	}
}
