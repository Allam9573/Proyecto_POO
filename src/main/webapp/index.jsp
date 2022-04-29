<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*"%>
<%@ page import="controllers.*"%>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Tienda</title>
</head>
<body>

	<%
	ProductoDAO pDAO = new ProductoDAO();
	ArrayList<Producto> productos;
	
	if(request.getAttribute("resultado")!=null){
		productos = (ArrayList<Producto>)request.getAttribute("resultado");
	}else{
		productos = pDAO.listarProductos();
	}
	
	ClienteDAO cDAO = new ClienteDAO();
	ArrayList<Cliente> clientes = cDAO.listarClientes();
	%>
	<main>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Tienda</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="listarClientes.jsp">Clientes</a></li>
						
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
			<form action="ProductoController" class="form-inline" method="post">
				<input type="text" class="form-control w-25"
					placeholder="Buscar producto" name="queryProducto"
					id="queryProducto" /> <input type="hidden" name="action"
					value="buscar" /> <input type="submit" value="Buscar"
					class="btn btn-success">
			</form>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#ID</th>
						<th scope="col" class="text-indigo-800">Nombre</th>
						<th scope="col">Descripcion</th>
						<th scope="col">Precio</th>
						<th scope="col">Acciones</th>
					</tr>
				</thead>
				<tbody>

					<%
					for (Producto producto : productos) {
						out.print("<tr><td>" + producto.getProductoId() + "</td><td>" + producto.getNombre() + "</td><td>"
						+ producto.getDescripcion() + "</td><td>" + "Lps. " + producto.getPrecio()
						+ "</td><td><button class='btn btn-danger' data-bs-toggle='modal' data-bs-target='#exampleModal'>Editar</button><a class='btn btn-outline-danger mx-2' href='ProductoController?action=eliminar&productoId="
						+ producto.getProductoId() + "'>Eliminar</a><a class='btn btn-success' href='login.jsp'>Comprar</a></td></tr>");
					}
					%>

				</tbody>
			</table>
			
			<h2>
			<%
				if(request.getAttribute("message")!= null){
					out.print(request.getAttribute("message"));
				}
			%>
			</h2>

		</div>
	<%-- 	<form action="ProductoController">
			<input type="hidden" name="action" value="listarProductos">
		</form>
		<div class="container">
			<h2>Usuarios registrados:</h2>

			<form action="ClienteController" method="post">
				<input type="text" name="usuario" placeholder="Usuario" /> <input
					type="text" placeholder="Clave" name="contrasenia" /> <input
					type="hidden" name="action" value="login" /> <input type="submit"
					value="Login" />
			</form>
			<%
			if (request.getAttribute("message") != null) {

				out.println(request.getAttribute("message"));
			}
			%>
		</div> --%>


		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h5 class="modal-title" id="exampleModalLabel">Actualizar
							Producto:</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="ProductoController" method="post">
							<input type="text" name="nombre" placeholder="Nombre"
								class="form-control my-3" /> <input type="text" name="cantidad"
								placeholder="Cantidad" class="form-control my-3" /> <input
								type="text" name="descripcion"
								placeholder="Descripcion de producto" class="form-control my-3" />
							<input type="text" name="precio" placeholder="Precio"
								class="form-control my-3" />
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary"
									data-bs-dismiss="modal">Actualizar</button>
								<button type="submit" class="btn btn-outline-primary"
									data-bs-dismiss="modal">Cancelar</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</main>


	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>



	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
		crossorigin="anonymous"></script>

</body>
</html>