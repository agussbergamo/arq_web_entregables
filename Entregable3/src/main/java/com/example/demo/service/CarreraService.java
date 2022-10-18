package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Carrera;
import com.example.demo.repository.CarreraRepository;

@Service
public class CarreraService {

	
	@Autowired
	private CarreraRepository carreraRepository;

	public Carrera altaCarrera(@RequestBody Carrera c) {
		return carreraRepository.save(c);
	}
	
	public Iterable<Carrera> obtenerCarreras() {
		return carreraRepository.findAll();
	}
	
	public List<Carrera> carrerasConInscriptos(){
		return carreraRepository.carrerasConInscriptos();
	}
}
