import java.util.ArrayList;
import java.util.Hashtable;

public class GrafoMaterias {
	
	private int[][] matriz;
	private int size;
	private String[] nombreVertice;
	private Hashtable<String, Integer> posVertice;
	
	private Hashtable<String, String> clave_materia;

	public GrafoMaterias(int numeroDeMaterias) {
		this.size = 0;
		this.matriz = new int[numeroDeMaterias][numeroDeMaterias];  //[origen][destino]
		this.nombreVertice = new String[numeroDeMaterias];
		this.posVertice = new Hashtable<>();
		this.clave_materia = new Hashtable<>();
	}
	
	public void agregarDatos(ArrayList<Materia> materiasList) {
		
		for (Materia materia : materiasList) {
			agregarVertice(materia);
		}
	
		for (Materia materia : materiasList) {
			agregarAristas(materia);
		}
	}
	
	public void agregarVertice(Materia materia) {
		
		this.nombreVertice[this.size] = materia.getNombreMateria();
		this.posVertice.put(materia.nombreMateria, this.size++);
		this.clave_materia.put(materia.getClave(), materia.nombreMateria);
	}
	
	public void agregarAristas(Materia materia) {
		String[] requisitos = materia.getRequisitos();
		
		for (int i = 0; i < requisitos.length; i++) {
			if(!requisitos[i].isEmpty()) {
				if(this.clave_materia.containsKey(requisitos[i])) {
					this.matriz[this.posVertice.get(this.clave_materia.get(requisitos[i]))][this.posVertice.get(materia.getNombreMateria())] = 1;
				}else {
					System.out.println(materia.getClave() + " - " + materia.getNombreMateria()
							+ " tienen un requisito con clave " + requisitos[i] + " que no existe");
				}
			}
		}
	}
	
	public void ordenTopologico() {
		
	}
	
}
