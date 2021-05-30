package proyecto;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;

import javax.swing.JOptionPane;

import userInterface.VentanaInicio;

/**
 * Proyecto Final - Main
 * @author Isaac Benjamin Ipenza Retamozo A01228344 / Juan Ramon Benitez Flores A01229673
 *
 */
public class ProyectoMain {
	
	public final static String[] headers = {"Clave","Materia","Requisitos"};
	
	public static GrafoMaterias CsvToGrafo(String fileName) throws FileNotFoundException, Exception {
		CsvMaterias csvMaterias = new  CsvMaterias(fileName, headers);
		ArrayList<Materia> materiasList = csvMaterias.extraerInformacion();
		
		GrafoMaterias grafoMaterias = new GrafoMaterias(materiasList.size());
		grafoMaterias.agregarDatos(materiasList);
		
		return grafoMaterias;
	}
	
	public static void imprimirOrdenTopologico(Hashtable<Integer,Queue<String>> ordenMaterias, int MPS) {
		int semestre = 1;
		for (int i = 1; i < ordenMaterias.size(); i++) {

				System.out.println();
				System.out.println(" \t" + semestre + "° Semestre");
				for (int j = 0; j < MPS && !ordenMaterias.get(i).isEmpty(); j++) {
						System.out.println("--> " + ordenMaterias.get(i).poll());
				}
				
				semestre++;
		}
		
		while(!ordenMaterias.get(0).isEmpty()) {
			System.out.println();
			System.out.println(" \t" + semestre + "° Semestre");
			
			for (int j = 0; j < MPS && !ordenMaterias.get(0).isEmpty(); j++) {
				System.out.println("--> " + ordenMaterias.get(0).poll());
			}
			
			semestre++;

		}
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		
//		String fileName = "IMT.csv";
//		String[] headers = {"Clave","Materia","Requisitos"};
//		
//		CsvMaterias csvMaterias = new  CsvMaterias(fileName, headers);
//		ArrayList<Materia> materiasList = csvMaterias.extraerInformacion();
//		
//		GrafoMaterias grafoMaterias = new GrafoMaterias(materiasList.size());
//		grafoMaterias.agregarDatos(materiasList);
//		
//		System.out.println("Materias cargadas exitosamente!!!");
//		System.out.println();
//
//		System.out.println();
//		System.out.println("Orden de las Materias");
//		System.out.println();
//		
//		String materiasPorSemestre = JOptionPane.showInputDialog("¿Cuantas materias se cursaran por semestre?", 7);
//		int	MPS = Integer.parseInt(materiasPorSemestre);
//			
//		
//		Hashtable<Integer,Queue<String>> ordenMaterias = grafoMaterias.ordenTopologicoIdeal(MPS);
//
//		imprimirOrdenTopologico(ordenMaterias, MPS);
//		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio vInicio = new VentanaInicio();
					vInicio.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
