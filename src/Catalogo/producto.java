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
	HttpSession sesion;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MProducto mProducto = new MProducto();
		String idreferencia;
		Cproducto productoSolicitado;
		int ContadorCategorias;
		String[] categorias;
		int numeroCategorias;
		MCategoria mCategoria = new MCategoria();

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		sesion = request.getSession(true);

		if (sesion.getAttribute("Iniciado") == null) {
			sesion.setAttribute("Iniciado", false);
		}

		try {
			numeroCategorias = 1;
			mCategoria.cargarCategorias();
			while (mCategoria.consultarSiguiente()) {
				numeroCategorias++;
			}

			try {
				categorias = new String[numeroCategorias];
				mCategoria.cargarCategorias();
				ContadorCategorias = 0;
				do {
					categorias[ContadorCategorias] = mCategoria.getnombreCategoria();
					ContadorCategorias++;
				} while (mCategoria.consultarSiguiente());
				sesion.setAttribute("Categorias", categorias);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			mProducto.consultarProducto(request.getParameter("idreferencia"));
			productoSolicitado = new Cproducto(mProducto.getIdreferencia(), mProducto.getNombre(), mProducto.getMarca(),
					mProducto.getDescripcion(), mProducto.getPrecio(), mProducto.getStock(), mProducto.getCategoria(),
					mProducto.getSubcategoria(), mProducto.getNombreCategoria(), mProducto.getNombreSubcategoria());
			sesion.setAttribute("Producto", productoSolicitado);
		} catch (Exception e) {
			// TODO: handle exception
		}

		request.getRequestDispatcher("WEB-INF/producto.jsp").forward(request, response);
	}

}
