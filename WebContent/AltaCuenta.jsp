
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Alta Cuenta</title>
</head>
<body>
<jsp:include page="MenuAdmin.jsp"></jsp:include>

		<%
		boolean estado=false;
		if(request.getAttribute("estadoCuenta")!=null){
			estado=(Boolean)request.getAttribute("estadoCuenta");
			
			
			if(estado==true){
			%>
				<script>swal("Cuenta agregada con exito!", "", "success")</script>
			<%
			}else
			{
			%>
				<script>swal("Error al generar la cuenta.", "", "error")</script>
			<%
			}
			

		}

		%>
		


<div class="container body-content" style="margin-top: 5em;">
	<div>
		<h2>ALTA CUENTA</h2><br>
	</div>

	<form action="servletCuenta" method="get">
		<div>

		  	<label>Numero de cuenta: </label><input type="text" readonly="readonly" name="txtNumeroCuenta" class="form-control" placeholder="Se auto-generara un numero de cuenta"><br>
		  	<label>Saldo inicial: </label><input readonly="readonly" value="10000" type="text" name="txtMontoInicial" class="form-control" ><br>
		  	<label>Tipo de cuenta</label> 
				<select class="form-control" name="tipoCuenta">  
					<option value="1">1 - Caja de ahorro </option>  
					<option value="2">2 - Cuenta corriente </option> 
				</select> <br>
		  	<label>CBU: </label><input type="text" readonly="readonly" name="txtCBU" class="form-control" placeholder="Se auto-generara un numero de CBU"><br>
			<input class="btn btn-primary" type="submit" name="btnAceptar" value="Aceptar">
	 	</div>
 	</form>
 	

	
	
	
  </div>
</body>
</html>