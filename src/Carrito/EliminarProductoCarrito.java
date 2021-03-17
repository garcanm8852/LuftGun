package Carrito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.MCarrito;

/**
 * Servlet implementation class EliminarProductoCarrito
 */
@WebServlet("/EliminarProductoCarrito")
public class EliminarProductoCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MCarrito mCarrito = new MCarrito();
		Cookie[] Cookies;
		sesion = request.getSession();
		if ((boolean) sesion.getAttribute("Iniciado") != false) {
			if (request.getParameter("idcarrito") != null) {
				try {
					mCarrito.eliminarProducto(Integer.parseInt(request.getParameter("idcarrito")));
				} catch (Exception e) {
					// TODO: handle exception
				}
				response.sendRedirect("Carrito");
			} else {
				response.sendRedirect("Carrito");
			}

		} else {
			Cookies = request.getCookies();
			for (int i = 0; i < Cookies.length; i++) {
				if (request.getParameter("idcookie").equals(Cookies[i].getName())) {
					Cookies[i].setMaxAge(0);
					response.addCookie(Cookies[i]);
				}
			}
			response.sendRedirect("Carrito");

			/* Borrar producto con cookies */
		}

	}

}
