<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.TipoMovimiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nueva transferencia</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>
		<%
		List<Cuenta> listaCuentaXUsuario = new ArrayList<Cuenta>();
		if (request.getAttribute("listaCuentasPorUsuario") != null) {
			listaCuentaXUsuario = (List<Cuenta>) request.getAttribute("listaCuentasPorUsuario");
		}
		%>
		<%
		List<TipoMovimiento> listaTipoMoviento = new ArrayList<TipoMovimiento>();
		if (request.getAttribute("listaTipoMovimiento") != null) {
			listaTipoMoviento = (List<TipoMovimiento>) request.getAttribute("listaTipoMovimiento");
		}
		%>
		<%
		List<Cuenta> listaNroCuenta = new ArrayList<Cuenta>();
		if (request.getAttribute("listaNumeroCuentas") != null) {
			listaNroCuenta = (List<Cuenta>) request.getAttribute("listaNumeroCuentas");
		}
		%>

		
		<%
		boolean estado=false;
		if(request.getAttribute("estadoMovimiento")!=null){
			estado=(Boolean)request.getAttribute("estadoMovimiento");
			
			
			if(estado==true){
			%>
				<script>swal("Transferencia realizado con exito!", "", "success")</script>
			<%
			}else
			{
			%>
				<script>swal("Error al realizar la Transferencia .", " XXXXXXXXXXXX ", "error")</script>
			<%
			}
			

		}

		%>

		

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 1em;">
		<div class="container body-content">
	  		<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesion de <%= Usuario.getNombreUsu()%></span>
	  		<span class="navbar-text">
	   	   	<a class="nav-item" href="PrincipalCliente.jsp">Menu cliente</a>
	    	</span>
  		</div>
	</nav>


	<form action="servletMovimientos" method="get">
	
		<div class="container body-content"><br />
			<div class="jumbotron jumbotron-fluid text-center rounded-pill">
				<div class="container">
					<h1 class="display-4">Transferir</h1>
				</div>
			</div>
			<hr />
			<div class="form-group">
				<label for="exampleInputEmail1">Transfiriendo desde:</label>
				<select name="CuentaUsuario" class="form-control">
		  		<% 
			  		for(Cuenta cuentause: listaCuentaXUsuario){    
			 		 %>
			  	<option value="<%=cuentause.getIdCuenta() %>"> <%=cuentause.getIdCuenta() %> - <%=cuentause.getNumeroCuenta() %> </option>
			   	<%
			   	} %>
		  		</select><br>
		  		
			</div>
				<div class="form-group">
				<label for="exampleInputEmail1">Tipo de Movimiento:</label> 
				<select name="TipoMovimiento" class="form-control">
		  		<% 
			  		for(TipoMovimiento tmovimiento: listaTipoMoviento){    
			 		 %>
			  	<option value="<%=tmovimiento.getIdTipoMovimiento() %>"> <%=tmovimiento.getIdTipoMovimiento() %> - <%=tmovimiento.getDescripcion() %> </option>
			   	<%
			   	} %>
		  		</select><br>
			
			
			<div class="form-group">
				<label for="exampleInputEmail1">CUENTA destino:</label> 
				<select name="NumeroCuentaDestino" class="form-control">
		  		<% 
			  		for(Cuenta cuenta: listaNroCuenta){    
			 		 %>
			  	<option value="<%=cuenta.getIdCuenta() %>"> <%=cuenta.getIdCuenta() %> - <%=cuenta.getNumeroCuenta() %> </option>
			   	<%
			   	} %>
		  		</select><br>
				
			</div>			
			<div class="form-group">
				<label for="exampleInputEmail1">Detalle:</label> 
				<input type="text" name="txtDetalle" class="form-control small" Style="width: 500px">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Monto:</label> 
				<input type="text" name="txtMonto" class="form-control small" Style="width: 500px">
			</div>
			
			<input class="btn btn-primary" style="margin-bottom: 20px" type="submit" name="btnAceptar" value="Aceptar">
		</div>
	</form>
</body>
</html>