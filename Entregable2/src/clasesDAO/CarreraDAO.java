package clasesDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import clases.Carrera;
import clases.Estudiante;
import interfaces.InterfaceCarreraDAO;

public class CarreraDAO implements InterfaceCarreraDAO {

	public CarreraDAO() {
		super();
	}
	
	@Override
	public void altaCarrera(Carrera c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public List<Carrera> carrerasConInscriptos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Carrera> carreras = em.createQuery("SELECT DISTINCT c FROM Carrera c JOIN c.estudiantes e "
				+ "WHERE size(e) > 0 ORDER BY size(e)").getResultList();		
		em.close();
		emf.close();
		return carreras;
	}
	
}
