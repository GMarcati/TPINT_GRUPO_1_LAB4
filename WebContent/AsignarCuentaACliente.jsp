<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Asignar cuenta a cliente</title>
</head>
<body>
<jsp:include page="MenuAdmin.jsp"></jsp:include>

	<%
	if (request.getAttribute("estadoAsignarCuenta") != null) 
	{%>

		<script>swal("Cuenta asignada a cliente con exito!", "", "success")</script>
			
	<% }
	%>
	
			<%
		boolean estado=false;
		if(request.getAttribute("estadoAsignarCuenta")!=null){
			estado=(Boolean)request.getAttribute("estadoAsignarCuenta");
			
			
			if(estado==true){
			%>
				<script>swal("Cuenta asignada a cliente con exito!", "", "success")</script>
			<%
			}else
			{
			%>
				<script>swal("Error al asignar la cuenta al cliente.", "", "error")</script>
			<%
			}
			

		}

		%>
	
	
			<%
		List<Usuario> listaU = new ArrayList<Usuario>();
		if (request.getAttribute("listaUsuario") != null) {
			listaU = (List<Usuario>) request.getAttribute("listaUsuario");
		}
		%>
		
		<%
		List<Cuenta> listaC = new ArrayList<Cuenta>();
		if (request.getAttribute("listaCuenta") != null) {
			listaC = (List<Cuenta>) request.getAttribute("listaCuenta");
		}
		%>
		

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
						<a class="dropdown-item" href="servletUsuario?listarSelects" >Alta cliente</a>
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
						<a class="dropdown-item" href="Logoff.jsp" >Cerrar sesion</a> 
					</div>
				</li>
						 					
                </ul>
            </div>
        </div>
    </nav>

<form method="get" action="servletCuenta">
	<div class="container body-content" style="margin-top: 5em;">
		<div>
			<h2>ASIGNAR CUENTA A CLIENTE</h2><br></div>
		<div>
			<label>Id Cuenta:</label>
	  		<select name="txtIdCuenta" class="form-control">
	  		<% 
		  	for(Cuenta cuenta : listaC){    
		 	 %>
		  	<option value="<%=cuenta.getIdCuenta() %>"> <%=cuenta.getIdCuenta() %> - <%=cuenta.getNumeroCuenta() %> </option>
		   	<%
		   	} %>
	  		</select><br>
			<label>Id Cliente:</label>
	  		<select name="txtIdCliente" class="form-control">
	  		<% 
		  	for(Usuario usuario : listaU){    
		 	 %>
		  	<option value="<%=usuario.getIdUsuario() %>"> <%=usuario.getIdUsuario() %> - <%=usuario.getUsuario() %> </option>
		   	<%
		   	} %>
	  		</select><br>
			
			
			<input class="btn btn-primary" type="submit" name="btnAsignarCuenta" value="Aceptar">
	 	</div>
	</div>
</form>

</body>
</html>