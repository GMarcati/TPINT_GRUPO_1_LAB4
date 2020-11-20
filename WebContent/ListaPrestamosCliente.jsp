<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTADO PRESTAMOS</title>

</head>
<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>
	
	<div class="container-fluid" style="margin-top: 5em;">
		<div>
			<h2>LISTADO DE PRESTAMOS</h2>
			<!-- Ingresar USUARIO SESION -->
			<br>
		</div>

		<label>Busqueda por Cuenta: </label>
		<div class="input-group mb-3">
			<div class="input-group-prepend"></div>
			<input type="text" name="txtNroCuenta" class="form-control" placeholder="Ej: 1214545" aria-label="Username" aria-describedby="basic-addon1"> 
			<input type="submit" name="btnAceptar" value="Aceptar">
		</div>
		<br>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">IdPrestamo</th>
					<th scope="col">NroCuenta</th>
					<th scope="col">Fecha Creacion</th>
					<th scope="col">Valor cuota</th>
					<th scope="col">Cuotas</th>
					<th scope="col">Estado</th>
					<th scope="col">Accion</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>213213242</td>
					<td>2020-02-12</td>
					<td>$5000</td>
					<td>18</td>
					<td>Finalizado</td>
					<td><a href="PagarPrestamo.jsp?idPrestamo" class="btn btn-primary">PAGAR</a> <br></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>2245242</td>
					<td>2020-05-10</td>
					<td>$3000</td>
					<td>12</td>
					<td>Activo</td>
					<td><a href="PagarPrestamo.jsp?idPrestamo" class="btn btn-primary">PAGAR</a> <br></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>5050505</td>
					<td>2020-09-20</td>
					<td>$1000</td>
					<td>36</td>
					<td>Activo</td>
					<td><a href="PagarPrestamo.jsp?idPrestamo" class="btn btn-primary">PAGAR</a> <br></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>