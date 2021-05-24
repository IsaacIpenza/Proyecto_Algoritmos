import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvMaterias {

	String fileName;
	String[] headers;

	public CsvMaterias(String fileName, String[] headers) {
		this.fileName = fileName;
		this.headers = headers;
	}
	
	public ArrayList<Materia> extraerInformacion() throws FileNotFoundException, Exception{
		ArrayList<Materia> materiasList = new ArrayList<>();
		
		try {
			Reader reader = new FileReader(this.fileName);
			CSVParser csvParse = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(this.headers).withSkipHeaderRecord());
			
			for (CSVRecord line : csvParse) {
				Materia materiaTmp = new Materia("", "", null);
				
				materiaTmp.setClave(line.get("Clave"));
				materiaTmp.setNombreMateria(line.get("Materia"));
				materiaTmp.setRequisitos(line.get("Requisitos").split("/"));
				
				materiasList.add(materiaTmp);
			}
			
			csvParse.close();
			reader.close();
			
			return materiasList;
			
		}catch(FileNotFoundException e) {
			throw new FileNotFoundException("Error al abrir el archivo .csv");
		}catch(Exception e) {
			throw new IOException("Error al leer la informacion del documento ");
		}
	}
}
