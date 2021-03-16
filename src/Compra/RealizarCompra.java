package Compra;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Catalogo.Cproducto;
import Modelos.MCarrito;
import Modelos.MPedPro;
import Modelos.MPedido;

/**
 * Servlet implementation class RealizarCompra
 */
@WebServlet("/RealizarCompra")
public class RealizarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
	MPedido mPedido = new MPedido();
	MPedPro mPedPro = new MPedPro();
	MCarrito mCarrito = new MCarrito();
	Cproducto[] listaProductos;

	private void AnadirPedPro(int pIdPedido, Cproducto[] pListaProductos) {
		// TODO Auto-generated method stub
		listaProductos = pListaProductos;
		
		try {
			for (int i = 0; i < pListaProductos.length; i++) {
				mPedPro.AnadirRelacion(pIdPedido,listaProductos[i].getIdreferencia());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion = request.getSession();
		if ((boolean) sesion.getAttribute("Iniciado")) {

			request.getRequestDispatcher("WEB-INF/realizarcompra.jsp").forward(request, response);

			
		}else {
			response.sendRedirect("Carrito");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			sesion = request.getSession();
			mPedido.getUltimoPedido();
			mPedido.AnadirPedido(
					(mPedido.getIdpedido())+1,
					(int)sesion.getAttribute("idcliente"),
					Integer.parseInt(request.getParameter("fTelefono")),
					request.getParameter("fCalle"),
					request.getParameter("fProvincia"),
					request.getParameter("fLocalidad"),
					Integer.parseInt(request.getParameter("fCp")),
					request.getParameter("fPais"));
			AnadirPedPro((mPedido.getIdpedido())+1,(Cproducto[]) sesion.getAttribute("ProductosCarrito"));
			mCarrito.vaciarCarrito((int)sesion.getAttribute("idcliente"));
			request.getRequestDispatcher("WEB-INF/comprarealizada.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}
		

	
	}



}


