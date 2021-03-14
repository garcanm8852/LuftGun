package AccionesUsuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.MCliente;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
	boolean estadoRegistro;
	boolean existeCorreo;
	MCliente mCliente = new MCliente();

	protected boolean comprobarCorreo(String pEmail) {
		existeCorreo = false;
		mCliente.ExisteCliente(pEmail);
		try {
			if (pEmail.equals(mCliente.getEmail())) {
				existeCorreo = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return existeCorreo;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sesion = request.getSession();

		if (sesion.getAttribute("Error") == null) {
			sesion.setAttribute("Error", false);

		}
		if (sesion.getAttribute("Contrasena") == null) {
			sesion.setAttribute("Contrasena", false);
		}

		request.getRequestDispatcher("WEB-INF/registro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sesion.setAttribute("Validacion", (int) (Math.random() * 999999 + 1));
		estadoRegistro = true;
		if (!request.getParameter("fContrasena").equals(request.getParameter("fContasenaRep"))) {
			sesion.setAttribute("Contrasena", true);
			estadoRegistro = false;
		}
		if (comprobarCorreo(request.getParameter("fEmail"))) {
			sesion.setAttribute("Correo", true);
			estadoRegistro = false;
		}

		if (estadoRegistro == true) {

		} else {
			request.getRequestDispatcher("WEB-INF/registro.jsp").forward(request, response);

		}
	}

}
