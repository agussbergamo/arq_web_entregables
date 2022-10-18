package clases;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@SuppressWarnings("serial")
@Embeddable
public class EstudianteCarreraId implements Serializable {

	@Column(name="idEstudiante")
	private int idEstudiante;
	@Column(name="idCarrera")
	private int idCarrera;
	
	public EstudianteCarreraId() {
		super();
	}
	
	public EstudianteCarreraId(int idEstudiante, int idCarrera) {
		super();
		this.idEstudiante = idEstudiante;
		this.idCarrera = idCarrera;
	}
	
	public int getIdEstudiante() {
		return idEstudiante;
	}
	
	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCarrera, idEstudiante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstudianteCarreraId other = (EstudianteCarreraId) obj;
		return idCarrera == other.idCarrera && idEstudiante == other.idEstudiante;
	} 
	
}
