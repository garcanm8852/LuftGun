package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class MConectar {
	Connection Conexion;

	String URL = "jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-mgarde";
	String USER = "a20-mgarde";
	String PASSW = "a20-mgarde";

	public Connection establecerConexion() {
		try {
			Conexion = DriverManager.getConnection(URL, USER, PASSW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Conexion;

	}
}
