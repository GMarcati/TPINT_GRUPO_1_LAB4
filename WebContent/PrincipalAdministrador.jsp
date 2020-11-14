<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<title>Principal administrador</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 1em;">
    	<div class="container body-content">
  			<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesión de <%= Usuario.getNombreUsu()%></span>
  				<span class="navbar-text"><a class="nav-item" href="#">Cerrar sesión</a></span>
  		</div>
	</nav>
	
	<%
		if (request.getAttribute("estadoCuenta") != null) {
	%>
		<script>alert("Login realizado con exito")</script>
	<%
		}
	%>
	
	<div class="container body-content" style="margin-top: 1em;">
		<h2 style="margin-left:70px; margin-top: 50px">
			Bienvenido admin,<br>
			¿Que desea realizar?
		</h2><br>
	 
		<div style="margin-left: 40px">
		 	<a href="AltaCliente.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Alta cliente</a> <br>
		 	<a href="servletUsuario?listadoU" class="btn btn-outline-primary" style="width: 450px; margin:10px">Listar/Modificar/Dar de baja cliente</a> <br>
			<a href="AltaCuenta.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Alta cuenta</a> <br>
			<a href="ListaEliminarCuentas.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Listar/Modificar/Dar de baja cuenta</a> <br>
			<a href="AsignarCuentaACliente.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Asignar cuenta a cliente</a> <br>
			<a href="ListaPrestamos.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Autorizar préstamos</a> <br>
			<a href="VerInformes.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Ver informes</a> <br>
		</div>
	</div>
<!-- Desde aca vamos a poder redireccionar para realizar ABM de clientes y en esta pagina vamos a poder ver el listado-->
	

<!-- Listado de clientes -->


</body>
</html>