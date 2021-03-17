package Catalogo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelos.MCategoria;
import Modelos.MProducto;

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] categorias = null;
		Cproducto[] listaProductos;
		int ContadorCategorias;
		int contadorProductos;
		int numeroPaginas = 0;
		MCategoria mCategoria = new MCategoria();
		MProducto mProducto = new MProducto();
		int numeroCategorias = 1;

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		sesion = request.getSession(true);

		/* Obetención del número de categorias y Categorías */
		try {
			mCategoria.cargarCategorias();
			while (mCategoria.consultarSiguiente()) {
				numeroCategorias++;
			}

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

		/**
		 * Control de estado de sesión.
		 * */
		if (sesion.getAttribute("Iniciado") == null) {
			sesion.setAttribute("Iniciado", false);
		}

		/**
		 * Conteo y creación del número de páginas
		 * */
		try {
			if (mProducto.numeroRegistros() % 5 == 0) {
				numeroPaginas = mProducto.numeroRegistros() / 5;
			} else {
				numeroPaginas = (mProducto.numeroRegistros() / 5) + 1;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * Carga de catálogo por categaría
		 */
		if (request.getParameter("idcategoria") != null) {
			/**
			 * Carga de catálogo con búsqueda por categorias
			 */
			listaProductos = new Cproducto[25];
			mProducto.cargarProductosPorCategorias(Integer.parseInt(request.getParameter("idcategoria")));

			contadorProductos = 0;
			try {
				do {
					listaProductos[contadorProductos] = new Cproducto(mProducto.getIdreferencia(),
							mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
							mProducto.getPrecio(), mProducto.getStock(), mProducto.getCategoria(),
							mProducto.getSubcategoria(), mProducto.getNombreCategoria(),
							mProducto.getNombreSubcategoria());
					;
					contadorProductos++;
				} while (mProducto.consultarSiguiente());
				sesion.setAttribute("Productos", listaProductos);
				
			} catch (Exception e) {
				// TODO: handle exception
			}


		} else {
			/**
			 * Carga de catálogo con búsqueda por subcategorias
			 */
			if (request.getParameter("idsubcategoria") != null) {
				listaProductos = new Cproducto[25];
				mProducto.cargarProductosPorSubcategorias(Integer.parseInt(request.getParameter("idsubcategoria")));

				contadorProductos = 0;
				try {
					do {
						listaProductos[contadorProductos] = new Cproducto(mProducto.getIdreferencia(),
								mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
								mProducto.getPrecio(), mProducto.getStock(), mProducto.getCategoria(),
								mProducto.getSubcategoria(), mProducto.getNombreCategoria(),
								mProducto.getNombreSubcategoria());
						;
						contadorProductos++;
					} while (mProducto.consultarSiguiente());

				} catch (Exception e) {
					// TODO: handle exception
				}
				sesion.setAttribute("Productos", listaProductos);
				
			} else {

				/**
				 * Carga de catálogo paginada - Página 0.
				 */
				sesion.setAttribute("NumeroPaginas", numeroPaginas);

				if (request.getParameter("Pagina") == null) {

					listaProductos = new Cproducto[5];
					mProducto.cargarProductosPaginados(0);
					contadorProductos = 0;
					try {
						do {
							listaProductos[contadorProductos] = new Cproducto(mProducto.getIdreferencia(),
									mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
									mProducto.getPrecio(), mProducto.getStock(), mProducto.getSubcategoria());
							contadorProductos++;
						} while (mProducto.consultarSiguiente());
					} catch (Exception e) {

						e.printStackTrace();
					}

					sesion.setAttribute("Productos", listaProductos);

				} else {
					/**
					 * Carga de catálogo paginada - Página por parámetros.
					 */
					listaProductos = new Cproducto[5];
					mProducto.cargarProductosPaginados(Integer.parseInt(request.getParameter("Pagina")));
					contadorProductos = 0;
					try {
						do {
							listaProductos[contadorProductos] = new Cproducto(mProducto.getIdreferencia(),
									mProducto.getNombre(), mProducto.getMarca(), mProducto.getDescripcion(),
									mProducto.getPrecio(), mProducto.getStock(), mProducto.getSubcategoria());
							contadorProductos++;
						} while (mProducto.consultarSiguiente());
					} catch (Exception e) {

						e.printStackTrace();
					}

					sesion.setAttribute("Productos", listaProductos);
				}
			}
		}

		request.getRequestDispatcher("WEB-INF/catalogo.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
