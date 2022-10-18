package clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.FacturaProducto;
import factory.DBderby;
import factory.DBmysql;
import interfaces.DAO;

public class FacturaProductoDAO implements DAO<FacturaProducto> {

	public static final String MYSQL_DB = "mysql";
	public static final String DERBY_DB = "derby";
	private Connection conn; 
	
	public FacturaProductoDAO(String db) throws SQLException {
		crearTabla(db);
	}

	@Override
	public void crearTabla(String db) throws SQLException {
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			String tableMySQL = "CREATE TABLE IF NOT EXISTS facturaproducto (idFactura INT, idProducto INT, cantidad INT, PRIMARY KEY(idFactura, idProducto))";
			conn.prepareStatement(tableMySQL).execute();
			conn.commit();
			conn.close();
			break;
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			String drop = "DROP TABLE facturaproducto";
			conn.prepareStatement(drop).execute();
			conn.commit();
			String tableDerby = "CREATE TABLE facturaproducto (idFactura INT, idProducto INT, cantidad INT, PRIMARY KEY(idFactura, idProducto))";
			conn.prepareStatement(tableDerby).execute();
			conn.commit();
			conn.close();
			break;
		}		
	}

	@Override
	public void insertarDatos(FacturaProducto datos, String db) throws SQLException {
		int idFactura = datos.getIdFactura();
		int idProducto = datos.getIdProducto();
		int cantidad = datos.getCantidad();
		
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			break; 
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			break;
		}		
		String insert = "INSERT INTO facturaproducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idProducto);
		ps.setInt(3, cantidad);
		
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();	
	}
	
}
