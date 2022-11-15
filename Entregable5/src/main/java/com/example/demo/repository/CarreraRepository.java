package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Integer>{

	//Alta carrera es un post ya definido en JpaRepository

	@Query("SELECT DISTINCT c, size(e) AS inscriptos FROM Carrera c JOIN c.estudiantes e WHERE size(e) > 0 ORDER BY 2 ")
	public List<Carrera> carrerasConInscriptos();

}
