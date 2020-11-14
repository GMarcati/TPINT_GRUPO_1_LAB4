<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Mis datos personales</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 1em;">
    	<div class="container body-content">
  			<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesión de <%= Usuario.getNombreUsu()%></span>
    		<span class="navbar-text">
   	   		<a class="nav-item" href="PrincipalCliente.jsp">Menú cliente</a>
    		</span>
  		</div>
	</nav>
	
	<div class="container body-content" style="margin-top: 1em;">
		<div class="margin m-3">
	  		<label>DNI: </label><input type="text" name="txtDni" class="form-control" placeholder="25358754" aria-label="25358754 " aria-describedby="basic-addon1"><br>
	  		<label>CUIL: </label><input type="text" name="txtCuil" class="form-control" placeholder="30253587542" aria-label="" aria-describedby="basic-addon1"><br>
	 		<label>Nombre: </label><input type="text" name="txtNombre" class="form-control" placeholder="Pepito" aria-label="Pepito" aria-describedby="basic-addon1"><br>
	  		<label>Apellido: </label><input type="text" name="txtApellido" class="form-control" placeholder="Cibrian" aria-label="Cibrian" aria-describedby="basic-addon1"><br>
	  		<label>Sexo: </label><input type="text" name="txtSexo" class="form-control" placeholder="Masculino" aria-label="Masculino" aria-describedby="basic-addon1"><br>
	  		<label>Nacionalidad: </label><input type="text" name="txtNacionalidad" class="form-control" placeholder="Argentina" aria-label="Argentina" aria-describedby="basic-addon1"><br>
	
		        
			<label>Fecha de Nacimiento:</label><input type="text" name="txtFechaNac" class="form-control" placeholder="20/05/1890" aria-label="20/05/1890" aria-describedby="basic-addon1"><br>
			<label>Dirección:</label><input type="text" name="txtDireccion" class="form-control" placeholder="Av. Libertador 2254" aria-label="Av. Libertador 2254" aria-describedby="basic-addon1"><br>
			<label>Localidad:</label><input type="text" name="txtLocalidad" class="form-control" placeholder="General Pacheco" aria-label="General Pacheco" aria-describedby="basic-addon1"><br>
			<label>Provincia: </label><input type="text" name="txtProvincia" class="form-control" placeholder="Buenos Aires" aria-label="Buenos Aires" aria-describedby="basic-addon1"><br>
			<label>Mail: </label><input type="text" name="txtMail" class="form-control" placeholder="pepitocibrian@gmail.com" aria-label="pepitocibrian@gmail.com" aria-describedby="basic-addon1"><br>
			<label>Teléfono: </label><input type="text" name="txtTelefono" class="form-control" placeholder="116620458578" aria-label="116620458578" aria-describedby="basic-addon1"><br>
			<a href="PrincipalCliente.jsp" class="btn btn-primary">Volver al menú</a>
		</div>
	</div>
</body>
</html>