package clasesDTO;

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

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public Long getInscriptos() {
		return inscriptos;
	}

	public Long getEgresados() {
		return egresados;
	}

	@Override
	public String toString() {
		return "ReporteDTO [anio=" + anio + ", nombreCarrera=" + nombreCarrera + ", inscriptos=" + inscriptos
				+ ", egresados=" + egresados + "]\n";
	}



	
}
