<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTAR CLIENTES</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="MenuAdmin.jsp"></jsp:include>
	<!-- Hacer MENU ADMIN -->
	<!-- Aca va el form direccionando al servlet -->

	<div class="container-fluid" style="margin-top: 1em;">
		<div>
			<h2>LISTADO DE CLIENTES</h2>
			<!-- Ingresar USUARIO SESION -->
			<br>
		</div>
		<label>Busqueda nro de cliente: </label>
		<div class="input-group mb-3">
			<div class="input-group-prepend"></div>
			<span class="input-group-text" id="basic-addon1">$</span>
			<input type="text" name="txtNroCuenta" class="form-control" placeholder="Ej: 0202215151" aria-label="Username" aria-describedby="basic-addon1"> 
			<input type="submit" name="btnAceptar" value="Aceptar">
		</div>
		<br> <label>Busqueda por Apellido: </label>
		<div class="input-group mb-3">
			<div class="input-group-prepend"></div>
			<span class="input-group-text" id="basic-addon1">$</span>
			<input type="text" name="txtApellido" class="form-control" placeholder="Ej: Cibrian" aria-label="Username" aria-describedby="basic-addon1"> 
			<input type="submit" name="btnAceptar" value="Aceptar">
		</div>
		<br>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">IdCliente</th>
					<th scope="col">IdUsuario</th>
					<th scope="col">IdCuenta</th>
					<th scope="col">Dni</th>
					<th scope="col">Cuil</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellido</th>
					<th scope="col">Sexo</th>
					<th scope="col">Fecha Nac.</th>
					<th scope="col">Direcion</th>
					<th scope="col">Localidad</th>
					<th scope="col">Provincia</th>
					<th scope="col">Mail</th>
					<th scope="col">Telefo</th>
					<th scope="col">#</th>
					<th scope="col">#</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>213213242</td>
					<td>123213</td>
					<td>25252525</td>
					<td>20252525257</td>
					<td>Juan</td>
					<td>Pepe</td>
					<td>M</td>
					<td>20/08/2000</td>
					<td>CalleFalse 1234</td>
					<td>Tigre</td>
					<td>Bs As</td>
					<td>pepe@gmail.com</td>
					<td>01115656565</td>
					<td><input type="submit" name="btnModificar" value="Modificar"></td>
					<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>5050505</td>
					<td>2024020</td>
					<td>25252525</td>
					<td>20252525257</td>
					<td>Mario</td>
					<td>Iglesias</td>
					<td>M</td>
					<td>05/10/1980</td>
					<td>Canada 3545</td>
					<td>Tortuguitas</td>
					<td>Bs As</td>
					<td>mario@gmail.com</td>
					<td>01115651515</td>
					<td><input type="submit" name="btnModificar" value="Modificar"></td>
					<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>0000012112</td>
					<td>999992520522</td>
					<td>63636324</td>
					<td>2065656567</td>
					<td>Luis</td>
					<td>Garcia</td>
					<td>M</td>
					<td>02/02/1991</td>
					<td>12 de Octubre 2566</td>
					<td>Alberti</td>
					<td>Bs As</td>
					<td>garcialuis45@gmail.com</td>
					<td>011156569898</td>
					<td><input type="submit" name="btnModificar" value="Modificar"></td>
					<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>