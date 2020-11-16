<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ALTA CLIENTE</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>




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

		<%
		if(request.getAttribute("estadoUsuario")!=null){
		%>
		<script>swal("Usuario agregado con exito!", "", "success")</script>
		<%
		}

		%>

	<form action="servletUsuario" method="get">
	
	

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  style="margin-bottom: 1em;">
        <div class="container">

			<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesión de <%= Usuario.getNombreUsu()%></span>
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
						<a class="dropdown-item" href="servletCuenta?listadoC" >Listar/Modificar/Dar de baja cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AsignarCuentaACliente.jsp">Asignar cuenta a cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="ListaPrestamos.jsp">Autorizar préstamos</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="VerInformes.jsp" >Ver informes</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="Logoff.jsp" >Cerrar sesión</a> 
					</div>
				</li>
						 					
                </ul>


            </div>
        </div>

    </nav>

		<div class="container body-content" style="margin-top: 5em;">
			<div>
				<h2>ALTA CLIENTE</h2> <br>
			</div>
			
			<label>Usuario: </label>
			<input required type="text" name="txtUsuario" class="form-control" placeholder="Ej: THerrera " aria-label="Ej: THerrera" aria-describedby="basic-addon1"><br>
			<label>Constraseña: </label>
			<input required type="text" name="txtPass" class="form-control" placeholder="Ej: b4nc0s4r4s4 " aria-label="Ej: b4nc0s4r4s4" aria-describedby="basic-addon1"><br>
			<label>DNI: </label>
			<input required	 type="number" name="txtDni" class="form-control" placeholder="Ej: 25358754 - ingrese el dni sin puntos " aria-label="Ej: 25358754 - ingrese el dni sin puntos" aria-describedby="basic-addon1"><br>
			<label>CUIL:</label>
			<input required type="number" name="txtCuil" class="form-control" placeholder="Ej: 30253587542 - ingreso el CUIL sin barra " aria-label="Ej: 30253587542 - ingreso el CUIL sin barra" aria-describedby="basic-addon1"><br>
			<label>Nombre:</label>
			<input required type="text" name="txtNombre" class="form-control" placeholder="Ej: Pepito" aria-label="Ej: Pepito" aria-describedby="basic-addon1"><br>
			<label>Apellido:</label>
			<input required type="text" name="txtApellido" class="form-control" placeholder="Ej: Cibrian" aria-label="Ej: Cibrian" aria-describedby="basic-addon1"><br>

			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">Sexo: </legend>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="Masculino" checked> 
							<label class="form-check-label" for="gridRadios1"> Masculino</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="Femenino">
							 <label class="form-check-label" for="gridRadios2"> Femenino </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="Indefinido">
							 <label class="form-check-label" for="gridRadios2"> Indefinido </label>
						</div>
					</div>
				</div>
			</fieldset>
			
			  <label>Nacionalidad:</label>
  <select name="nacionalidad" class="form-control">
  
  <% 
  
  for(Nacionalidad nacionalidad : listaNac){    
   %>
   <option value="<%=nacionalidad.getIdNacionalidad() %>"> <%=nacionalidad.getDescripcion() %> </option>
   <%
   } %>
  
  
  </select><br>
  
    <label>Provincia:</label>
  <select name="provincia"  class="form-control">
  
  <% 
  
  for(Provincia provincia : listaProv){    
   %>
   <option value="<%=provincia.getIdProvincia() %>"> <%=provincia.getDescripcion() %> </option>
   <%
   } %>
  
  
  </select><br>
    <label>Localidad:</label>
  <select name="localidad"  class="form-control">
  
  <% 
  
  for(Localidad localidad : listaLocal){    
   %>
   <option value="<%=localidad.getIdLocalidad() %>"> <%=localidad.getDescripcion() %> </option>
   <%
   } %>
  
  
  </select><br>

			<label>Fecha de Nacimiento</label><input required type="date" name="txtFechaNac" class="form-control" placeholder="Ej: 20/05/1890" aria-label="Ej: 20/05/1890" aria-describedby="basic-addon1"><br>
			
			<label>Dirección</label>
			<input required type="text" name="txtDireccion" class="form-control" placeholder="Ej: Av. Libertador 2254" aria-label="Ej: Av. Libertador 2254" aria-describedby="basic-addon1"><br>
			<label>Mail: </label>
			<input required type="email" name="txtMail" class="form-control" placeholder="Ej: pepitocibrian@gmail.com" aria-label="Ej: pepitocibrian@gmail.com" aria-describedby="basic-addon1"><br> 
			<label>Teléfono:</label>
			<input required type="number" name="txtTelefono" class="form-control" placeholder="Ej: 116620458578" aria-label="Ej: 116620458578" aria-describedby="basic-addon1"><br> 
			<input class="btn btn-primary" style="margin-bottom: 20px" type="submit" name="btnAceptar" value="Aceptar">
		</div>
	</form>
</body>
</html>