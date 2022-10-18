package clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Producto;
import factory.DBderby;
import factory.DBmysql;
import interfaces.DAO;

public class ProductoDAO implements DAO<Producto> {

	public static final String MYSQL_DB = "mysql";
	public static final String DERBY_DB = "derby";
	private Connection conn; 
	
	public ProductoDAO(String db) throws SQLException {
		crearTabla(db);
	}

	@Override
	public void crearTabla(String db) throws SQLException {
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			String tableMySQL = "CREATE TABLE IF NOT EXISTS producto (idProducto INT, nombre VARCHAR (45), valor FLOAT, PRIMARY KEY(idProducto))";
			conn.prepareStatement(tableMySQL).execute();
			conn.commit();
			conn.close();
			break;
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			String drop = "DROP TABLE producto";
			conn.prepareStatement(drop).execute();
			conn.commit();
			String tableDerby = "CREATE TABLE producto (idProducto INT, nombre VARCHAR (45), valor FLOAT, PRIMARY KEY(idProducto))";
			conn.prepareStatement(tableDerby).execute();
			conn.commit();
			conn.close();
			break;
		}		
	}

	@Override
	public void insertarDatos(Producto datos, String db) throws SQLException {
		int id = datos.getIdProducto();
		String nombre = datos.getNombre();
		float valor = datos.getValor();
		
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			break; 
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			break;
		}		
		String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, nombre);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();	
	}
	
	public Producto getProductoVIP(String db) throws SQLException  {
		Producto p = new Producto();
		
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			break; 
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			break;
		}
		
		String select = "SELECT p.*, SUM(fp.cantidad*p.valor) AS recaudacion "
				+ "FROM producto p JOIN facturaproducto fp ON p.idProducto = fp.idProducto "
				+ "GROUP BY p.idProducto "
				+ "ORDER BY recaudacion DESC "
				+ "LIMIT 1 ";

		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			p = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
		}
		return p;		
	}
	
}
