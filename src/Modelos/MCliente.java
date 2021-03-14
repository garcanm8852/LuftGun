package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MCliente {
	Connection Conexion;
	PreparedStatement ps;
	ResultSet cargaCliente;
	int idCliente;
	String Nombre;
	String Apellido;
	String Email;
	String Contrasena;
	int Tel;
	String Calle;
	String Provincia;
	String Localidad;
	int Cp;
	String Pais;
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

	public void DatosInicioSesion(String pEmail, String pContrasena) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.DatosClienteSesion(?,?);");
			ps.setString(1, pEmail);
			ps.setString(2, pContrasena);
			cargaCliente = ps.executeQuery();
			cargaCliente.next();
			cerrarConexion();
		} catch (Exception e) {
			System.out.println("falla aqui");
		}
	}

	public boolean ExisteCliente(String pEmail) {
		try {
			establecerConexion();
			ps = Conexion.prepareStatement("SELECT * FROM luftgun.cliente where email = ?;");
			ps.setString(1, pEmail);
			cargaCliente = ps.executeQuery();
			cargaCliente.next();
			cerrarConexion();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public int getIdcliente() {
		idCliente = 0;
		try {
			idCliente = cargaCliente.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return idCliente;
	}

	public String getNombre() {
		Nombre = "";
		try {
			Nombre = cargaCliente.getString(2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Nombre;
	}

	public String getApellido() {
		Apellido = "";
		try {
			Apellido = cargaCliente.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Apellido;
	}

	public String getEmail() {
		Email = "";
		try {
			Email = cargaCliente.getString(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return Email;
	}

	public String getContrasena() {
		Contrasena = "";
		try {
			Contrasena = cargaCliente.getString(5);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Contrasena;
	}

	public int getTel() {
		Tel = 0;
		try {
			Tel = cargaCliente.getInt(6);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Tel;
	}

	public String getCalle() {
		Calle = "";
		try {
			Calle = cargaCliente.getString(7);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Calle;

	}

	public String getProvincia() {
		Provincia = "";
		try {
			Provincia = cargaCliente.getString(8);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Provincia;

	}

	public String getLocalidad() {
		Localidad = "";
		try {
			Localidad = cargaCliente.getString(9);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Localidad;

	}

	public int getCp() {
		Cp = 0;
		try {
			Cp = cargaCliente.getInt(10);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Cp;
	}

	public String getPais() {
		Pais = "";
		try {
			Pais = cargaCliente.getString(11);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Pais;

	}

}
