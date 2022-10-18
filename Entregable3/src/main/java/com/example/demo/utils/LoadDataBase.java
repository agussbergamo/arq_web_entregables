package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.Carrera;
import com.example.demo.entity.Estudiante;
import com.example.demo.entity.EstudianteCarrera;
import com.example.demo.repository.CarreraRepository;
import com.example.demo.repository.EstudianteCarreraRepository;
import com.example.demo.repository.EstudianteRepository;


@Configuration
public class LoadDataBase {

	private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	CommandLineRunner initDatabase(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, EstudianteCarreraRepository estudianteCarreraRepository) {
		return args -> {
			Carrera c1 = new Carrera(1, "TUDAI", 2);
			Carrera c2 = new Carrera(2, "TUARI", 2);
			Carrera c3 = new Carrera(3, "Ingenieria_sistemas", 5);
			Estudiante e1 = new Estudiante(34146358, "Leonela", "Leonela", 33, "no-binario", "Tandil", 1234);
			Estudiante e2 = new Estudiante(45999999, "Exequiel", "Ladri", 42, "macho", "Pehuajo", 1568);
			Estudiante e3 = new Estudiante(12589647, "Lautaro", "Lautaro", 31, "masculino", "Humahuaca", 4567);
			EstudianteCarrera ec1 = new EstudianteCarrera(e1, c1, 2021, 2022, 2);
			EstudianteCarrera ec2 = new EstudianteCarrera(e2, c1, 2021, 2022, 2);
			EstudianteCarrera ec3 = new EstudianteCarrera(e2, c2, 2021, 2022, 2);
			EstudianteCarrera ec4 = new EstudianteCarrera(e3, c1, 2021, 2022, 2);
			log.info("Preloading " + carreraRepository.save(c1));
			log.info("Preloading " + carreraRepository.save(c2));
			log.info("Preloading " + carreraRepository.save(c3));
			log.info("Preloading " + estudianteRepository.save(e1));
			log.info("Preloading " + estudianteRepository.save(e2));
			log.info("Preloading " + estudianteRepository.save(e3));
			log.info("Preloading " + estudianteCarreraRepository.save(ec1));
			log.info("Preloading " + estudianteCarreraRepository.save(ec2));
			log.info("Preloading " + estudianteCarreraRepository.save(ec3));
			log.info("Preloading " + estudianteCarreraRepository.save(ec4));
			
		};
	}
}
