package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MCategoria {
	
	public MCategoria() {
	}

	Connection Conexion;
	ResultSet cargaCategorias;
	int id;
	String nombreCategoria;
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
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.categoria");
			cargaCategorias = ps.executeQuery();
			cargaCategorias.next();
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean consultarSiguiente() {
		estado = false;
		try {
			 establecerConexion();
			 estado = cargaCategorias.next();
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
			 estado = cargaCategorias.previous();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
		return estado;

	}
	
	public void cursorPrincipio() {
		try {
			establecerConexion();
			cargaCategorias.first();
			cerrarConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cursorFinal() {
		try {
			establecerConexion();
			cargaCategorias.last();
			cerrarConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getId(){
		id = 0;
		try {
			id = cargaCategorias.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public String getnombreCategoria(){
		nombreCategoria = null;
		try {
			nombreCategoria = cargaCategorias.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombreCategoria;
	}
	
}
