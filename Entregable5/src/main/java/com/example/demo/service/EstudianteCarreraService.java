package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ReporteDTO;
import com.example.demo.entity.Carrera;
import com.example.demo.entity.Estudiante;
import com.example.demo.entity.EstudianteCarrera;
import com.example.demo.repository.CarreraRepository;
import com.example.demo.repository.EstudianteCarreraRepository;
import com.example.demo.repository.EstudianteRepository;

@Service
public class EstudianteCarreraService {
	
	@Autowired
	private EstudianteCarreraRepository estudianteCarreraRepository;
	@Autowired
	private EstudianteRepository estudianteRepository;
	@Autowired
	private CarreraRepository carreraRepository;
	
	public EstudianteCarrera matricular(Map<String, Integer> body) {
		int idEstudiante = body.get("idEstudiante");
		int idCarrera = body.get("idCarrera");
		int inscripcion = body.get("inscripcion");
		int graduacion = body.get("graduacion");
		int antiguedad= body.get("antiguedad");
		Estudiante e = estudianteRepository.findById(idEstudiante).get();
		Carrera c = carreraRepository.findById(idCarrera).get();
		EstudianteCarrera ec = new EstudianteCarrera(e, c, inscripcion, graduacion, antiguedad);
		return estudianteCarreraRepository.save(ec);
	}

	public List<ReporteDTO> generarReporte(){
		return estudianteCarreraRepository.generarReporte();
	}
	
}
