import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Isaac Benjamin Ipenza Retamozo A01228344 / Juan Ramon Benitez Flores A01229673
 *
 */
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
	 * ALGORITMO VORAZ
	 *  Metodo  que genera el ordenTopologico apartir de la cantidad de materias que se quieren llevar por semestre cuidando todo el tiempo
	 *  que no se este cursando ningun requisito a la par.
	 *  Si el valor de MPS es muy grande es muy probable que pocos sean los semestres que si puedan cursar esa cantidad de materias.
	 * @param MPS Numero entero con la cantidad de materias a querer cursar en 1 semestre
	 * @return
	 */
	public Hashtable<Integer,Queue<String>> ordenTopologicoIdeal(int MPS){
		
		Hashtable<Integer,Queue<String>> ordenTopologicoMaterias = new Hashtable<>();
		Queue<String> materiasNoDesbloqueanRequisitos = new LinkedList<String>();
		boolean[] agregado = new boolean[this.size];
		int materiasAgregadas = 0;
		int semestre = 1;
		
		while(materiasAgregadas < this.size){

			Queue<String> materiasDelSemestre = new LinkedList<String>();

			for (int i = 0; i < this.size && materiasAgregadas < this.size; i++) {

				boolean agregar = true;
				boolean desbloqueaMaterias = false;

				if(!agregado[i]) {
					for (int j = 0; j < this.size; j++) {
						if(matriz[i][j] == 1) {
							desbloqueaMaterias = true;
						}

						if(matriz[j][i] == 1 && !agregado[j]) {
							agregar = false;
						}
					}
				


					if(agregar){
						if(desbloqueaMaterias) {
							materiasDelSemestre.add(this.nombreVertice[i]);
						}else {
							materiasNoDesbloqueanRequisitos.add(this.nombreVertice[i]);

						}
						agregado[i] = true;
						materiasAgregadas++;
					}
				}
			}

			while(materiasDelSemestre.size() < MPS && !materiasNoDesbloqueanRequisitos.isEmpty()) {
				materiasDelSemestre.add(materiasNoDesbloqueanRequisitos.poll());
			}
			
			ordenTopologicoMaterias.put(semestre++, materiasDelSemestre);		
		}
		
		ordenTopologicoMaterias.put(0, materiasNoDesbloqueanRequisitos);
		
		return ordenTopologicoMaterias;
	}

	/**
	 * ALGORITMO VORAZ
	 * RESULTADO NO COMPLETAMENTE OPTIMO PARA EL PROBLEMA
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
						if(matriz[j][i] == 1) {		//iteramos los valores de las filas sobre una columna
							agregar = false;		//esta linea  se puede eliminar al tener el break en seguida
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
							matriz[i][j] = 0;
						}
					}
				}
			}
		}

		return ordenMaterias;
	}
	
	/**
	 * ALGORITMO VORAZ
	 * NO ES OPCION COMO SOLUCION PARA LA PROBLEMATICA DE LAS MATERIAS
	 * Metodo que encuentra el orden topologico apartir de cursar las materias segun el orden del csv, 
	 * peso si esta tiene algun requisito se prioriza el completar este requisitos para asi lograr
	 * cursar la materia en la que se estaba.
	 * No considera el no poder cursar materia y sus requisitos al mismo tiempo.
	 * @return Un queue con el orden en el que las materias se deben de cursar.
	 */
	public Queue<String> ordenTopologicoEsValidoEstarCursandoRequisitos() {
		
		Queue<String> ordenMaterias =  new LinkedList<String>();
		boolean[] agregado = new boolean[this.size];
		
		for (int x = 0; x < agregado.length; x++) {
			for (int i = 0; i < this.size; i++) {
				ordenTopologicoRec(ordenMaterias, i, agregado);
			}
		}
		
		return ordenMaterias;
	}
	/**
	 * Metodo auxiliar para lograr completar el metodo de ordenTopologicoNoParalelo()
	 * @param ordenMaterias La queue donde agregaremos las materias en el orden a cursar
	 * @param i Valor del numero de columna en la que estoy del matriz de adyacencias
	 * @param agregado Arreglo que me permite saber que vertices del grafo de materias ya agrege a la lista de materias
	 */
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
