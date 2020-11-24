<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mis datos personales</title>
</head>
<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>
	

		
		<%
		Usuario usuario = new Usuario();
		usuario=(Usuario)session.getAttribute("Usuario");
		
		%>

	<div class="container body-content" style="margin-top: 5em;">

		<h2 class="text-center">DATOS PERSONALES</h2> <br>

	<div class="form-row">
    <div class="form-group col-md-6">
      <label>DNI: </label>
      <input readonly="readonly" type="text" name="txtDni" class="form-control" value="<%=usuario.getDni()%>">
    </div>
    <div class="form-group col-md-6">
      <label>CUIL: </label>
      <input readonly="readonly" type="text" name="txtCuil" class="form-control" value="<%=usuario.getCuil()%>">
    </div>
	</div>
	
	<div class="form-row">
    <div class="form-group col-md-6">
      <label>Nombre: </label>
      <input readonly="readonly" type="text" name="txtNombre" class="form-control" value="<%=usuario.getNombre()%>">
    </div>
    <div class="form-group col-md-6">
      <label>Apellido: </label>
      <input readonly="readonly" type="text" name="txtApellido" class="form-control" value="<%=usuario.getApellido()%>">
    </div>
	</div>
	
	<div class="form-row">
    <div class="form-group col-md-6">
      <label>Sexo: </label>
      <input readonly="readonly" type="text" name="txtSexo" class="form-control" value="<%=usuario.getSexo()%>">
    </div>
    <div class="form-group col-md-6">
      <label>Nacionalidad: </label>
      <input readonly="readonly" type="text" name="txtNacionalidad" class="form-control" value="<%=usuario.getNacionalidad().getDescripcion()%>">
    </div>
	</div>
	
	<div class="form-row">
    <div class="form-group col-md-6">
      <label>Localidad:</label>
      <input readonly="readonly" type="text" name="txtLocalidad" class="form-control" value="<%=usuario.getLocalidad().getDescripcion()%>">
    </div>
    <div class="form-group col-md-6">
      <label>Provincia: </label>
      <input readonly="readonly" type="text" name="txtProvincia" class="form-control" value="<%=usuario.getProvincia().getDescripcion()%>">
    </div>
	</div>
	
	<div class="form-row">
    <div class="form-group col-md-6">
      <label>Fecha de Nacimiento:</label>
      <input readonly="readonly" type="text" name="txtFechaNac" class="form-control" value="<%=usuario.getFechaNac()%>">
    </div>
    <div class="form-group col-md-6">
      <label>Direcci�n:</label>
      <input readonly="readonly" type="text" name="txtDireccion" class="form-control" value="<%=usuario.getDireccion()%>">
    </div>
	</div>
	
	<div class="form-row">
    <div class="form-group col-md-6">
      <label>Mail: </label>
      <input readonly="readonly" type="text" name="txtMail" class="form-control" value="<%=usuario.getMail()%>">
    </div>
    <div class="form-group col-md-6">
      <label>Tel�fono: </label>
      <input readonly="readonly" type="text" name="txtTelefono" class="form-control" value="<%=usuario.getTelefono()%>">
    </div>
	</div>
		</div>
	
</body>
</html>