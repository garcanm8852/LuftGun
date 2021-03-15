package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MPedido {
	Connection Conexion;
	PreparedStatement ps;
	ResultSet cargaPedido;

	final String URL = "jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-mgarde";
	final String USER = "a20-mgarde";
	final String PASSW = "a20-mgarde";
	int idpedido;

	public MPedido() {
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

	public void AnadirPedido(int pIdpedido, int pIdcliente, int pTelefono, String pCalle, String pProvincia,
			String pLocalidad, int pCp, String pPais) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("INSERT INTO luftgun.pedido(idpedido, idcliente, fecha, tel, calle, provincia, localidad, cp, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, pIdpedido);
			ps.setInt(2, pIdcliente);
			ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			ps.setInt(4, pTelefono);
			ps.setString(5, pCalle);
			ps.setString(6, pProvincia);
			ps.setString(7, pLocalidad);
			ps.setInt(8, pCp);
			ps.setString(9, pPais);
			ps.execute();
			cerrarConexion();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void getUltimoPedido() {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT MAX(idpedido) FROM luftgun.pedido");
			cargaPedido = ps.executeQuery();
			cargaPedido.next();
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public int getIdpedido() {
		idpedido = 0;
		try {
			idpedido = cargaPedido.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return idpedido;
	}

}
