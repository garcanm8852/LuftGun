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
 * Servlet implementation class VerificarCuenta
 */
@WebServlet("/VerificarCuenta")
public class VerificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnvioCorreo OutMail = new EnvioCorreo();
	HttpSession sesion;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sesion = request.getSession();
		  try {
			    OutMail.enviar("luftgunmail@gmail.com",
			            "Luftgun20$",
			           (String) sesion.getAttribute("vEmail") ,
			           (String) sesion.getAttribute("vNombre") + " Código de validación LuftGun",
			          "Su código de validación de registro de LuftGun es: " + (int) sesion.getAttribute("Validacion"));

			} catch (Exception e) {
			    e.printStackTrace();
			}
		request.getRequestDispatcher("WEB-INF/VerificarCuenta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sesion = request.getSession();
		MCliente mCliente = new MCliente();
		if (request.getParameter("fValidacion").equals( sesion.getAttribute("Validacion").toString())) {
		    mCliente.insertarCliente((mCliente.nextID() + 1),
		    	request.getParameter("fNombre"),
				request.getParameter("fApellido"),
				request.getParameter("fEmail"),
				request.getParameter("fContrasena"));
		    request.getRequestDispatcher("Catalogo").forward(request, response);

		}else {
			doGet(request, response);
		}

			}

}
