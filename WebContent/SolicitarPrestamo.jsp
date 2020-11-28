<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar préstamo</title>
</head>

<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>


		<%
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		if (request.getAttribute("listaCuentasPorUsuario") != null) {
			listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentasPorUsuario");
		}
		%>
		
		<%
		boolean estado=false;
		if(request.getAttribute("estadoPrestamo")!=null){
			estado=(Boolean)request.getAttribute("estadoPrestamo");
			
			
			if(estado==true){
			%>
				<script>swal("Prestamo solicitado con exito!", "", "success")</script>
			<%
			}else
			{
			%>
				<script>swal("Error al solicitar el prestamo.", "No existen prestamos para ese monto.", "error")</script>
			<%
			}
			

		}

		%>


	<form action="servletPrestamo" method="post">
	<div class="container body-content" style="margin-top: 5em;"><br />
		<div class="jumbotron jumbotron-fluid text-center rounded-pill">
			<div class="container">
				<h1 class="display-4">Solicitar préstamo</h1>
			</div>
		</div>
		<hr />
		<div class="form-group">
			<label for="exampleInputEmail1">Monto</label> 
			<input required type="number" name="txtMontoSolicitado" class="form-control small" Style="width: 500px" min="1" step="any">
		</div>
		<div class="form-group">
				<label for="exampleInputEmail1">Cantidad cuotas:</label> 
				<select name="selectCantidadCuotas" class="form-control small" Style="width: 500px">
			  	<option value="3">3 (Tasa 0.30%)</option>
			  	<option value="6">6 (Tasa 0.60%)</option>
			  	<option value="12">12 (Tasa 1.20%)</option>
			  	<option value="18">18 (Tasa 1.80%)</option>
				<option value="24">24 (Tasa 2.40%)</option>
		  		</select>
		</div>
		<%int cont=1; %>
		<div class="form-group">
				<label for="exampleInputEmail1">Cuenta destino:</label> 
				<select name="idCuentaDestino" class="form-control small" Style="width: 500px">
		  		<% 
			  		for(Cuenta cuenta: listaCuentas){    
			 		 %>
			  	<option value="<%=cuenta.getIdCuenta() %>"> <%=cont++ %> - <%=cuenta.getNumeroCuenta() %> </option>
			   	<%
			   	} %>
		  		</select>
				
		</div>
		<input class="btn btn-primary" style="margin-bottom: 20px" type="submit" name="btnSolicitar" value="Solicitar">
		<div>
			<br/>
		</div>
	</div>
	</form>
	
	
</body>
</html>