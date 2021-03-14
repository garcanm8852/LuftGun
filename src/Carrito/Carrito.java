package Carrito;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
	MCarrito mCarrito = new MCarrito();
	Cproducto[] listaProductos;
	MProducto mProducto = new MProducto();
	int contadorCarrito;

	protected void anadirProductoCarritoBD(String pReferencia, int pIdcliente) {
		try {
			mCarrito.AnadirProductoCarrito(pReferencia, pIdcliente);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected Cproducto[] almacenarProductosCarrito() {
		mCarrito.cargarCarrito((int) sesion.getAttribute("idcliente"));
		listaProductos = null;
		contadorCarrito = 0;
		try {
			do {
				mProducto.consultarProducto(mCarrito.getIdreferencia());
				listaProductos[contadorCarrito] = new Cproducto(mProducto.getIdreferencia(), mProducto.getNombre(),
						mProducto.getMarca(), mProducto.getDescripcion(), mProducto.getPrecio(), null,
						mProducto.getStock(), mProducto.getSubcategoria());

				contadorCarrito++;
			} while (mCarrito.consultarSiguiente());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listaProductos;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest reqst, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		sesion = request.getSession(true);

		if ((boolean) sesion.getAttribute("Iniciado") == true) {
			anadirProductoCarritoBD(request.getParameter("idproducto"),
					(int) sesion.getAttribute("idcliente"));
			
			sesion.setAttribute("ProductosCarrito", almacenarProductosCarrito());
			request.getRequestDispatcher("WEB-INF/carrito.jsp").forward(request, response);

		}



		 else {

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
