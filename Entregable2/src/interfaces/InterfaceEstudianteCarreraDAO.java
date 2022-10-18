package interfaces;

import java.util.List;

import clases.Carrera;
import clases.Estudiante;
import clases.EstudianteCarrera;

public interface InterfaceEstudianteCarreraDAO {
	
	void matricularEstudiante(Estudiante e, Carrera c, int inscripcion, int duracion, int antiguedad);

	//List<Carrera> carrerasConInscriptos();
	
}
