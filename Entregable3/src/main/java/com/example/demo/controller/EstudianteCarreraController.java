package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ReporteDTO;
import com.example.demo.entity.EstudianteCarrera;
import com.example.demo.service.EstudianteCarreraService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("estudianteCarrera")
public class EstudianteCarreraController {

	@Autowired
	private EstudianteCarreraService estudianteCarreraService;
	
	@RequestMapping(value="/matricular", method=RequestMethod.POST,produces="application/json")
	public EstudianteCarrera matricular(@RequestBody Map<String, Integer> body) {
		return estudianteCarreraService.matricular(body);
	}

	@GetMapping("/generarReporte")
	public List<ReporteDTO> carrerasConInscriptos(){
		return estudianteCarreraService.generarReporte();
	}
}