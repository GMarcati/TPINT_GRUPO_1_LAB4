<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar préstamo</title>
</head>

<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>

	<div class="container body-content" style="margin-top: 5em;"><br />
		<div class="jumbotron jumbotron-fluid text-center rounded-pill">
			<div class="container">
				<h1 class="display-4">Solicitar préstamo</h1>
			</div>
		</div>
		<hr />
		<div class="form-group">
			<label for="exampleInputEmail1">Monto</label> 
			<input type="text" class="form-control small" Style="width: 500px">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Cantidad cuotas</label>
			 <input type="text" class="form-control small" Style="width: 500px">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Cuenta destino</label>
			<input type="text" class="form-control small" Style="width: 500px">
		</div>
		<a href="SolicitarPrestamo.jsp" class="btn btn-primary">Solicitar</a>
		<div>
			<br/>
		</div>
	</div>
</body>
</html>