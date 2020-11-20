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
				<script>swal("Error al asignar la cuenta al cliente!", "El cliente alcanzo el maximo de cuentas disponibles (3).", "error")</script>
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
		


<form method="get" action="servletCuenta">
	<div class="container body-content" style="margin-top: 5em;">
		<div>
			<h2>ASIGNAR CUENTA A CLIENTE</h2><br></div>
		<div>
			<label>Número cuenta:</label>
	  		<select name="txtIdCuenta" class="form-control">
	  		<% 
		  	for(Cuenta cuenta : listaC){    
		 	 %>
		  	<option value="<%=cuenta.getIdCuenta() %>"> <%=cuenta.getIdCuenta() %> - <%=cuenta.getNumeroCuenta() %> </option>
		   	<%
		   	} %>
	  		</select><br>
			<label>Usuario:</label>
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