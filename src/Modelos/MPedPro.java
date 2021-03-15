package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MPedPro {
	Connection Conexion;
	PreparedStatement ps;
	final String URL = "jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-mgarde";
	final String USER = "a20-mgarde";
	final String PASSW = "a20-mgarde";

	
	public MPedPro() {
		
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

	public void AnadirRelacion(int pIdPedido, String pIdreferencia) {
		try {
			establecerConexion();
			
			ps = Conexion.prepareStatement("INSERT INTO luftgun.pedpro(idpedido, idreferencia)VALUES (?, ?)");
			ps.setInt(1, pIdPedido);
			ps.setString(2, pIdreferencia);
			ps.execute();
			
			cerrarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
}
