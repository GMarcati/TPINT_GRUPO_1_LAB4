<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>LISTAR CUENTAS</title>
</head>
<body>
	<jsp:include page="MenuCliente.jsp"></jsp:include>
	<div class="container-fluid" style="margin-top: 5em;">
		<div><h2>LISTADO DE CUENTAS</h2><!-- Ingresar USUARIO SESION --><br></div>
	
		<label>Busqueda por NroCuenta: </label> 	
		<div class="input-group mb-3">
		  	<div class="input-group-prepend"></div>
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