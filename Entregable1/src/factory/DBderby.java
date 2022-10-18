package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import clasesDAO.ClienteDAO;
import clasesDAO.FacturaDAO;
import clasesDAO.FacturaProductoDAO;
import clasesDAO.ProductoDAO;

public class DBderby extends CreadorDAOs {

	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";	
	public static final String URI = "jdbc:derby:MyDerbyDb;create=true";
	
	public DBderby() {
		registrarDriver();
	}
	
	private static void registrarDriver() {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}	
	}

	public static Connection obtenerConexion() {
		try {
			Connection conn = DriverManager.getConnection(URI);
			conn.setAutoCommit(false);
			return conn; 			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	} 


	@Override
	public ClienteDAO getClienteDAO(String db) throws SQLException {
		return new ClienteDAO(db);
	}

	@Override
	public FacturaDAO getFacturaDAO(String db) throws SQLException {
		return new FacturaDAO(db);
	}

	@Override
	public ProductoDAO getProductoDAO(String db) throws SQLException {
		return new ProductoDAO(db);
	}

	@Override
	public FacturaProductoDAO getFacturaProductoDAO(String db) throws SQLException {
		return new FacturaProductoDAO(db);
	}

}
