package clasesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import clases.Carrera;
import clases.Estudiante;
import interfaces.InterfaceEstudianteDAO;

public class EstudianteDAO implements InterfaceEstudianteDAO {

	public EstudianteDAO() {
		super();
	}

	@Override
	public void altaEstudiante(Estudiante e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public List<Estudiante> obtenerEstudiantesOrdenados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT e FROM Estudiante e ORDER BY apellido ASC";
		Query query = em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante> estudiantes = query.getResultList();
		em.close();
		emf.close();
		return estudiantes;	
	}
	
	@Override
	public Estudiante obtenerEstudiantePorLU(int LU) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT e FROM Estudiante e WHERE num_libreta = :LU";
		Query query = em.createQuery(jpql);
		Estudiante estudiante = (Estudiante) query.setParameter("LU", LU).getSingleResult();
		em.close();
		emf.close();
		return estudiante;
	}

	/*
	Student s = (Student) em.createQuery("SELECT s FROM Student s WHERE s.LU = :LU").
			    setParameter(ACADEMIC_TRANSCRIPT, LU).getSingleResult();
	em.getTransaction().commit();
	 */

	@Override
	public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT e FROM Estudiante e WHERE genero = :genero";
		Query query = em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante> estudiantes = query.setParameter("genero", genero).getResultList();
		em.close();
		emf.close();
		return estudiantes;	
	}

	@Override
	public List<Estudiante> obtenerEstudiantesDeCarreraPorCiudad(Carrera carrera, String ciudad) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();		
		String jpql = "SELECT DISTINCT(e) FROM Estudiante e, EstudianteCarrera ec WHERE ec.idCarrera = :carrera AND e.ciudad = :ciudad";
		Query query = em.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Estudiante> estudiantes = query.setParameter("carrera", carrera).setParameter("ciudad", ciudad).getResultList();
		em.close();
		emf.close();
		return estudiantes;
	}
}
