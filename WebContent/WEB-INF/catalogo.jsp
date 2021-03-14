<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@page import="Catalogo.Cproducto"%>

<!DOCTYPE html>
<%
	Cproducto[] listaProductos;
%>


<html lang='en'>

<head>
<meta charset='UTF-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css'
	integrity='sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l'
	crossorigin='anonymous'>
<script src='https://code.jquery.com/jquery-3.5.1.slim.min.js'
	integrity='sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj'
	crossorigin='anonymous'></script>
<script
	src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js'
	integrity='sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns'
	crossorigin='anonymous'></script>
<title>Catálogo</title>
</head>

<body>

	<header>
		<nav class='navbar navbar-expand-lg navbar-light bg-light fixed-top '>
			<a class='navbar-brand '>LuftGun</a>
			<button class='navbar-toggler ' data-target='#my-nav '
				data-toggle='collapse ' aria-controls='my-nav '
				aria-expanded='false ' aria-label='Toggle navigation '>
				<span class='navbar-toggler-icon '></span>
			</button>
			<div id='my-nav ' class='collapse navbar-collapse '>
				<ul class='navbar-nav mr-auto '>
					<li class='nav-item '><a class='nav-link ' href='Index '>Inicio</a>
					</li>
					<li class='nav-item'><a class='nav-link ' href='Catalogo '
						tabindex='-1 ' aria-disabled='true '>Catalogo</a></li>
					<%
						String[] categorias = (String[]) session.getAttribute("Categorias");
						for (int i = 0; i < categorias.length; i++) {
							out.print("<li class='nav-item '><a class='nav-link ' href='Catalogo?idcategoria=" + (i + 1)
									+ "' tabindex='-1 ' aria-disabled='true '>" + categorias[i] + "</a></li>");
						}
					%>

					<%
						if ((boolean) session.getAttribute("Iniciado")) {

							out.print("<li class='nav-item active' style='color:#007bff;'> Bienvenido"
									+ (String) session.getAttribute("NombreUsuario"));
							out.print(
									"<a class='nav-link ' href='CerrarSesion' tabindex='-1 ' aria-disabled='true '>Cerrar	Sesión</a>");
							out.print("</li>");

						} else {

							out.print("<li class='nav-item active'>");
							out.print(
									"<a class='nav-link ' href='IniciarSesion ' tabindex='-1 ' aria-disabled='true '>Iniciar	Sesión</a>");
							out.print("</li>");

							out.print("<li class='nav-item  '>");
							out.print("<a class='nav-link ' href='Registro ' tabindex='-1 ' aria-disabled='true '>Registrarse</a>");
							out.print("</li>");

						}
					%>
				</ul>
			</div>
		</nav>
	</header>


	<section class='container mt-5'>
		<article class='row mt-5'>
			<div class='col-md-12 mt-5'>
				<h1>Artículos de LuftGun</h1>
				<h2></h2>
			</div>
		</article>

		<article class='row justify-content-between'>
			<div class='col-md-12'>
				<div class="row">

					<%
						try {
							listaProductos = (Cproducto[]) session.getAttribute("Productos");
							for (int j = 0; j < listaProductos.length; j++) {

								out.print("<div class='col-md-3 mt-5'>");
								out.print("<div class='card w-100'>");
								out.print("<div class='card-body'>");
								out.print("<img class='card-img-top' src = '/LuftGun/DecodificarImagen?idreferencia="
										+ listaProductos[j].getIdreferencia() + "'>");
								out.print("<h5 class='card-title'>" + listaProductos[j].getNombre() + "</h5>");
								out.print("<h6 class='card-subtitle mb-2 text-muted'>" + listaProductos[j].getMarca() + "</h6>");
								out.print("<p class='card-text text-center bold'>" + listaProductos[j].getPrecio() + "€</p>");
								out.print("<a href='Producto?idreferencia=" + listaProductos[j].getIdreferencia()
										+ "' class='btn btn-primary w-100'>Comprar</a>");
								out.print("</div>");
								out.print("</div>");
								out.print("</div>");

							}
						} catch (Exception e) {
							// Throw e;

						}
					%>
				</div>
			</div>
		</article>

	</section>



</body>

</html>