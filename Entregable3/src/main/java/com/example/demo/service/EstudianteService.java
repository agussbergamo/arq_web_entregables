package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Estudiante;
import com.example.demo.repository.EstudianteRepository;

@Service
public class EstudianteService {

	@Autowired
	private EstudianteRepository estudianteRepository;

	public Estudiante altaEstudiante(Estudiante e) {
		return estudianteRepository.save(e);
	}
	
	public List<Estudiante> obtenerEstudiantesOrdenados() {
		return estudianteRepository.obtenerEstudiantesOrdenados();
	}

	public Estudiante obtenerEstudiantePorLU(int LU) {
		return estudianteRepository.obtenerEstudiantePorLU(LU);
	}  
	
	public List<Estudiante> obtenerEstudiantesPorGenero(String genero){
		return estudianteRepository.obtenerEstudiantesPorGenero(genero);
	} 

	public List<Estudiante> obtenerEstudiantesDeCarreraPorCiudad(int idCarrera, String ciudad){
		return estudianteRepository.obtenerEstudiantesDeCarreraPorCiudad(idCarrera, ciudad);
	}
	
}
