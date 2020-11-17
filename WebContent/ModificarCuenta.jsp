<%@page import="entidad.Usuario"%>
<%@page import="entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar cuenta</title>

</head>
<body>

	<%
		Cuenta cuenta = new Cuenta();
		if (request.getAttribute("cuentaFiltrada") != null) {
			cuenta = (Cuenta)request.getAttribute("cuentaFiltrada");
		}
	%>
	
	
	
	

<jsp:include page="MenuAdmin.jsp"></jsp:include>


<div class="container body-content" style="margin-top: 1em;">
<div><h2>MODIFICAR CUENTA</h2><br></div>
<div>
 <form method="get" action="servletCuenta">
    
  <label>Id cuenta: </label> <input readonly="readonly" type="text" name="txtIdCuenta" class="form-control" value="<%=request.getAttribute("idModificar")%>"><br>
  <label>Numero de cuenta: </label><input required type="number" name="txtNumeroCuenta" class="form-control" value="<%=cuenta.getNumeroCuenta()%>"><br>
  <label>Saldo: </label><input required type="number" name="txtSaldo" class="form-control" value="<%=cuenta.getSaldo()%>" min="1" step="any"><br>
		  	<label>Tipo de cuenta</label> 
				<select required class="form-control" name="tipoCuenta" >  
					<option value="1" >1 - Caja de ahorro </option>  
					<option value="2">2 - Cuenta corriente </option> 
</select> <br>
  <label>CBU: </label><input required type="number" name="txtCBU" class="form-control" value="<%=cuenta.getCBU()%>"><br>
<input class="btn btn-primary" type="submit" name="btnModificar" value="Aceptar">
</form>
 </div>
  </div>
</body>
</html>