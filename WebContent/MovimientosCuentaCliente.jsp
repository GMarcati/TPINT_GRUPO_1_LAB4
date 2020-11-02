<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Movimientos</title>
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
<!-- Aca va el form direccionando al servlet -->

<div class="container body-content" style="margin-top: 1em;">
<div><center><h2>MOVIMIENTOS </h2></center><!-- Ingresar USUARIO SESION --></div>
<div><h4>CUENTA: 99999999</h4><!-- Ingresar USUARIO SESION --></div>
<label>Busqueda por fecha: </label> 	
<div class="input-group mb-3">
  <div class="input-group-prepend">
    
  </div>
	<input type="text" name="txtImporteDesde" class="form-control" placeholder="Desde:" aria-label="Username" aria-describedby="basic-addon1">
	<input type="text" name="txtImporteHasta" class="form-control" placeholder="Hasta:" aria-label="Username" aria-describedby="basic-addon1">
	<input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary">
</div><br>

<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Tipo de Movimiento</th>
      <th scope="col">Fecha</th>
      <th scope="col">Detalle</th>
      <th scope="col">Importe</th>
      <th scope="col">Cuenta Destino</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Transferencia</td>
      <td>20/10/2020</td>
      <td>Alquiler</td>
      <td>$ 70.000,-</td>
      <td>00055254480058</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Deposito</td>
      <td>28/10/2020</td>
      <td>Ahorros</td>
      <td>$ 50.000,-</td>
      <td>00044582802581</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Transferencia</td>
      <td>30/10/2020</td>
      <td>Jornal</td>
      <td>$ 30.000,-</td>
      <td>00020000575588</td>
    </tr>
    <tr>
      <th scope="row">4</th>
      <td>Transferencia</td>
      <td>30/10/2020</td>
      <td>Aguinaldo</td>
      <td>$ 15.000,-</td>
      <td>00020000575588</td>
    </tr>
  </tbody>
</table>
</div>

</body>
</html>