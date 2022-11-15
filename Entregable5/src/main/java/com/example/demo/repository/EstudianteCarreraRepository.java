package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.ReporteDTO;
import com.example.demo.entity.EstudianteCarrera;

public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Integer> {
	
	//Matricular estudiante es un post ya definido en JpaRepository
	
	@Query("SELECT NEW com.example.demo.dto.ReporteDTO (ec.inscripcion AS anio, c.nombre AS nombreCarrera, COUNT(ec.inscripcion) AS inscriptos, COUNT(ec.graduacion) AS graduados) "
			+ "FROM EstudianteCarrera ec JOIN ec.idCarrera c "
			+ "GROUP BY anio, c.nombre "	
			+ "ORDER BY c.nombre ASC, anio ASC ")
	public List<ReporteDTO> generarReporte();
		
}
