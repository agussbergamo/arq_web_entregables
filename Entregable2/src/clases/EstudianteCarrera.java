package clases;

import javax.persistence.*;

@Entity
public class EstudianteCarrera {

	@EmbeddedId
	private EstudianteCarreraId id;
	@ManyToOne
	@JoinColumn(name="idEstudiante", insertable = false, updatable = false)
	private Estudiante idEstudiante;
	@ManyToOne
	@JoinColumn(name="idCarrera", insertable = false, updatable = false)
	private Carrera idCarrera;
	@Column
	private int inscripcion;
	@Column
	private int graduacion;  
	@Column ()
	private int antiguedad;
	
	public EstudianteCarrera() {
		super();
	}

	public EstudianteCarrera(Estudiante idEstudiante, Carrera idCarrera, int inscripcion,
			int graduacion, int antiguedad) {
		this.id = new EstudianteCarreraId(idEstudiante.getDNI(), idCarrera.getId());
		this.idEstudiante = idEstudiante;
		this.idCarrera = idCarrera;
		this.inscripcion = inscripcion;
		this.graduacion = graduacion;
		this.antiguedad = antiguedad;
	}

	public EstudianteCarreraId getId() {
		return id;
	}

	public Estudiante getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Estudiante estudiante) {
		this.idEstudiante = estudiante;
	}

	public Carrera getCarrera() {
		return idCarrera;
	}

	public void setCarrera(Carrera carrera) {
		this.idCarrera = carrera;
	}

	public int getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(int inscripcion) {
		this.inscripcion = inscripcion;
	}

	public int getGraduacion() {
		return graduacion;
	}

	public void setGraduacion(int graduacion) {
		this.graduacion = graduacion;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	} 
	
	
	
}
