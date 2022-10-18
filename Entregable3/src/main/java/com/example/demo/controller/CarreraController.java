package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Carrera;
import com.example.demo.service.CarreraService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("carreras")
public class CarreraController {

	@Autowired
	private CarreraService carreraService;

	@PostMapping("/")
	public Carrera altaCarrera(@RequestBody Carrera c) {
		return carreraService.altaCarrera(c);
	}
	
	@GetMapping("/")
	public Iterable<Carrera> obtenerCarreras() {
		return carreraService.obtenerCarreras();
	}
	
	@GetMapping("/conInscriptos")
	public List<Carrera> carrerasConInscriptos(){
		return carreraService.carrerasConInscriptos();
	}

}