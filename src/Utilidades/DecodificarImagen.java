package Utilidades;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelos.MProducto;

/**
 * Servlet implementation class DecodificarImagen
 */
@WebServlet("/DecodificarImagen")
public class DecodificarImagen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String phototype = "jpg";
	MProducto mProducto = new MProducto();
	byte[] buf;
	String idreferencia;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			idreferencia = "";
			buf = null;
			idreferencia = request.getParameter("idreferencia"); 
			buf = mProducto.cargarImagenProducto(idreferencia);
			// Response.setContentType (content_type);
			// Tells the browser to output pictures
			response.setContentType("image /" + phototype);
			// Output image output stream
			OutputStream out = response.getOutputStream();
			// Output to the input of the buffer page
			out.write(buf);
			// Input is completed, clear buffer
			out.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
