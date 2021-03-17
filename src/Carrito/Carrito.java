package Carrito;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Catalogo.Cproducto;
import Modelos.MCarrito;
import Modelos.MProducto;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/Carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest reqst, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MCarrito mCarrito = new MCarrito();
		Cproducto[] listaProductos;
		MProducto mProducto = new MProducto();
		int[] listaidCarrito = new int[25];
		int contadorCarrito;
		int contadorCookies;
		Cookie CookieProducto;
		Cookie[] cookies;

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		sesion = request.getSession(true);

		if (sesion.getAttribute("Iniciado") == null) {
			sesion.setAttribute("Iniciado", false);
		}

		if ((boolean) sesion.getAttribute("Iniciado") == true) {

			if (request.getParameter("idproducto") != null) {
				mCarrito.AnadirProductoCarrito(request.getParameter("idproducto"),
						(int) sesion.getAttribute("idcliente"));
			}

			mCarrito.cargarCarrito((int) sesion.getAttribute("idcliente"));
			listaProductos = new Cproducto[25];
			contadorCarrito = 0;
			try {
				if (mCarrito.getIdcarrito() != 0) {
					do {
						mProducto.consultarProducto(mCarrito.getIdreferencia());
						listaProductos[contadorCarrito] = new Cproducto(mProducto.getIdreferencia(),
								mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
								mProducto.getPrecio(), null, mProducto.getStock(), mProducto.getSubcategoria());
						listaidCarrito[contadorCarrito] = mCarrito.getIdcarrito();
						contadorCarrito++;
					} while (mCarrito.consultarSiguiente());
				}

			} catch (Exception e) {

			}
			sesion.setAttribute("ProductosCarrito", listaProductos);
			sesion.setAttribute("idCarritos", listaidCarrito);
			request.getRequestDispatcher("WEB-INF/carrito.jsp").forward(request, response);

		} // Fin usuario iniciado

		else {
			sesion.setAttribute("ProductosCarrito", null);
			if (request.getParameter("idproducto") != null) {
				CookieProducto = new Cookie(request.getParameter("idproducto"), request.getParameter("idproducto"));
				response.addCookie(CookieProducto);
				listaProductos = new Cproducto[25];
				contadorCookies = 0;
				cookies = request.getCookies();
				try {

					for (int i = 0; i < cookies.length; i++) {
						mProducto.consultarProducto(cookies[i].getValue());
						if (!cookies[i].getName().equals("JSESSIONID")) {
							listaProductos[contadorCookies] = new Cproducto(mProducto.getIdreferencia(),
									mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
									mProducto.getPrecio(), null, mProducto.getStock(), mProducto.getSubcategoria());
							contadorCookies++;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception

				}

				sesion.setAttribute("ProductosCookie", listaProductos);
				response.sendRedirect("Carrito");

			} // Fin de Carga de producto con añadido COOKIE
			else {
				listaProductos = new Cproducto[25];
				contadorCookies = 0;
				cookies = request.getCookies();
				try {

					for (int i = 0; i < cookies.length; i++) {
						mProducto.consultarProducto(cookies[i].getValue());
						if (!cookies[i].getName().equals("JSESSIONID")) {
							listaProductos[contadorCookies] = new Cproducto(mProducto.getIdreferencia(),
									mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
									mProducto.getPrecio(), null, mProducto.getStock(), mProducto.getSubcategoria());
							contadorCookies++;
						}
					}

				} catch (Exception e) {
					// TODO: handle exception

				}
				sesion.setAttribute("ProductosCookie", listaProductos);
				request.getRequestDispatcher("WEB-INF/carrito.jsp").forward(request, response);

			} // Fin de carga de producto base COOKIE

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
