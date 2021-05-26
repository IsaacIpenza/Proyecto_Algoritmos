import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class GrafoMaterias {
	
	private int[][] matriz;
	private int size;
	private String[] nombreVertice;
	private Hashtable<String, Integer> posVertice;
	private Hashtable<String, String> clave_materia; // <Clave, Materia>

	public GrafoMaterias(int numeroDeMaterias) {
		this.size = 0;
		this.matriz = new int[numeroDeMaterias][numeroDeMaterias];  //[origen][destino]
		this.nombreVertice = new String[numeroDeMaterias];
		this.posVertice = new Hashtable<>();
		this.clave_materia = new Hashtable<>();
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
	
	public void agregarDatos(ArrayList<Materia> materiasList) {
		
		for (Materia materia : materiasList) {
			agregarVertice(materia);
		}
	
		for (Materia materia : materiasList) {
			agregarAristas(materia);
		}
	}
	
	/**
	 * Metodo que genera el orden topologico de manera directa tomando primero todas aquellas materias
	 * que no tienen requisitos y luego las que ya se cumplio el requisitos y asi sucesivamente.
	 * @return Un queue con el orden en el que las materias se deben de cursar.
	 */
	public Queue<String> ordenTopologico() {

		Queue<String> ordenMaterias =  new LinkedList<String>();
		boolean[] agregado = new boolean[this.size];
		
		boolean agregar;
		int materiasAgregadas = 0;

		while(materiasAgregadas < this.size) {
			for (int i = 0; i < this.size; i++) {
				agregar = true;
				
				if(!agregado[i]) {
					for (int j = 0; j < this.size; j++) {
						if(matriz[j][i] == 1) {  //iteramos los valores de las filas sobre una columna
							agregar = false;
							break;
						}
					}
					
					if(agregar) {
						agregado[i] = true;
						ordenMaterias.add(nombreVertice[i]);
						materiasAgregadas++;
					}
				}
			}
			
			for (int i = 0; i < this.size; i++) {
				if(agregado[i]) {
					for (int j = 0; j < this.size; j++) {
						if(matriz[i][j] == 1) { //iteramos los valores de las columnas sobre una fila
							matriz[i][j] = 0;;
						}
					}
				}
			}
		}

		return ordenMaterias;
	}
	
	/**
	 * Metodo que encuentra el orden topologico apartir de cursar las materias segun el orden del csv, 
	 * peso si esta tiene algun requisito se prioriza el completar este requisitos para asi lograr
	 * cursar la materia en la que se estaba.
	 * No considera el no poder cursar materia y sus requisitos al mismo tiempo.
	 * @return Un queue con el orden en el que las materias se deben de cursar.
	 */
	public Queue<String> ordenTopologicoDirecto() {
		
		Queue<String> ordenMaterias =  new LinkedList<String>();
		boolean[] agregado = new boolean[this.size];
		
		for (int x = 0; x < agregado.length; x++) {
			for (int i = 0; i < this.size; i++) {
				ordenTopologicoRec(ordenMaterias, i, agregado);
			}
		}
		
		return ordenMaterias;
	}
	
	public void ordenTopologicoRec(Queue<String> ordenMaterias, int i, boolean[] agregado) {
		
		if(!agregado[i]) {
			
			for (int j = 0; j < this.size; j++) {
				//Iteramos las filas de una misma columan
				if(matriz[j][i] == 1) {
					ordenTopologicoRec(ordenMaterias, j, agregado);
					matriz[j][i] = 0;
					return;
				}
			}
			
			agregado[i] = true;
			ordenMaterias.add(nombreVertice[i]);
		}
	}
}
