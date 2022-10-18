package interfaces;

import java.util.List;
import clases.Carrera;

public interface InterfaceCarreraDAO {

	void altaCarrera(Carrera c);
	
	public List<Carrera> carrerasConInscriptos();


	
}
