package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.EstudianteCarrera;
import com.example.demo.service.EstudianteCarreraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * EstudianteCarreraController gestiona los servicios de los endpoints de la entidad EstudianteCarrera.
 * @author Exequiel, Lautaro y Agustina 
 * @version 3.0
 * @see EstudianteCarrera
 */
@RestController
@RequestMapping("estudianteCarrera")
public class EstudianteCarreraController {

    /**
	 * Inyección de dependencias a cargo de Springboot
     */
	@Autowired
	private EstudianteCarreraService estudianteCarreraService;

	/**
     * Controlador que provee acceso al servicio encargado de matricular un estudiante en una carrera.
     * @param entidad EstudianteCarrera a dar de alta. 
     * @return EstudianteCarrera ec. 
     */
	@RequestMapping(value="/matricular", method=RequestMethod.POST,produces="application/json")
	@Operation(
            summary = "Matricular un estudiante en una carrera.",
            description = "Servicio encargado de matricular un estudiante en una carrera.",
            tags = { "estudianteCarrera-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstudianteCarrera.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> matricular(@RequestBody Map<String, Integer> body){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteCarreraService.matricular(body));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Los campos ingresados son incorrectos, intente nuevamente.\"}");
        }
	}
	
	
	/**
     * Controlador que provee acceso al servicio encargado de devolver un listado de carreras con inscriptos.
     * @return lista de Carrera. 
     */
	@GetMapping("/generarReporte")
	@Operation(
            summary = "Retorna el listado de carreras con inscriptos.",
            description = "Servicio encargado de listar carreras con inscriptos.",
            tags = { "estudianteCarrera-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstudianteCarrera.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> carrerasConInscriptos() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteCarreraService.generarReporte());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No existen carreras con inscriptos.\"}");
        }
	}
}