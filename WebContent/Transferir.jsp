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
</head>
<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>
		<%
		List<Cuenta> listaCuentaXUsuario = new ArrayList<Cuenta>();
		if (request.getAttribute("listaCuentasPorUsuario") != null) {
			listaCuentaXUsuario = (List<Cuenta>) request.getAttribute("listaCuentasPorUsuario");
		}
		%>
		
		<%
		boolean estado=false;
		if(request.getAttribute("estadoTransferencia")!=null){
			estado=(Boolean)request.getAttribute("estadoTransferencia");
			
			
			if(estado==true){
			%>
				<script>swal("Transferencia realizado con exito!", "", "success")</script>
			<%
			}else
			{
			%>
				<script>swal("Error al realizar la transferencia.", "Saldo insuficiente o cbu inexistente.", "error")</script>
			<%
			}
			

		}

		%>

		



	<form action="servletMovimientos" method="post">
	
		<div class="container body-content" style="margin-top: 5em;"><br />
			<div class="jumbotron jumbotron-fluid text-center rounded-pill">
				<div class="container">
					<h1 class="display-4">Transferir</h1>
				</div>
			</div>
			<hr />
			<%int cont=1; %>
			<div class="form-group">
				<label for="exampleInputEmail1">Transfiriendo desde:</label>
				<select name="CuentaUsuario" class="form-control small" Style="width: 500px">
		  		<% 
			  		for(Cuenta cuentause: listaCuentaXUsuario){    
			 		 %>
			  	<option value="<%=cuentause.getIdCuenta() %>"> <%=cont++ %> - <%=cuentause.getNumeroCuenta() %> </option>
			   	<%
			   	} %>
		  		</select>
		  		
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">CBU destino:</label> 
				<input required type="number" name="txtCBU" class="form-control small" Style="width: 500px">
				
			</div>			
			<div class="form-group">
				<label for="exampleInputEmail1">Detalle:</label> 
				<input required type="text" name="txtDetalle" class="form-control small" Style="width: 500px">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Monto:</label> 
				<input required type="number" name="txtMonto" class="form-control small" Style="width: 500px" min="1" step="any">
			</div>
			
			<input class="btn btn-primary" style="margin-bottom: 20px" type="submit" name="btnTransferir" value="Aceptar">
		</div>
	</form>
</body>
</html>