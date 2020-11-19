<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>LISTA PRESTAMOS</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<!-- DATATABLE -->
<!-- CSS para datatable -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<!-- Javascript para datatable -->
<script src="https://code.jquery.com/jquery-3.5.1.js">  </script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js">  </script>
 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 
 <script>
 $(document).ready( function () {
 $('#table_id').DataTable({
  "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
            }
 
 }
 
 );

 } );
 </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  style="margin-bottom: 1em;">
        <div class="container">

			<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesión de <%= session.getAttribute("NombreUsuario")%></span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">

                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<li class="nav-text dropdown">
				<a class="nav-link dropdown-toggle " href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Menu admin
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="servletUsuario?listarSelects" >Alta cliente</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletUsuario?listadoU">Listar/Modificar/Dar de baja cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AltaCuenta.jsp" >Alta cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletCuenta?listadoC" >Listar/Modificar/Dar de baja cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletCuenta?AsignarCuenta">Asignar cuenta a cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="ListaPrestamos.jsp">Autorizar préstamos</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="VerInformes.jsp" >Ver informes</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletLogin?Logoff" >Cerrar sesion</a> 
					</div>
				</li>
						 					
                </ul>


            </div>
        </div>

    </nav>
    
	<div class="container-fluid" style="margin-top: 5em;">
		<div>
			<h2>LISTADO DE PRESTAMOS</h2><br>
		</div>

		<label>Busqueda por IdCliente: </label> 	
		<div class="input-group mb-3">
			<div class="input-group-prepend"></div>
			<input type="text" name="txtNroCuenta" class="form-control" placeholder="Ej: 10" aria-label="Username" aria-describedby="basic-addon1">
			<input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary ml-4">
		</div><br>

		<table class="table">
			<thead>
		    	<tr>
					<th scope="col">Número cuenta</th>
				    <th scope="col">Monto solicitado</th>
				    <th scope="col">Monto a devolver</th>
				    <th scope="col">Cuotas</th>
				    <th scope="col">Valor cuota</th>
				    <th scope="col">Estado</th>
				    <th scope="col">Accion</th>
		    	</tr>
		  	</thead>
			<tbody>
				<tr>
			  	    <td>213213242</td>
					<td>$5000</td>
					<td>$10000</td>
			     	<td>12</td>
			     	<td>833.33</td>
			     	<td>Pendiente</td>
			      	<td><a href="ListaPrestamos.jsp?estado" class="btn btn-success" >Aprobado</a> <a href="ListaPrestamos.jsp?estado" class="btn btn-danger" >Rechazado</a><br></td>
			    </tr>
			    <tr>
			      	<td>2245242</td>
			      	<td>$3000</td>
			      	<td>$6000</td>
			      	<td>6</td>
			      	<td>833.33</td>
			      	<td>Aprobado</td>
					<td><a href="ListaPrestamos.jsp?estado" class="btn btn-success" >Aprobado</a> <a href="ListaPrestamos.jsp?estado" class="btn btn-danger" >Rechazado</a><br></td>
			    </tr>
		    	<tr>
		      		<td>5050505</td>
		      		<td>$10000</td>
		      		<td>$20000</td>
		     		<td>36</td>
		     		<td>833.33</td>
		      		<td>Rechazado</td>
					<td><a href="ListaPrestamos.jsp?estado" class="btn btn-success" >Aprobado</a> <a href="ListaPrestamos.jsp?estado" class="btn btn-danger" >Rechazado</a><br></td>
		    	</tr>
		  	</tbody>
		</table>
	</div>
</body>
</html>