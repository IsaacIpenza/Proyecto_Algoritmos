package proyecto;

/**
 * Clase que nos permite generar objetos de materias para manipularlo de mejor forma la informacion una vez recibida
 * @author Isaac Benjamin Ipenza Retamozo A01228344 / Juan Ramon Benitez Flores A01229673
 *
 */
public class Materia {

	String nombreMateria;
	String clave;
	String[] requisitos;
	
	public Materia(String nombreMateria, String clave, String[] requisitos) {
		
		this.nombreMateria = nombreMateria;
		this.clave = clave;
		this.requisitos = requisitos;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String[] getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String[] requisitos) {
		this.requisitos = requisitos;
	}
	
	
}
