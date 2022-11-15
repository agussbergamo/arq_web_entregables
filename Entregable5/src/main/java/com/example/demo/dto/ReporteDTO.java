package com.example.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ReporteDTO {

	private int anio;
	private String nombreCarrera; 
	private Long inscriptos;
	private Long egresados;
	
	public ReporteDTO(int anio, String nombreCarrera, Long inscriptos, Long egresados) {
		this.anio = anio;
		this.nombreCarrera = nombreCarrera;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
	}
	
	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public Long getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(Long inscriptos) {
		this.inscriptos = inscriptos;
	}

	public Long getEgresados() {
		return egresados;
	}

	public void setEgresados(Long egresados) {
		this.egresados = egresados;
	}

	@Override
	public String toString() {
		return "ReporteDTO [anio=" + anio + ", nombreCarrera=" + nombreCarrera + ", inscriptos=" + inscriptos
				+ ", egresados=" + egresados + "]\n";
	}
	
}