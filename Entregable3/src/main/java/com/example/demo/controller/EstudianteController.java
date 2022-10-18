package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Estudiante;
import com.example.demo.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("estudiantes")
public class EstudianteController {

	@Autowired
	private EstudianteService estudianteService;

	@RequestMapping(value="/", method=RequestMethod.POST,produces="application/json")
	public Estudiante altaEstudiante(@RequestBody Estudiante e) {
		return estudianteService.altaEstudiante(e);
	}
	
	@GetMapping("/")
	public List<Estudiante> obtenerEstudiantesOrdenados() {
		return estudianteService.obtenerEstudiantesOrdenados();
	}

	@GetMapping("/obtenerPorLU/{LU}")
	public Estudiante obtenerEstudiantePorLU(@PathVariable int LU) {
		return estudianteService.obtenerEstudiantePorLU(LU);
	}  
	
	@GetMapping("/obtenerPorGenero/{genero}")
	public List<Estudiante> obtenerEstudiantesPorGenero(@PathVariable String genero){
		return estudianteService.obtenerEstudiantesPorGenero(genero);
	} 

	@GetMapping("/obtenerDeCarreraPorCiudad/{idCarrera}/{ciudad}")
	public List<Estudiante> obtenerEstudiantesDeCarreraPorCiudad(@PathVariable int idCarrera, @PathVariable String ciudad){
		return estudianteService.obtenerEstudiantesDeCarreraPorCiudad(idCarrera, ciudad);
	}

}

	
