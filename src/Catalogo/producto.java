package Catalogo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.MCategoria;
import Modelos.MProducto;

/**
 * Servlet implementation class Producto
 */
@WebServlet("/Producto")
public class Producto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String idreferencia;
	HttpSession sesion;
	MProducto mProducto = new MProducto();
	Cproducto productoSolicitado;
	int ContadorCategorias;
	String[] categorias;
	int numeroCategorias;
	MCategoria mCategoria = new MCategoria();

	protected int numeroCategorias() {

		try {
			numeroCategorias = 1;
			mCategoria.cargarCategorias();
			while (mCategoria.consultarSiguiente()) {
				numeroCategorias++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return numeroCategorias;

	}

	protected String[] almacenarCategorias() {
		try {
			categorias = new String[numeroCategorias()];
			mCategoria.cargarCategorias();
			ContadorCategorias = 0;
			do {
				categorias[ContadorCategorias] = mCategoria.getnombreCategoria();
				ContadorCategorias++;
			} while (mCategoria.consultarSiguiente());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categorias;
	}

	protected Cproducto almacenarProducto(String pidreferencia ) {
			
		try {
			mProducto.consultarProducto(pidreferencia);
			productoSolicitado = new Cproducto(mProducto.getIdreferencia(),
					mProducto.getNombre(),
					mProducto.getMarca(),
					mProducto.getDescripcion(),
					mProducto.getPrecio(),
					mProducto.getStock(),
					mProducto.getCategoria(),
					mProducto.getSubcategoria(),
					mProducto.getNombreCategoria(),
					mProducto.getNombreSubcategoria());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return productoSolicitado;
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
		if (sesion.getAttribute("Validacion") == null) {
			sesion.setAttribute("Validacion", (int) (Math.random() * 999999 + 1));
		}
		sesion.setAttribute("Categorias", almacenarCategorias());
		sesion.setAttribute("Producto", almacenarProducto(request.getParameter("idreferencia")));
		request.getRequestDispatcher("WEB-INF/producto.jsp").forward(request, response);
	}


}
