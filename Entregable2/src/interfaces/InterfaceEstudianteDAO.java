package interfaces;

import java.util.List;

import clases.Carrera;
import clases.Estudiante;

public interface InterfaceEstudianteDAO {
	
	void altaEstudiante(Estudiante e);
	
	List<Estudiante> obtenerEstudiantesOrdenados();
	
	Estudiante obtenerEstudiantePorLU(int LU);
	
	List<Estudiante> obtenerEstudiantesPorGenero(String genero);

	List<Estudiante> obtenerEstudiantesDeCarreraPorCiudad(Carrera carrera, String ciudad);

}
