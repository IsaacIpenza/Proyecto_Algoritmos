import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class ProyectoMain {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		
		String fileName = "IMT_invertido.csv";
		String[] headers = {"Clave","Materia","Requisitos"};
		
		CsvMaterias csvMaterias = new  CsvMaterias(fileName, headers);
		ArrayList<Materia> materiasList = csvMaterias.extraerInformacion();
		
		GrafoMaterias grafoMaterias = new GrafoMaterias(materiasList.size());
		grafoMaterias.agregarDatos(materiasList);
		
		System.out.println("Materias cargadas exitosamente!!!");
		System.out.println();

		Queue<String> ordenMaterias = grafoMaterias.ordenTopologicoDirecto();
		
		System.out.println();
		System.out.println("Orden de las Materias");
		System.out.println();
		
		String materiasPorSemestre = JOptionPane.showInputDialog("¿Cuantas materias se cursaran por semestre?", 7);
		int MPS = Integer.parseInt(materiasPorSemestre);
		
		int semestre = 1;
		while(!ordenMaterias.isEmpty()) {
			
			System.out.println();
			System.out.println(" \t" + semestre + "° Semestre");
			
			for (int i = 0; i < MPS && !ordenMaterias.isEmpty(); i++) {
				System.out.println("--> " + ordenMaterias.poll());
			}
			
			semestre++;
		}
	}

	
}
