<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	
	<title>Modificar Cliente</title>
</head>
<body>

	<%
		Usuario usuario = new Usuario();
		if (request.getAttribute("usuarioFiltrado") != null) {
			usuario = (Usuario)request.getAttribute("usuarioFiltrado");
		}
	%>
			<%
		List<Nacionalidad> listaNac = new ArrayList<Nacionalidad>();
		if (request.getAttribute("listaNacionalidad") != null) {
			listaNac = (List<Nacionalidad>) request.getAttribute("listaNacionalidad");
		}
		%>
		<%
		List<Provincia> listaProv = new ArrayList<Provincia>();
		if (request.getAttribute("listaProvincia") != null) {
			listaProv = (List<Provincia>) request.getAttribute("listaProvincia");
		}
		%>
		<%
		List<Localidad> listaLocal = new ArrayList<Localidad>();
		if (request.getAttribute("listaLocalidad") != null) {
			listaLocal = (List<Localidad>) request.getAttribute("listaLocalidad");
		}
		%>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  style="margin-bottom: 1em;">
        <div class="container">

			<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesion de <%= Usuario.getNombreUsu()%></span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">

                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<li class="nav-text dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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

<form method="get" action="servletUsuario">
<div class="container body-content" style="margin-top: 5em;">
<div><h2>MODIFICAR CLIENTE</h2><br></div>
<div>
 
    
  <label>Constraseña: </label><input type="text" name="txtPass" class="form-control" value="<%=usuario.getContrasenia()%>" ><br>
  <label>DNI: </label><input type="text" name="txtDni" class="form-control" value="<%=usuario.getDni()%>"><br>
  <label>CUIL: </label><input type="text" name="txtCuil" class="form-control" value="<%=usuario.getCuil()%>"><br>
  <label>Nombre: </label><input type="text" name="txtNombre" class="form-control" value="<%=usuario.getNombre()%>"><br>
  <label>Apellido: </label><input type="text" name="txtApellido" class="form-control" value="<%=usuario.getApellido()%>"><br>
  <label>Sexo: </label><input type="text" name="txtSexo" class="form-control" value="<%=usuario.getSexo()%>"><br>
  
  <label>Nacionalidad:</label>
  <select class="form-control">
  
  <% 
  
  for(Nacionalidad nacionalidad : listaNac){    
   %>
   <option value="<%=nacionalidad.getIdNacionalidad() %>"> <%=nacionalidad.getDescripcion() %> </option>
   <%
   } %>
  
  
  </select><br>
  
    <label>Provincia:</label>
  <select class="form-control">
  
  <% 
  
  for(Provincia provincia : listaProv){    
   %>
   <option value="<%=provincia.getIdProvincia() %>"> <%=provincia.getDescripcion() %> </option>
   <%
   } %>
  
  
  </select><br>
    <label>Localidad:</label>
  <select class="form-control">
  
  <% 
  
  for(Localidad localidad : listaLocal){    
   %>
   <option value="<%=localidad.getIdLocalidad() %>"> <%=localidad.getDescripcion() %> </option>
   <%
   } %>
  
  
  </select><br>
  
  <label>Fecha de Nacimiento</label><input type="text" name="txtFechaNac" class="form-control" value="<%=usuario.getFechaNac()%>"><br>
  <label>Direccion</label><input type="text" name="txtDireccion" class="form-control" value="<%=usuario.getDireccion()%>"><br>
  <label>Mail: </label><input type="text" name="txtMail" class="form-control"  value="<%=usuario.getMail()%>"><br>
  <label>Telefono: </label><input type="text" name="txtTelefono" class="form-control" value="<%=usuario.getTelefono()%>"><br>
  <input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary">
 </div>
  </div>
  </form>
  
<div style="margin-top: 5em;">

</div>

</body>
</html>