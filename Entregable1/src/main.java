import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Cliente;
import clases.Factura;
import clases.FacturaProducto;
import clases.Producto;
import clasesDAO.ClienteDAO;
import clasesDAO.FacturaDAO;
import clasesDAO.FacturaProductoDAO;
import clasesDAO.ProductoDAO;
import factory.CreadorDAOs;

public class main {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		
		String db1 = "mysql";
		//String db1 = "derby";
		
		CreadorDAOs dbElegida1 = CreadorDAOs.getDAOFactory(db1);
		
		ProductoDAO pDAO1 = dbElegida1.getProductoDAO(db1);
		pDAO1.crearTabla(db1);
		
		ClienteDAO cDAO1 = dbElegida1.getClienteDAO(db1);
		cDAO1.crearTabla(db1);

		FacturaDAO fDAO1 = dbElegida1.getFacturaDAO(db1);
		fDAO1.crearTabla(db1);
		
		FacturaProductoDAO fpDAO1 = dbElegida1.getFacturaProductoDAO(db1);
		fpDAO1.crearTabla(db1);
		
		CSVParser parserProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\Users\\Agus\\eclipse-workspace\\arq_web\\Entregable1\\CSV\\productos.csv"));
				for(CSVRecord row: parserProductos) {
					int idProducto =  Integer.parseInt(row.get("idProducto"));
					String nombre = row.get("nombre");
					float valor = Float.parseFloat(row.get("valor"));
					Producto p = new Producto (idProducto, nombre, valor); 
					pDAO1.insertarDatos(p, db1);
				}
		
		CSVParser parserClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\Users\\Agus\\eclipse-workspace\\arq_web\\Entregable1\\CSV\\clientes.csv"));
		for(CSVRecord row: parserClientes) {
			int idCliente =  Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
			Cliente c = new Cliente (idCliente, nombre, email); 
			cDAO1.insertarDatos(c, db1);
		}
		
		CSVParser parserFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\Users\\Agus\\eclipse-workspace\\arq_web\\Entregable1\\CSV\\facturas.csv"));
		for(CSVRecord row: parserFacturas) {
			int idFactura =  Integer.parseInt(row.get("idFactura"));
			int idCliente =  Integer.parseInt(row.get("idCliente"));
			Factura f = new Factura (idFactura, idCliente); 
			fDAO1.insertarDatos(f, db1);
		}
		
		CSVParser parserFacturaProducto = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\Users\\Agus\\eclipse-workspace\\arq_web\\Entregable1\\CSV\\facturas-productos.csv"));
		for(CSVRecord row: parserFacturaProducto) {
			int idFactura =  Integer.parseInt(row.get("idFactura"));
			int idProducto =  Integer.parseInt(row.get("idProducto"));
			int cantidad =  Integer.parseInt(row.get("cantidad"));
			FacturaProducto fp = new FacturaProducto (idFactura, idProducto, cantidad); 
			fpDAO1.insertarDatos(fp, db1);
		}
		
		System.out.println(pDAO1.getProductoVIP(db1));
		System.out.println(cDAO1.listarClientesPorFacturacion(db1));
		
	}

}
