<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTAR CUENTAS</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container body-content">
		<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
        
    	<span class="navbar-text">
   	   	<a class="nav-item" href="PrincipalCliente.jsp">Menu cliente</a>
    	</span>
  		</div>
  		
  
</nav>
<div class="container-fluid" style="margin-top: 1em;">
<div><h2>LISTADO DE CUENTAS</h2><!-- Ingresar USUARIO SESION --><br></div>

<label>Busqueda por NroCuenta: </label> 	
<div class="input-group mb-3">
  <div class="input-group-prepend">
  </div>
	<span class="input-group-text" id="basic-addon1">$</span><input type="text" name="txtNroCuenta" class="form-control" placeholder="Ej: 1214545" aria-label="Username" aria-describedby="basic-addon1">
	<input type="submit" name="btnAceptar" value="Aceptar">
</div><br>

<table class="table">
  <thead>
    <tr>
      <th scope="col">IdCuenta</th>
      <th scope="col">NroCuenta</th>
      <th scope="col">TipoCuenta</th>
      <th scope="col">FechaCreacion</th>
      <th scope="col">CBU</th>
      <th scope="col">Saldo</th>
      <th scope="col">Historial</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>213213242</td>
      <td>Cuenta Corriente</td>
      <td>25/02/1985</td>
      <td>20252525257</td>
      <td>$25000</td>
      <td><a href="MovimientosCuentaCliente.jsp?id" class="btn btn-primary" >Movimientos</a> <br></td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>2245242</td>
      <td>Cuenta Sueldo</td>
      <td>10/05/1995</td>
      <td>20252525257</td>
      <td>$50000</td>
      <td><a href="MovimientosCuentaCliente.jsp?id" class="btn btn-primary" >Movimientos</a> <br></td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>5050505</td>
      <td>Cuenta Fondo Desempleo</td>
      <td>28/03/2010</td>
      <td>20252525257</td>
      <td>$70000</td>
      <td><a href="MovimientosCuentaCliente.jsp?id" class="btn btn-primary" >Movimientos</a> <br></td>
    </tr>
  </tbody>
</table>
</div>
</body>
</html>