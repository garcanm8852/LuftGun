package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MProducto {
	Connection Conexion;
	ResultSet cargaProductos;
	ResultSet srcProducto;
	ResultSet cargaRegistros;
	String idreferencia;
	String nombre;
	String marca;
	String descripcion;
	Double precio;
	byte[] img;
	int stock;
	int subcategoria;
	byte[] src;
	String nombreCategoria;
	int categoria;
	String nombreSubcategoria;
	int numeroRegistros;
	int pagina;
	boolean estadoExiste;
	PreparedStatement ps;
	String Sentencia;
	boolean estado = false;
	final String URL = "jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-mgarde";
	final String USER = "a20-mgarde";
	final String PASSW = "a20-mgarde";

	public void establecerConexion() {
		try {
			Conexion = DriverManager.getConnection(URL, USER, PASSW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			Conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void cargarProductos() {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.producto");
			cargaProductos = ps.executeQuery();
			cargaProductos.next();
			cerrarConexion();
		} catch (SQLException e) {
		}
	}
	
	public void cargarProductosPaginados(int pPagina) {
		pagina = 5 * pPagina;
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.producto offset ? limit 5");
			ps.setInt(1, pagina);
			cargaProductos = ps.executeQuery();
			cargaProductos.next();
			cerrarConexion();
		} catch (SQLException e) {
		}
	}
	
	public int numeroRegistros() {
		numeroRegistros = 0;
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT COUNT(idreferencia) from luftgun.producto");
			cargaRegistros = ps.executeQuery();
			cargaRegistros.next();			
			numeroRegistros = cargaRegistros.getInt(1);
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return numeroRegistros;
	}
	
	public void cargarProductosPorCategorias(int pidCategoria) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.DatosProductosCompletoBusqueda(?);");
			ps.setInt(1, pidCategoria);
			cargaProductos = ps.executeQuery();
			cargaProductos.next();
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void cargarProductosPorSubcategorias(int idSubcategoria) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.DatosProductoSubcategoria(?);");
			ps.setInt(1, idSubcategoria);
			cargaProductos = ps.executeQuery();
			cargaProductos.next();
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public byte[] cargarImagenProducto(String pidreferencia) {
		src = null;
		srcProducto = null;
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT img FROM luftgun.producto WHERE idreferencia = ?");
			ps.setString(1, pidreferencia);
			srcProducto = ps.executeQuery();
			srcProducto.next();
			src = srcProducto.getBytes(1);
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return src;
	}
	
	public void consultarProducto(String pidreferencia) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.DatosProductoBusqueda(?)");
			ps.setString(1, pidreferencia);
			cargaProductos = ps.executeQuery();
			cargaProductos.next();
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public boolean consultarSiguiente() {
		estado = false;
		try {
			 estado = cargaProductos.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;

	}
	
	public boolean consultarAnterior() {
		estado = false;
		try {
			 estado = cargaProductos.previous();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;

	}
	
	public void cursorPrincipio() {
		try {
			cargaProductos.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cursorFinal() {
		try {
			cargaProductos.last();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getIdreferencia(){
		idreferencia = "";
		try {
			idreferencia = cargaProductos.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idreferencia;
	}
	
	public String getNombre(){
		nombre = null;
		try {
			nombre = cargaProductos.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
	}
	
	public String getMarca() {
		marca= null;
		try {
			marca = cargaProductos.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return marca;
	}
	
	public String getDescripcion() {
		descripcion= null;
		try {
			descripcion = cargaProductos.getString(4);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return descripcion;
	}
	
	public Double getPrecio() {
		precio = null;
		try {
			precio = cargaProductos.getDouble(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return precio;
	}
	
	public byte[] getImg() {
		img = null;
		try {
			img = cargaProductos.getBytes(6);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return img;
	}
	
	public int getStock() {
		stock = 0;
		try {
			stock = cargaProductos.getInt(7);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stock;
	}
	
	public int getSubcategoria() {
		subcategoria = 0;
		try {
			subcategoria = cargaProductos.getInt(8);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return subcategoria;
	}
	
	public String getNombreSubcategoria(){
		nombreSubcategoria = "";
		try {
			nombreSubcategoria = cargaProductos.getString(9);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return nombreSubcategoria;
	}
	
	public int getCategoria() {
		categoria = 0;
		try {
			categoria = cargaProductos.getInt(10);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return categoria;
	}
	
	public String getNombreCategoria() {
		nombreCategoria = "";
		try {
			nombreCategoria = cargaProductos.getString(11);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nombreCategoria;
	}




	
	
}
