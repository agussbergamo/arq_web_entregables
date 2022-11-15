package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Carrera;
import com.example.demo.service.CarreraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * CarreraController gestiona los servicios de los endpoints de la entidad Carrera.
 * @author Exequiel, Lautaro y Agustina 
 * @version 3.0
 * @see Carrera
 */
@RestController
@RequestMapping("carreras")
public class CarreraController {
    /**
	 * Inyección de dependencias a cargo de Springboot
     */
	@Autowired
	private CarreraService carreraService;

    /**
     * Controlador que provee acceso al servicio encargado de dar de alta una carrera.
     * @param entidad Carrera a dar de alta. 
     * @return Carrera c. 
     */
	@PostMapping("/")
	@Operation(
            summary = "Da de alta una carrera.",
            description = "Servicio encargado de dar de alta una carrera.",
            tags = { "carrera-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carrera.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> altaCarrera(@RequestBody Carrera c){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.altaCarrera(c));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Los campos ingresados son incorrectos, intente nuevamente.\"}");
        }
	}
	
	/**
     * Controlador que provee acceso al servicio encargado de devolver el listado de carreras.
     * @return lista de Carrera. 
     */
	@GetMapping("/")
	@Operation(
            summary = "Retorna el listado de carreras.",
            description = "Servicio encargado de listar todas las carreras.",
            tags = { "carrera-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carrera.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> obtenerCarreras() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.obtenerCarreras());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No existen carreras. \"}");
        }
	}
	
	/**
     * Controlador que provee acceso al servicio encargado de devolver el listado de carreras con inscriptos.
     * @return lista de Carrera. 
     */
	@GetMapping("/conInscriptos")
	@Operation(
            summary = "Retorna el listado de carreras con inscriptos.",
            description = "Servicio encargado de listar las carreras con inscriptos.",
            tags = { "carrera-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carrera.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> carrerasConInscriptos() {
	        try{
	            return ResponseEntity.status(HttpStatus.OK).body(carreraService.carrerasConInscriptos());
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No existen carreras con inscriptos.\"}");
	        }
	}

}