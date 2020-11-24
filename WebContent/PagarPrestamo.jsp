<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Abonar cuota préstamo</title>
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
		long cuotas = 0;
		if (request.getAttribute("cuotasApagar") != null) {
			cuotas = (long) request.getAttribute("cuotasApagar");
		}
		%>
		
		<%
		Prestamo prestamo = new Prestamo();
		if (request.getAttribute("prestamo") != null) {
			prestamo= (Prestamo) request.getAttribute("prestamo") ;
		}
		%>

<form action="servletPrestamo" method="get">
<div class="container body-content " style="margin-top: 5em;">	
	<h2> PAGAR PRESTAMO</h2>
	<div class="m-3">
		<div class=form-group">
		<label for="exampleInputEmail1">Id prestamo:</label>
		<input readonly="readonly" type="text" name="txtIdPrestamo" class="form-control small" Style="width: 500px" value="<%=prestamo.getIdPrestamo()%>" ><br>
		
		</div>
		<div class=form-group">
		<label for="exampleInputEmail1">Cuota:</label>
		<input readonly="readonly" type="text" name="txtCuota" class="form-control small" Style="width: 500px" value="<%=prestamo.getValorCuota()%>" ><br>
		
		</div>
		<div class="form-group" >
		<label for="exampleInputEmail1">Pagar desde la cuenta:</label>
		<select name="CuentaUsuario" class="form-control small" Style="width: 500px">
		<% 
			for(Cuenta cuentause: listaCuentaXUsuario){    
		 %>
		<option value="<%=cuentause.getIdCuenta() %>"> <%=cuentause.getIdCuenta() %> - <%=cuentause.getNumeroCuenta() %> </option>
		<%
		} %>
		</select>
		</div>
		<div class="form-group" >
		<label for="exampleInputEmail1">Cuota a abonar:</label>
		<select name="CuentaUsuario" class="form-control small" Style="width: 500px">
		<% 
		
			for(int i=1;i<=cuotas;i++){    
		 %>
		<option value="<%=i %>"> <%=i %> </option>
		<%
		} %>
		</select>
		
		</div><br>

        <input class="btn btn-primary" type="submit" name="btnPagarCuota" value="Pagar">
    </div>
</div>
</form>
	
</body>
</html>