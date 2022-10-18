package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Carrera;
import clases.Estudiante;
import clasesDAO.CarreraDAO;
import clasesDAO.EstudianteCarreraDAO;
import clasesDAO.EstudianteDAO;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		final String CSV_CARRERAS = "./src/csv/carreras.csv";
		final String CSV_ESTUDIANTES = "./src/csv/estudiantes.csv";
		final String CSV_ESTUDIANTE_CARRERA = "./src/csv/estudianteCarrera.csv";

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		
		EstudianteDAO eDAO = new EstudianteDAO();
		CarreraDAO cDAO = new CarreraDAO();
		EstudianteCarreraDAO ecDAO = new EstudianteCarreraDAO();
		
		
		CSVParser parserEstudiante = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_ESTUDIANTES));
		for(CSVRecord row: parserEstudiante) {
			int dni =  Integer.parseInt(row.get("DNI"));
			String nombre = row.get("nombre");
			String apellido = row.get("apellido");			
			int edad =  Integer.parseInt(row.get("edad"));
			String genero = row.get("genero");
			String ciudad = row.get("ciudad");
			int num_libreta =  Integer.parseInt(row.get("LU"));
			Estudiante e = new Estudiante(dni, nombre, apellido, edad, genero, ciudad, num_libreta);
			eDAO.altaEstudiante(e);
		}	

		CSVParser parserCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CARRERAS));
		for(CSVRecord row: parserCarrera) {
			int idCarrera =  Integer.parseInt(row.get("id_carrera"));
			String nombre = row.get("carrera");
			int duracion =  Integer.parseInt(row.get("duracion"));
			Carrera c = new Carrera(idCarrera, nombre, duracion);
			cDAO.altaCarrera(c);
		}
		
		CSVParser parserEstudianteCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_ESTUDIANTE_CARRERA));
		for(CSVRecord row: parserEstudianteCarrera) {
			int id_estudiante =  Integer.parseInt(row.get("id_estudiante"));
			Estudiante estudiante = em.find(Estudiante.class, id_estudiante);
			if(estudiante!=null) {
				int id_carrera =  Integer.parseInt(row.get("id_carrera"));
				Carrera carrera = em.find(Carrera.class, id_carrera);
				int inscripcion =  Integer.parseInt(row.get("inscripcion"));
				int graduacion =  Integer.parseInt(row.get("graduacion"));
				int antiguedad =  Integer.parseInt(row.get("antiguedad"));
				ecDAO.matricularEstudiante(estudiante, carrera, inscripcion, graduacion, antiguedad);
			}
		}
		
		//Servicios punto 2
		
		//Servicio a) y b) en la carga de los datos
		
		//Servicio c)
		//System.out.println(eDAO.obtenerEstudiantesOrdenados());
	
		//Servicio d)
		//System.out.println(eDAO.obtenerEstudiantePorLU(34978));
		
		//Servicio e) 
		//System.out.println(eDAO.obtenerEstudiantesPorGenero("Male"));
		
		//Servicio f)
		//System.out.println(cDAO.carrerasConInscriptos());
		
		//Servicio g)
		//Carrera c = new Carrera (4, "Ingenieria Electronica", 5);
		//System.out.println(eDAO.obtenerEstudiantesDeCarreraPorCiudad(c, "Ganhe"));
		
		//Servicio punto 3
		//System.out.println(ecDAO.generarReporte());
		
		
	}
}

	
		



