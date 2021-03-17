package AccionesUsuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.MCliente;
import Utilidades.EnvioCorreo;

/**
 * Servlet implementation class PrimerInicioSesion
 */
@WebServlet("/PrimerInicioSesion")
public class PrimerInicioSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnvioCorreo OutMail = new EnvioCorreo();
	HttpSession sesion;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion = request.getSession();
		sesion.setAttribute("CodigoInvalido", false);
		try {
			OutMail.enviar("luftgunmail@gmail.com", "Luftgun20$", (String) sesion.getAttribute("email"),
					(String) sesion.getAttribute("NombreUsuario ") + " Código de validación LuftGun",
					"Su código de validación de registro de LuftGun es: " + (int) sesion.getAttribute("PrimerInicio"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/primerinicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion = request.getSession();
		MCliente mCliente = new MCliente();

		if (Integer.parseInt(request.getParameter("fValidacion")) == (int) sesion.getAttribute("PrimerInicio")) {
			mCliente.CambiarEstado((int) sesion.getAttribute("idcliente"), "Operativo");
			sesion.setAttribute("email", null);
			sesion.setAttribute("contTemp", null);
			sesion.setAttribute("Iniciado", true);
			response.sendRedirect("Catalogo");
		} else {
			sesion.setAttribute("CodigoInvalido", true);

		}
	}

}
