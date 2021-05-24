import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProyectoMain {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		
		String fileName = "IMT.csv";
		String[] headers = {"Clave","Materia","Requisitos"};
		
		CsvMaterias csvMaterias = new  CsvMaterias(fileName, headers);
		ArrayList<Materia> materiasList = csvMaterias.extraerInformacion();
		
		GrafoMaterias grafo = new GrafoMaterias(materiasList.size());
		grafo.agregarDatos(materiasList);
		
		System.out.println("Materias cargadas exitosamente!!!");
		
		
		
	}

	
}
