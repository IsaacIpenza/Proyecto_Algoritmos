package proyecto;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	
	

	public static void main(String[] args) throws FileNotFoundException, Exception {
		
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
