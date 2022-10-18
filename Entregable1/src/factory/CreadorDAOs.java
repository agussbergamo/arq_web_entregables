package factory;

import java.sql.SQLException;

import clasesDAO.ClienteDAO;
import clasesDAO.FacturaDAO;
import clasesDAO.FacturaProductoDAO;
import clasesDAO.ProductoDAO;

public abstract class CreadorDAOs {

	public static final String MYSQL_DB = "mysql";
	public static final String DERBY_DB = "derby";
	private static CreadorDAOs mysql = null; 
	private static CreadorDAOs derby = null; 

	public abstract ClienteDAO getClienteDAO (String db) throws SQLException; 
	public abstract FacturaDAO getFacturaDAO (String db) throws SQLException; 
	public abstract ProductoDAO getProductoDAO (String db) throws SQLException; 
	public abstract FacturaProductoDAO getFacturaProductoDAO (String db) throws SQLException; 
	
	//DAOFactory
	public static CreadorDAOs getDAOFactory(String db) {
		switch (db) {
		case MYSQL_DB :
			return instanciarMySQL();
		case DERBY_DB :
			return instanciarDerby();
		default:
			return null; 
		}	
	}
	
	//Patrón singleton para mysql	
	public static CreadorDAOs instanciarMySQL() {
		if(mysql == null) {
			mysql = new DBmysql();
			return mysql; 
		}
		return mysql;
	}
	
	//Patrón singleton para derby
	public static CreadorDAOs instanciarDerby() {
		if(derby == null) {
			derby = new DBderby();
			return derby; 
		}
		return derby;		
	}	
	
}
