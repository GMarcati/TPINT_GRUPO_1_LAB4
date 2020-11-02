<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Abonar cuota préstamo</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 1em;">
		<div class="container body-content">
  		<span class="navbar-brand mb-0 h1">nombreUsuario</span>
  		<span class="navbar-text">
   	   	<a class="nav-item" href="PrincipalCliente.jsp">Menu cliente</a>
    	</span>
  		</div>
	</nav>
<div class="container body-content">	
	<h2> PAGAR PRESTAMO</h2>
	<div class="m-3">
  		<label for="exampleInputEmail1">Seleccione la cuenta de origen:</label>
        <input type="text" class="form-control small" Style="width: 500px">  <!-- seria ideal un desplegable con las cuentas del cliente -->
        <label for="exampleInputEmail1">Indique que cuenta va a abonar:</label>
        <input type="text" class="form-control small" Style="width: 500px"><br>  <!-- seria ideal un desplegable con las cuentas pendientes -->
        <a href="#" class="btn btn-primary">PAGAR</a>
    </div>
</div>
	
</body>
</html>