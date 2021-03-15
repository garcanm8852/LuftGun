package AccionesUsuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.MCategoria;
import Modelos.MCliente;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MCategoria mCategoria = new MCategoria();
	MCliente mCliente = new MCliente();
	HttpSession sesion;
	int numeroCategorias;
	boolean estadoSesion;
	String[] categorias;
	int ContadorCategorias;
	


	
	protected boolean inciarSesion(String pEmail, String pContrasena) {
		estadoSesion = false;
		mCliente.DatosInicioSesion(pEmail, pContrasena);
		try {
			if (mCliente.getEmail().equals( pEmail) && mCliente.getContrasena().contentEquals(pContrasena)) {
				estadoSesion = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estadoSesion;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		sesion = request.getSession(true);
		sesion.setAttribute("Error", false);
		if (sesion.getAttribute("Iniciado") == null) {
			sesion.setAttribute("Iniciado", false);
		}
		request.getRequestDispatcher("WEB-INF/iniciarsesion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		sesion = request.getSession(true);

		
		if (inciarSesion(request.getParameter("fEmail"),request.getParameter("fContrasena"))) {
			sesion.setAttribute("Iniciado", true);
			sesion.setAttribute("NombreUsuario", mCliente.getNombre());
			sesion.setAttribute("idcliente", mCliente.getIdcliente());
			request.getRequestDispatcher("Catalogo").forward(request, response);
		}else {
			sesion.setAttribute("Iniciado", false);
			sesion.setAttribute("Error", true);
			request.getRequestDispatcher("WEB-INF/iniciarsesion.jsp").forward(request, response);
		}
		
	}



}
