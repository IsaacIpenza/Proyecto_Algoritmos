package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 


/**
 * Clase con metodos auxiliares que nos  permiten imprimir el orden  topologico en diversos puntos del  programa
 *  @author Isaac Benjamin Ipenza Retamozo A01228344 / Juan Ramon Benitez Flores A01229673
 *
 */
public class ImprimirOrden {

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

	public static void textAreaImprimirOrdenTopologico(JTextArea textArea, Hashtable<Integer,Queue<String>> ordenMaterias, int MPS) {

		textArea.append("Como cursar tus materias: \n");	

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

	public static void imprimirOrdenTopologicoPdf(JTextArea textArea, String name) {

		String archivo = System.getProperty("user.home") +"\\" + name +".pdf";
		Document document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
			document.open();
			document.add(new Paragraph(textArea.getText()));
			document.close();
			writer.close();
			
			JOptionPane.showMessageDialog(null, "Se guardo en PDF exitosamente!!!");
		}catch (DocumentException e){
			e.printStackTrace();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
