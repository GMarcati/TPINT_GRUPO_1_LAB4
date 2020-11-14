<%@page import="entidad.Usuario"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTAR CLIENTES</title>
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

	<%
		List<Usuario> listaU = new ArrayList<Usuario>();
		if (request.getAttribute("listaUsuario") != null) {
			listaU = (List<Usuario>) request.getAttribute("listaUsuario");
		}
	%>
	
	<%
	if (request.getAttribute("usuarioEliminado") != null) 
	{%>

		<script>alert("Usuario eliminado con exito")</script>
			
	<% }
	%>
	
	usuarioEliminado

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  style="margin-bottom: 1em;">
        <div class="container">

			<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
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
						<a class="dropdown-item" href="AltaCliente.jsp" >Alta cliente</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletUsuario?listadoU">Listar/Modificar/Dar de baja cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AltaCuenta.jsp" >Alta cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="ListaEliminarCuentas.jsp" >Listar/Modificar/Dar de baja cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AsignarCuentaACliente.jsp">Asignar cuenta a cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="ListaPrestamos.jsp">Autorizar préstamos</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="VerInformes.jsp" >Ver informes</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="Logoff.jsp" >Cerrar sesion</a> 
					</div>
				</li>
						 					
                </ul>


            </div>
        </div>

    </nav>
	<!-- Hacer MENU ADMIN -->
	<!-- Aca va el form direccionando al servlet -->

	<div class="container-fluid" style="margin-top: 1em;">
		<div>
			<h2>LISTADO DE CLIENTES</h2>
			<br>
		</div>
		<br>

<table id="table_id" style="width:100%" class="table table-striped table-bordered " >
<thead >
		<tr class="text-center">
			<th>ID USUARIO	</th>
			<th>USUARIO</th>
			<th>DNI</th>
			<th>CUIL</th>
			<th>NOMBRE</th>
			<th>APELLIDO</th>
			<th>SEXO	</th>
			<th>FECHA NACIMIENTO	</th>
			<th>DIRECCION	</th>
			<th>NACIONALIDAD	</th>
			<th>PROVINCIA	</th>
			<th>LOCALIDAD	</th>
			<th>MAIL	</th>
			<th>TELEFONO	</th>
			<th>ACCION	</th>	
		</tr>
		</thead>


		<tbody>
		<%
			for (Usuario usuario : listaU) {
		%>
		<tr>
			<td><%=usuario.getIdUsuario()%></td>
			<td><%=usuario.getNombre()%></td>
			<td><%=usuario.getDni()%></td>
			<td><%=usuario.getCuil()%></td>
			<td><%=usuario.getNombre()%></td>
			<td><%=usuario.getApellido()%></td>
			<td><%=usuario.getSexo()%></td>
			<td><%=usuario.getFechaNac()%></td>
			<td><%=usuario.getDireccion()%></td>
			<td><%=usuario.getNacionalidad().getDescripcion()%></td>
			<td><%=usuario.getProvincia().getDescripcion()%></td>
			<td><%=usuario.getLocalidad().getDescripcion()%></td>
			<td><%=usuario.getMail()%></td>
			<td><%=usuario.getTelefono()%></td>
			<td class="text-center"><a href="servletUsuario?idModificar=<%=usuario.getIdUsuario()%>" class="btn btn-secondary btn-sm" style="width: 80px;">Modificar</a> <br><br> <a href="servletUsuario?idEliminar=<%=usuario.getIdUsuario()%>" class="btn btn-danger btn-sm" style="width: 80px;">Eliminar</a></td>
		</tr>
		

		<%
			}
		%>
		</tbody>


	</table>
	

	
	
	</div>


</body>
</html>