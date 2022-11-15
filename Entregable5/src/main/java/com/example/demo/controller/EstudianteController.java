package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Estudiante;
import com.example.demo.service.EstudianteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * EstudianteController gestiona los servicios de los endpoints de la entidad Estudiante.
 * @author Exequiel, Lautaro y Agustina 
 * @version 3.0
 * @see Estudiante
 */
@RestController
@RequestMapping("estudiantes")
public class EstudianteController {

    /**
	 * Inyección de dependencias a cargo de Springboot
     */
	@Autowired
	private EstudianteService estudianteService;

	/**
     * Controlador que provee acceso al servicio encargado de dar de alta un estudiante.
     * @param entidad Estudiante a dar de alta. 
     * @return Estudiante e. 
     */
	@RequestMapping(value="/", method=RequestMethod.POST,produces="application/json")
	@Operation(
            summary = "Da de alta un estudiante.",
            description = "Servicio encargado de dar de alta un estudiante.",
            tags = { "estudiante-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estudiante.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> altaEstudiante(@RequestBody Estudiante es){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.altaEstudiante(es));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Los campos ingresados son incorrectos, intente nuevamente.\"}");
        }
	}
	
	/**
     * Controlador que provee acceso al servicio encargado de devolver un listado de estudiantes ordenados.
     * @return lista de Estudiante. 
     */
	@GetMapping("/")
	@Operation(
            summary = "Retorna el listado de estudiantes.",
            description = "Servicio encargado de listar estudiantes ordenados alfabéticamente por apellido.",
            tags = { "estudiante-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estudiante.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> obtenerEstudiantesOrdenados() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.obtenerEstudiantesOrdenados());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No existen estudiantes. \"}");
        }
	}
	
	
    /**
     * Controlador que provee acceso al servicio encargado de retornar un estudiante por su número de libreta universitaria.
     * @param string libreta universitaria.
     * @return Estudiante e.
     */
	@GetMapping("/obtenerPorLU/{LU}")
    @Operation(
            summary = "Devuelve un estudiante según número de libreta universitaria.",
            description = "Servicio encargado de retornar un estudiante según LU ingresada por parámetro.",
            tags = { "Estudiante-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estudiante.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> obtenerEstudiantePorLU(@PathVariable int LU){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.obtenerEstudiantePorLU(LU));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el estudiante.\"}");
        }
    }

	/**
     * Controlador que provee acceso al servicio encargado de retornar una lista de estudiantes según género.
     * @param string género
     * @return lista de Estudiante.
     */
	@GetMapping("/obtenerPorGenero/{genero}")
    @Operation(
            summary = "Devuelve una lista de estudiantes por género.",
            description = "Servicio encargado de retornar una lista de estudiantes según género ingresado por parámetro.",
            tags = { "Estudiante-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estudiante.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> obtenerEstudiantesPorGenero(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.obtenerEstudiantesPorGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentran estudiantes con el género ingresado.\"}");
        }
    }
	
	
	/**
     * Controlador que provee acceso al servicio encargado de retornar una lista de estudiantes por carrera y ciudad.
     * @param string idCarrera y string ciudad.
     * @return lista de Estudiante.
     */
	@GetMapping("/obtenerDeCarreraPorCiudad/carrera/{idCarrera}/ciudad/{ciudad}")
    @Operation(
            summary = "Devuelve una lista de estudiantes por carrera y ciudad.",
            description = "Servicio encargado de retornar una lista de estudiantes por carrera y ciudad ingresadas por parámetro.",
            tags = { "Estudiante-controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estudiante.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> obtenerEstudiantesDeCarreraPorCiudad(@PathVariable int idCarrera, @PathVariable String ciudad){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.obtenerEstudiantesDeCarreraPorCiudad(idCarrera, ciudad));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentran estudiantes de la carrera y/o ciudad ingresadas.\"}");
        }
    }

}

	
