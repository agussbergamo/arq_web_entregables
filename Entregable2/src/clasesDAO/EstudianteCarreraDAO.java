package clasesDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import clases.Carrera;
import clases.Estudiante;
import clases.EstudianteCarrera;
import clasesDTO.ReporteDTO;
import interfaces.InterfaceEstudianteCarreraDAO;

public class EstudianteCarreraDAO implements InterfaceEstudianteCarreraDAO {

	@Override
	public void matricularEstudiante(Estudiante e, Carrera c, int inscripcion, int graduacion, int antiguedad) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		EstudianteCarrera ec = new EstudianteCarrera(e, c, inscripcion, graduacion, antiguedad);
		em.persist(ec);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public List<ReporteDTO> generarReporte(){
		List<ReporteDTO> reporte = new ArrayList<ReporteDTO>();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "SELECT ec.inscripcion AS anio, c.nombre, COUNT(ec.inscripcion) AS inscriptos, COUNT(ec.graduacion) AS graduados "
				+ "FROM EstudianteCarrera ec JOIN ec.idCarrera c "
				+ "GROUP BY anio, c.nombre "	
				+ "ORDER BY c.nombre ASC, anio ASC";
		Query query = em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Object[]> respuesta = query.getResultList();
		reporte = respuesta.stream().map(o -> new ReporteDTO((int)o[0], (String)o[1], (Long)o[2], (Long)o[3])).collect(Collectors.toList());
		em.close();
		emf.close();
		return reporte;	
	}

	
}
