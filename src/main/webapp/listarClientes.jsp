<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="dao.*"%>
<%@ page import="controllers.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Listado de Clientes:</title>
</head>
<body>
	<%
ClienteDAO cDAO = new ClienteDAO();
ArrayList<Cliente> clientes = cDAO.listarClientes();
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="index.jsp">Tienda</a>
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
	<div class="container text-center mt-5	">
		<h2>Listado de Clientes:</h2>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellido</th>
					<th scope="col">Usuario</th>
					<th scope="col">Correo</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Cliente cliente : clientes) {
					out.print("<tr>" + "<td>" + cliente.getClienteId() + "</td>" + "<td>" + cliente.getNombre() + "</td>" + "<td>"
					+ cliente.getApellido() + "</td>" + "<td>" + cliente.getUsuario() + "</td>" + "<td>" + cliente.getCorreo()
					+ "</td>" + "<td><button class='btn btn-danger'>Editar</button><a class='btn btn-danger ml-4' href='ClienteController?action=eliminar&clienteId="+cliente.getClienteId()+"'>Eliminar</a></td>"+"</tr>");
				}
				%>

			</tbody>
		</table>

	</div>

</body>
</html>