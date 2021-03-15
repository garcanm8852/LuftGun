<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
    <%@page import="Catalogo.Cproducto"%>

        <!DOCTYPE html>
        <%
	Cproducto[] listaProductosCarrito;
        int[] listaidCarrito;
        
%>


            <html lang='en'>

            <head>
                <meta charset='UTF-8'>
                <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css' integrity='sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l' crossorigin='anonymous'>
                <script src='https://code.jquery.com/jquery-3.5.1.slim.min.js' integrity='sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj' crossorigin='anonymous'></script>
                <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js' integrity='sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns' crossorigin='anonymous'></script>
                <title>Realizar Compra</title>
            </head>

            <body>

                <header>
                    <nav class='navbar navbar-expand-lg navbar-light bg-light fixed-top '>
                        <a class='navbar-brand '>LuftGun</a>
                        <button class='navbar-toggler ' data-target='#my-nav ' data-toggle='collapse ' aria-controls='my-nav ' aria-expanded='false ' aria-label='Toggle navigation '>
				<span class='navbar-toggler-icon '></span>
			</button>
                        <div id='my-nav ' class='collapse navbar-collapse '>
                            <ul class='navbar-nav mr-auto '>
                                <li class='nav-item '><a class='nav-link ' href='Index '>Inicio</a>
                                </li>
                                <li class='nav-item'><a class='nav-link ' href='Catalogo ' tabindex='-1 ' aria-disabled='true '>Catalogo</a></li>
                                									<li class="nav-item active"><a class="nav-link " href="Carrito "
						tabindex="-1 " aria-disabled="true ">Carrito</a></li>
                                <%
						String[] categorias = (String[]) session.getAttribute("Categorias");
						for (int i = 0; i < categorias.length; i++) {
							out.print("<li class='nav-item '><a class='nav-link ' href='Catalogo?idcategoria=" + (i + 1)
									+ "' tabindex='-1 ' aria-disabled='true '>" + categorias[i] + "</a></li>");
						}
					%>

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
					%>	
                            </ul>
                        </div>
                    </nav>
                </header>


                <section class='container mt-5'>
                    <article class='row mt-5'>
                        <div class='col-md-12 mt-5'>
                            <h1>Realizar la compra</h1>
                            <h2></h2>
                        </div>
                    </article>

                    <article class='row justify-content-between'>
                        <div class="col-md-4">
                            <form method="post">
                           	<h2>Datos de envio</h2>
                                <div class="form-froup mt-3">
                                    <label for="fTelefono">Número de teléfono:</label>
                                    <input type="tel" class="form-control" minlength="9" name="fTelefono" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fCalle">Calle:</label>
                                    <input type="text" class="form-control" name="fCalle" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fProvincia">Provincia:</label>
                                    <input type="text" class="form-control" name="fProvincia" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fLocalidad">Localidad:</label>
                                    <input type="text" class="form-control" name="fLocalidad" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fCp">Código Postal:</label>
                                    <input type="number" maxlength="5" minlength="5" class="form-control" name="fCp" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fPais">País:</label>
                                    <input type="text" class="form-control" name="fPais" required>
                                </div>
                                <h2 class="mt-5">Datos del método de pago</h2>
                                <div class="form-froup mt-3">
                                    <label for="fNombreTarjeta">Titular de la tarjeta:</label>
                                    <input type="text" class="form-control" name="fNombreTarjeta" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fNumeroTarjeta">Número de la tarjeta:</label>
                                    <input type="number" minlength="16" maxlength="16" class="form-control" name="fNumeroTarjeta" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fMesTarjeta">Mes de vencimiento de la tarjeta:</label>
                                    <input type="number" max="12" min="1" class="form-control" name="fMesTarjeta" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fAnoTarjeta">Año de vencimiento de la tarjeta:</label>
                                    <input type="number" max="99" min="1" class="form-control" name="fAnoTarjeta" required>
                                </div>
                                <div class="form-froup mt-3">
                                    <label for="fCVVTarjeta">Número secreto de la tarjeta:</label>
                                    <input type="number" minlength="3" maxlength="3" min="100" max="999" class="form-control" name="fCVVTarjeta" required>
                                </div>
                                <button type="submit " class="btn w-100 btn-primary mt-3 mb-5">Realizar compra</button>

                            </form>
                        </div>

                        <div class='col-md-8 mt-4'>
                            <div class="row">
                                
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
                                        
                    
                    
                                    }}catch(Exception e){
                                    	
                                    }
                                    
					%>
							
                            </div>
                            <div class="row">
                                         <div class="col-md-12">
                            <h3>El precio final de la compra es <%out.print(precioTotal);%> €</h3>
                            </div>
                            </div>
                        </div>
                    </article>

                </section>



            </body>

            </html>