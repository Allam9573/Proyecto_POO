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
				</tr>
			</thead>
			<tbody>
				<%
				for (Cliente cliente : clientes) {
					out.print("<tr>" + "<td>" + cliente.getClienteId() + "</td>" + "<td>" + cliente.getNombre() + "</td>" + "<td>"
					+ cliente.getApellido() + "</td>" + "<td>" + cliente.getUsuario() + "</td>" + "<td>" + cliente.getCorreo()
					+ "</td>" + "</tr>");
				}
				%>

			</tbody>
		</table>

	</div>

</body>
</html>