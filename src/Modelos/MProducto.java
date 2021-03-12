package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MProducto {
	Connection Conexion;
	ResultSet cargaProducto;
	
	int idreferencia;
	String nombre;
	String marca;
	String descripcion;
	Double precio;
	byte[] img;
	int stock;
	int subcategoria;
	
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
	

	public void cargarCategorias() {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.producto");
			cargaProducto = ps.executeQuery();
			cargaProducto.next();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean consultarSiguiente() {
		estado = false;
		try {
			 establecerConexion();
			 estado = cargaProducto.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
		return estado;

	}
	
	public boolean consultarAnterior() {
		estado = false;
		try {
			 establecerConexion();
			 estado = cargaProducto.previous();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
		return estado;

	}
	
	public void cursorPrincipio() {
		try {
			establecerConexion();
			cargaProducto.first();
			cerrarConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cursorFinal() {
		try {
			establecerConexion();
			cargaProducto.last();
			cerrarConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getIdreferencia(){
		idreferencia = 0;
		try {
			idreferencia = cargaProducto.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idreferencia;
	}
	
	public String getNombre(){
		nombre = null;
		try {
			nombre = cargaProducto.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombre;
	}
	
	public String getMarca() {
		marca= null;
		try {
			marca = cargaProducto.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return marca;
	}
	
	public String getDescripcion() {
		descripcion= null;
		try {
			descripcion = cargaProducto.getString(4);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return descripcion;
	}
	
	public Double getPrecio() {
		precio = null;
		try {
			precio = cargaProducto.getDouble(5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return precio;
	}
	
	public byte[] getImg() {
		img = null;
		try {
			img = cargaProducto.getBytes(6);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return img;
	}
	
	public int getStock() {
		stock = 0;
		try {
			stock = cargaProducto.getInt(7);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stock;
	}
	
	public int getSubcategoria() {
		subcategoria = 0;
		try {
			subcategoria = cargaProducto.getInt(8);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return subcategoria;
	}
	
}
