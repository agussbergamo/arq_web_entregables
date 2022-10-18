package clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Factura;
import factory.DBderby;
import factory.DBmysql;
import interfaces.DAO;

public class FacturaDAO implements DAO<Factura> {

	public static final String MYSQL_DB = "mysql";
	public static final String DERBY_DB = "derby";
	private Connection conn; 
	
	public FacturaDAO(String db) throws SQLException {
		crearTabla(db);
	}

	@Override
	public void crearTabla(String db) throws SQLException {
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			String tableMySQL = "CREATE TABLE IF NOT EXISTS factura (idFactura INT, idCliente INT, PRIMARY KEY(idFactura))";
			conn.prepareStatement(tableMySQL).execute();
			conn.commit();
			conn.close();
			break;
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			String drop = "DROP TABLE factura";
			conn.prepareStatement(drop).execute();
			conn.commit();
			String tableDerby = "CREATE TABLE factura (idFactura INT, idCliente INT, PRIMARY KEY(idFactura))";
			conn.prepareStatement(tableDerby).execute();
			conn.commit();
			conn.close();
			break;
		}		
	}

	@Override
	public void insertarDatos(Factura datos, String db) throws SQLException {
		int idFactura = datos.getIdFactura();
		int idCliente = datos.getIdCliente();
				
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			break; 
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			break;
		}		
		String insert = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idCliente);
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();	
	}
	
}
