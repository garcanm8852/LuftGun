package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MCarrito {
	Connection Conexion;
	PreparedStatement ps;
	ResultSet cargaCarrito;
	boolean estado;
	int idcliente;
	String idreferencia;
	
	final String URL = "jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-mgarde";
	final String USER = "a20-mgarde";
	final String PASSW = "a20-mgarde";

	public MCarrito() {
	}

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

	public boolean consultarSiguiente() {
		estado = false;
		try {
			estado = cargaCarrito.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;

	}

	public boolean consultarAnterior() {
		estado = false;
		try {
			estado = cargaCarrito.previous();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;

	}

	public void AnadirProductoCarrito(String pReferencia, int pIdcliente) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("INSERT INTO luftgun.carrito (idreferencia, idcliente) VALUES (?,?)");
			ps.setString(1, pReferencia);
			ps.setInt(2, pIdcliente);
			ps.execute();
			cerrarConexion();
		} catch (Exception e) {

		}
	}

	public void cargarCarrito(int pIdcliente) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.carrito where idcliente = ?");
			ps.setInt(1, pIdcliente);
			cargaCarrito = ps.executeQuery();
			cargaCarrito.next();
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public int getIdcliente() {
		idcliente = 0;
		try {
			idcliente = cargaCarrito.getInt(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return idcliente;
	}
	
	public String getIdreferencia() {
		idreferencia = "";
		try {
			idreferencia = cargaCarrito.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return idreferencia;
	}

}
