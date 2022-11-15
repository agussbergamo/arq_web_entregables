
package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Carrera {

	@Id
	private int id;
	@Column
	private String nombre;
	@Column
	private int duracion;
	@OneToMany(mappedBy="idCarrera", cascade = CascadeType.MERGE, orphanRemoval=true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<EstudianteCarrera> estudiantes;
	private int inscriptos; 

	public Carrera() {
		super();
	}

	public Carrera(int id, String nombre, int duracion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion; 
		this.estudiantes = new ArrayList<EstudianteCarrera>();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<EstudianteCarrera> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<EstudianteCarrera> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public int getInscriptos() {
		return estudiantes.size();
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + ", estudiantes=" + estudiantes + "]\n";
	}
}

