<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informes</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container body-content">
		<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
        
    	<span class="navbar-text">
   	   	<a class="nav-item" href="PrincipalAdministrador.jsp">Menu admin</a>
    	</span>
  		</div>
  		
  
</nav>
<div class="container-fluid" style="margin-top: 1em;">

<h2>Prestamos por cuenta en estado aceptados</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">IdPrestamo</th>
      <th scope="col">IdCuenta</th>
      <th scope="col">Numero de cuenta</th>
      <th scope="col">Tipo de cuenta</th>
      <th scope="col">CBU</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>4</td>
      <td>Cuenta Corriente</td>
      <td>102525896478</td>
      <td>0056423114542635784752</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>6</td>
      <td>Cuenta Sueldo</td>
      <td>202525252578</td>
      <td>0056423758942156345752</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>4</td>
      <td>Cuenta Fondo Desempleo</td>
      <td>282525752579</td>
      <td>0056423114542156345752</td>
    </tr>
  </tbody>
</table>
</div>
</body>
</html>