package clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Cliente;
import factory.DBderby;
import factory.DBmysql;
import interfaces.DAO;

public class ClienteDAO implements DAO<Cliente> {

	public static final String MYSQL_DB = "mysql";
	public static final String DERBY_DB = "derby";
	private Connection conn; 
	
	public ClienteDAO(String db) throws SQLException {
		crearTabla(db);
	}

	@Override
	public void crearTabla(String db) throws SQLException {
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			String tableMySQL = "CREATE TABLE IF NOT EXISTS cliente (idCliente INT, nombre VARCHAR (500), email VARCHAR (150), PRIMARY KEY(idCliente))";
			conn.prepareStatement(tableMySQL).execute();
			conn.commit();
			conn.close();
			break;
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			String drop = "DROP TABLE cliente";
			conn.prepareStatement(drop).execute();
			conn.commit();
			String tableDerby = "CREATE TABLE cliente (idCliente INT, nombre VARCHAR (500), email VARCHAR (150), PRIMARY KEY(idCliente))";
			conn.prepareStatement(tableDerby).execute();
			conn.commit();
			conn.close();
			break;
		}		
	}

	@Override
	public void insertarDatos(Cliente datos, String db) throws SQLException {
		int id = datos.getId();
		String nombre = datos.getNombre();
		String email = datos.getEmail();
		
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			break; 
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			break;
		}		
		String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, nombre);
		ps.setString(3, email);
		ps.executeUpdate();
		ps.close();
		conn.commit();
		conn.close();	
	}
	
	public ArrayList<Cliente> listarClientesPorFacturacion(String db) throws SQLException  {
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		switch(db) {
		case MYSQL_DB:
			this.conn = DBmysql.obtenerConexion();
			break; 
		case DERBY_DB:
			this.conn = DBderby.obtenerConexion();
			break;
		}
		
		String select = "SELECT c.*, SUM(fp.cantidad*p.valor) AS recaudacion "
				+ "FROM cliente c JOIN factura f "
				+ "ON c.idCliente = f.idCliente JOIN facturaproducto fp "
				+ "ON f.idFactura = fp.idFactura JOIN producto p "
				+ "ON fp.idProducto = p.idProducto "
				+ "GROUP BY c.idCliente "
				+ "ORDER BY recaudacion DESC ";

		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cliente c = new Cliente (rs.getInt(1), rs.getString(2), rs.getString(3));
			clientes.add(c);
		}
		return clientes;	
	}

}
