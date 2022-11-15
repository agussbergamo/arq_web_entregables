package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
	
	//Alta estudiante es un post ya definido en JpaRepository
	
	@Query("SELECT e FROM Estudiante e ORDER BY apellido ASC")
	public List<Estudiante> obtenerEstudiantesOrdenados();
	
	@Query("SELECT e FROM Estudiante e WHERE num_libreta = :LU")
	public Estudiante obtenerEstudiantePorLU(int LU);  
	
	@Query("SELECT e FROM Estudiante e WHERE genero = :genero")
	public List<Estudiante> obtenerEstudiantesPorGenero(String genero); 

	@Query("SELECT DISTINCT(e) FROM Estudiante e JOIN e.carreras c WHERE c.id = :idCarrera AND e.ciudad = :ciudad")
	public List<Estudiante> obtenerEstudiantesDeCarreraPorCiudad(int idCarrera, String ciudad);
	
}

