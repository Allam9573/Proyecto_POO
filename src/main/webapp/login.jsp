<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
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
<title>Insert title here</title>
</head>
<body>
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
					<li class="nav-item"><a class="nav-link"
						href="listarClientes.jsp">Clientes</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Features</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Pricing</a>
					</li>
					<li class="nav-item"><a class="nav-link disabled">Disabled</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container text-center">
		<div class="card" style="width: 18rem;">
			<div class="card-header">
				<h2>Inicio de Sesion</h2>
			</div>
			<div class="card-body">
				<form action="ClienteController" method="post">
					<div class="form-group mb-2">
						<input type="text" name="usuario" class="form-control"
							placeholder="Usuario" />
					</div>
					<div class="form-group mb-2">
						<input type="password" class="form-control"
							placeholder="Contrasenia" name="password" />
					</div>
					<input type="hidden" name="action" value="login" />
					<div class="form-group">
						<input class="btn btn-primary" type="submit"
							value="Iniciar Sesion" /> <br> <a href="" class="link">No
							tengo cuenta!</a>
					</div>
				</form>

			</div>
		</div>
	</div>
	<%
	if (request.getAttribute("message") != null) {
		out.print(request.getAttribute("message"));
	}
	%>

</body>
</html>