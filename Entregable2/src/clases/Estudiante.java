package clases;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Estudiante {

	@Id
	private int DNI;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private int edad;
	@Column
	private String genero;
	@Column
	private String ciudad;
	@Column
	private int num_libreta;
	@OneToMany(mappedBy="idEstudiante", cascade = CascadeType.MERGE, orphanRemoval=true, fetch = FetchType.EAGER)
	private List<EstudianteCarrera> carreras;

	public Estudiante() {
		super();
	}

	public Estudiante( int dni, String nombre, String apellido, int edad, String genero, String ciudad, int num_libreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		DNI = dni;
		this.ciudad = ciudad;
		this.num_libreta = num_libreta;
		this.carreras = new ArrayList<EstudianteCarrera>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNum_libreta() {
		return num_libreta;
	}

	public void setNum_libreta(int num_libreta) {
		this.num_libreta = num_libreta;
	}

	public List<EstudianteCarrera> getCarreras() {
		return new ArrayList<EstudianteCarrera>(carreras);
	}

	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero=" + genero
				+ ", DNI=" + DNI + ", ciudad=" + ciudad + ", num_libreta=" + num_libreta + ", carreras=" + carreras
				+ "]\n";
	}

}
