<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Principal cliente</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 1em;">
    	<div class="container body-content">
  		<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesion de <%= Usuario.getNombreUsu()%></span>
  		 <span class="navbar-text">
   	   	<a class="nav-item" href="#">Cerrar sesion</a>
    	</span>
  		</div>
	</nav>
	
	<div class="container body-content" style="margin-top: 1em;">
	<h2 style="margin-left:70px; margin-top: 50px">
		Banco Sarasa,<br>
		SU banco.
	</h2> <br>
	
	 <div style="margin-left: 40px">
	 	<a href="ListaCuentasCliente.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Cuentas</a> <br>
	 	<a href="Transferir.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Transferir</a> <br>
	 	<a href="SolicitarPrestamo.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Solicitar préstamo</a> <br>
	 	<a href="ListaPrestamosCliente.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Ver préstamos adquiridos</a> <br>
	 	<a href="DatosPersonales.jsp" class="btn btn-outline-primary" style="width: 450px; margin:10px">Mis datos</a> <br>
	 </div>
	 </div>
	 
	 
</body>
</html>