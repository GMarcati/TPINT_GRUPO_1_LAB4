<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.CuotasPrestamo"%>
<%@page import="entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleccionar cuenta</title>
</head>
<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>



		<%
		List<Cuenta> listaCuentaXUsuario = new ArrayList<Cuenta>();
		if (request.getAttribute("listaCuentasPorUsuario") != null) {
			listaCuentaXUsuario = (List<Cuenta>) request.getAttribute("listaCuentasPorUsuario");
		}
		%>
		<div class="container body-content " style="margin-top: 5em;">
		<h2> SELECCIONAR CUENTA ORIGEN:</h2>
		<form action="servletPrestamo" method="get">
		<div class="form-group" >
		<label for="exampleInputEmail1">Pagar desde la cuenta:</label>
		<select name="CuentaUsuario" id="CuentaUsuario" class="form-control small" Style="width: 500px">
		<% 
			for(Cuenta cuentause: listaCuentaXUsuario){    
		 %>
		<option value="<%=cuentause.getIdCuenta() %>"> <%=cuentause.getIdCuenta() %> - <%=cuentause.getNumeroCuenta() %> </option>
		<%
		} %>
		</select>
		</div>
		<input class="btn btn-primary" type="submit" name="btnSeleccionarCuenta" value="Confirmar cuenta origen">
		</form>
		</div>

</body>
</html>