<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="Catalogo.Cproducto"%>
	
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<title>Carrito</title>
</head>
<%
	Cproducto[] listaProductosCarrito;
	int listaidCarrito[];
%>


<body>

	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top ">
			<a class="navbar-brand ">LuftGun</a>
			<button class="navbar-toggler " data-target="#my-nav "
				data-toggle="collapse " aria-controls="my-nav "
				aria-expanded="false " aria-label="Toggle navigation ">
				<span class="navbar-toggler-icon "></span>
			</button>
			<div id="my-nav " class="collapse navbar-collapse ">
				<ul class="navbar-nav mr-auto ">
					<li class="nav-item "><a class="nav-link " href="Index ">Inicio</a>
					</li>
					<li class="nav-item "><a class="nav-link " href="Catalogo "
						tabindex="-1 " aria-disabled="true ">Catalogo</a></li>
									<li class="nav-item active"><a class="nav-link " href="Carrito "
						tabindex="-1 " aria-disabled="true ">Carrito</a></li>
					<%
						if ((boolean) session.getAttribute("Iniciado")) {

							out.print("<span class='nav-link' style='color:#007bff;'> Bienvenido "
									+ (String) session.getAttribute("NombreUsuario"));
							out.print("</span>");
							out.print("<li class='nav-item' style='color:#007bff;'>");

							out.print(
									"<a class='nav-link ' href='CerrarSesion' tabindex='-1 ' aria-disabled='true '>Cerrar	Sesión</a>");
							out.print("</li>");

						} else {

							out.print("<li class='nav-item '>");
							out.print(
									"<a class='nav-link ' href='IniciarSesion ' tabindex='-1 ' aria-disabled='true '>Iniciar	Sesión</a>");
							out.print("</li>");

							out.print("<li class='nav-item  '>");
							out.print("<a class='nav-link ' href='Registro ' tabindex='-1 ' aria-disabled='true '>Registrarse</a>");
							out.print("</li>");

						}
					%>				</ul>
			</div>
		</nav>
	</header>


	<section class="container mt-5">
		<article class="row mt-5">
			<div class="col-md-12 mt-5">
				<h1>Carrito</h1>
			</div>
		</article>
		<article class="row mt-5">
			<% 
			double precioTotal = 0;

			try {
				if ((boolean) session.getAttribute("Iniciado")) {
				
				listaProductosCarrito = (Cproducto[]) session.getAttribute("ProductosCarrito");
				listaidCarrito = (int[]) session.getAttribute("idCarritos");
				for (int j = 0; j < listaProductosCarrito.length; j++) {

					out.print("<div class='col-md-12 mt-5'>");
					out.print("<div class='card w-100'>");
					out.print("<div class='card-body'>");
					out.print("<div class='row'>");
					out.print("<div class='col-md-3'>");
					out.print("<img class='card-img-top' src = '/LuftGun/DecodificarImagen?idreferencia="
							+ listaProductosCarrito[j].getIdreferencia() + "'>");
					out.print("</div>");
					out.print("<div class='col-md-3'>");
					out.print("<h5 class='card-title'>" + listaProductosCarrito[j].getNombre() + "</h5>");
					out.print("</div>");
					out.print("<div class='col-md-2'>");
					out.print("<h6 class='card-subtitle mb-2 text-muted'>" + listaProductosCarrito[j].getMarca() + "</h6>");
					out.print("</div>");
					out.print("<div class='col-md-2'>");
					out.print("<p class='card-text text-center bold'>" + listaProductosCarrito[j].getPrecio() + "€</p>");
					out.print("</div>");
					out.print("<div class='col-md-2'>");
					out.print("<a href='EliminarProductoCarrito?idcarrito=" + listaidCarrito[j]
							+ "' class='btn btn-primary w-100'>Eliminar</a>");
					out.print("</div>");
					out.print("</div>");
					out.print("</div>");
					out.print("</div>");
					out.print("</div>");
					
					precioTotal = precioTotal + listaProductosCarrito[j].getPrecio();
				}
					


				}else{
					listaProductosCarrito = (Cproducto[]) session.getAttribute("ProductosCookie");
					for (int h = 0; h < listaProductosCarrito.length; h++) {

						out.print("<div class='col-md-12 mt-5'>");
						out.print("<div class='card w-100'>");
						out.print("<div class='card-body'>");
						out.print("<div class='row'>");
						out.print("<div class='col-md-3'>");
						out.print("<img class='card-img-top' src = '/LuftGun/DecodificarImagen?idreferencia="
								+ listaProductosCarrito[h].getIdreferencia() + "'>");
						out.print("</div>");
						out.print("<div class='col-md-3'>");
						out.print("<h5 class='card-title'>" + listaProductosCarrito[h].getNombre() + "</h5>");
						out.print("</div>");
						out.print("<div class='col-md-2'>");
						out.print("<h6 class='card-subtitle mb-2 text-muted'>" + listaProductosCarrito[h].getMarca() + "</h6>");
						out.print("</div>");
						out.print("<div class='col-md-2'>");
						out.print("<p class='card-text text-center bold'>" + listaProductosCarrito[h].getPrecio() + "€</p>");
						out.print("</div>");
						out.print("<div class='col-md-2'>");
						out.print("<a href='EliminarProductoCarrito?idcookie=" + listaProductosCarrito[h].getIdreferencia()
								+ "' class='btn btn-primary w-100'>Eliminar</a>");
						out.print("</div>");
						out.print("</div>");
						out.print("</div>");
						out.print("</div>");
						out.print("</div>");
						
						precioTotal = precioTotal + listaProductosCarrito[h].getPrecio();
					}
					
				}
			} catch (Exception e) {
				// Throw e;

			}
		%>
					</article>
			
                    <div class='row mt-5 mb-5 text-center'>
                        <div class='col-md-6'>
                            <h4>El precio total de su compra es: <%out.print((int)precioTotal); %>€</h4>
                        </div>
                        <div class='col-md-6'>
                        				<%
						if ((boolean) session.getAttribute("Iniciado")) {
							if(precioTotal>0)
							out.print("<a href='RealizarCompra' class='btn btn-primary text-light w-100'>Realizar la compra</a>");
						} else {
							out.print("<a href='IniciarSesion' class='btn btn-primary text-light w-100'>Iniciar Sesión</a>");
							out.print("<p class='text-danger'>Recuerda que si deseas seguir con el pedido debes iniciar sesión.</p>");
							out.print("<p class=''>O</p>");
							out.print("<a href='Registro' class='btn btn-success text-light w-100'>Registrate</a>");


						}
					%>	
                        
                        
                        
                        </div>
                    </div>
			

	</section>



</body>
</html>