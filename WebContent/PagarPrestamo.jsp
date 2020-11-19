<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Abonar cuota préstamo</title>
</head>

<body>

<jsp:include page="MenuCliente.jsp"></jsp:include>
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