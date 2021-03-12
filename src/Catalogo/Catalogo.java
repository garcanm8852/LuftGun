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

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int numeroCategorias;
	HttpSession sesion;
	MCategoria mCategoria = new MCategoria();
	String[] categorias;
	int ContadorCategorias; 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
				ContadorCategorias ++;
			} while (mCategoria.consultarSiguiente());		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categorias;
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion= request.getSession(true);
		if (sesion.getAttribute("Validacion") == null) {
			sesion.setAttribute("Validacion", (int) (Math.random()*999999+1));
		}
		sesion.setAttribute("Categorias", almacenarCategorias());
		request.getRequestDispatcher("WEB-INF/catalogo.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse resonse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	


}
