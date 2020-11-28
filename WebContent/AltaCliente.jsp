<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ALTA CLIENTE</title>


<script>
  function mostrarContrasena()
  {
      var tipo = document.getElementById("txtPass");
      var tipoAux = document.getElementById("txtPassConfirm");
      if(tipo.type == "password" || tipoAux.type == "password"){
          tipo.type = "text";
          tipoAux.type = "text";
      }else{
          tipo.type = "password";
          tipoAux.type = "password";
      }
  }
  
</script>

</head>
<body>
<jsp:include page="MenuAdmin.jsp"></jsp:include>
		<%
		List<Nacionalidad> listaNac = new ArrayList<Nacionalidad>();
		if (request.getAttribute("listaNacionalidad") != null) {
			listaNac = (List<Nacionalidad>) request.getAttribute("listaNacionalidad");
		}
		%>
		<%
		List<Provincia> listaProv = new ArrayList<Provincia>();
		if (request.getAttribute("listaProvincia") != null) {
			listaProv = (List<Provincia>) request.getAttribute("listaProvincia");
		}
		%>
		<%
		List<Localidad> listaLocal = new ArrayList<Localidad>();
		if (request.getAttribute("listaLocalidad") != null) {
			listaLocal = (List<Localidad>) request.getAttribute("listaLocalidad");
		}
		%>


		
		<%
		boolean estado=false;
		boolean estadoPass=false;
		if(request.getAttribute("estadoUsuario")!=null){
			estado=(Boolean)request.getAttribute("estadoUsuario");
			estadoPass=(Boolean)request.getAttribute("estadoPass");
			
			if(estado==true && estadoPass==true){
			%>
				<script>swal("Cliente agregado con exito!", "", "success")</script>
			<%
			} else if(estadoPass==false)
			{
			%>
				<script>swal("Error al agregar el cliente.", "La contraseña no coincide.", "error")</script>
			<% 
			}
			else
			{
			%>
				<script>swal("Error al agregar el cliente.", "El usuario y/o mail ingresado ya existe!", "error")</script>
			<%
			}
			

		}

		%>
		
		<%
		if(request.getAttribute("estadoMayorEdadException")!=null){
		%>
		<script>swal("Error al agregar el cliente.", "Debe ser mayor de 18 años para poder ser cliente.", "error")</script>
		<%
		}

		%>
		
				<%
		if(request.getAttribute("estadoAnioValidoException")!=null){
		%>
		<script>swal("Error al agregar el cliente.", "El año ingresado es invalido.", "error")</script>
		<%
		}

		%>
		
		
		
		
		
		

	<form action="servletUsuario" method="post">
		
	
		<div class="container body-content" style="margin-top: 5em;">
			<div>
				<h2>ALTA CLIENTE</h2> <br>
			</div>
							
			<label>Usuario: </label>
			<input required type="text" name="txtUsuario" class="form-control" maxlength="20" placeholder="Ej: THerrera " aria-label="Ej: THerrera" aria-describedby="basic-addon1"><br>
			<label>Contraseña: </label>
			<input required type="password" id="txtPass" name="txtPass" class="form-control" maxlength="20" placeholder="Ej: b4nc0s4r4s4 " aria-label="Ej: b4nc0s4r4s4" aria-describedby="basic-addon1"><br>
			<label>Confirmar contraseña: </label>
			<input required type="password" id="txtPassConfirm" name="txtPassConfirm" maxlength="20" class="form-control" placeholder="Ej: b4nc0s4r4s4 " aria-label="Ej: b4nc0s4r4s4" aria-describedby="basic-addon1"><br>
			<button class="btn btn-info" type="button" onclick="mostrarContrasena()">Mostrar Contraseña</button><br><br>
			<label>DNI: </label>
			<input required	 type="number" min="11111111" max="99999999" name="txtDni" maxlength="8" class="form-control" placeholder="Ej: 25358754 - ingrese el dni 8 digitos " aria-label="Ej: 25358754 - ingrese el dni sin puntos" aria-describedby="basic-addon1"><br>
			<label>CUIL:</label>
			<input required type="number" min="11111111111" max="99999999999" maxlength="11" name="txtCuil" class="form-control" placeholder="Ej: 30253587542 - ingreso el CUIL sin barra y de 11 digitos " aria-label="Ej: 30253587542 - ingreso el CUIL sin barra" aria-describedby="basic-addon1"><br>
			<label>Nombre:</label>
			<input required type="text" name="txtNombre" class="form-control" maxlength="40" placeholder="Ej: Pepito" aria-label="Ej: Pepito" aria-describedby="basic-addon1"><br>
			<label>Apellido:</label>
			<input required type="text" name="txtApellido" class="form-control" maxlength="40" placeholder="Ej: Cibrian" aria-label="Ej: Cibrian" aria-describedby="basic-addon1"><br>
	
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">Sexo: </legend>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="Masculino" checked> 
							<label class="form-check-label" for="gridRadios1"> Masculino</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="Femenino">
							 <label class="form-check-label" for="gridRadios2"> Femenino </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="Indefinido">
							 <label class="form-check-label" for="gridRadios2"> Indefinido </label>
						</div>
					</div>
				</div>
			</fieldset>
				
			<label>Nacionalidad:</label>
	  		<select name="nacionalidad" class="form-control">
	  		<% 
		  	for(Nacionalidad nacionalidad : listaNac){    
		 	 %>
		  	<option value="<%=nacionalidad.getIdNacionalidad() %>"> <%=nacionalidad.getIdNacionalidad() %> - <%=nacionalidad.getDescripcion() %> </option>
		   	<%
		   	} %>
	  		</select><br>
	  
	   		<label>Provincia:</label>
	  		<select name="provincia"  class="form-control">
	  		<% 
	  		for(Provincia provincia : listaProv){    
	   		%>
	   		<option value="<%=provincia.getIdProvincia() %>"> <%=provincia.getIdProvincia() %> - <%=provincia.getDescripcion() %> </option>
	   		<%
	   		} %>
	  		</select><br>
	  			
	   		<label>Localidad:</label>
	  		<select name="localidad"  class="form-control">
	 		<%  
	  		for(Localidad localidad : listaLocal){    
	   		%>
	   		<option value="<%=localidad.getIdLocalidad() %>"> <%=localidad.getIdLocalidad() %> - <%=localidad.getDescripcion() %> </option>
	   		<%
	   		} %>
	  		</select><br>
	
			<label>Fecha de Nacimiento</label><input required type="date" name="txtFechaNac" class="form-control" placeholder="Ej: 20/05/1890" aria-label="Ej: 20/05/1890" aria-describedby="basic-addon1"><br>
				
			<label>Dirección</label>
			<input required type="text" name="txtDireccion" class="form-control" maxlength="40" placeholder="Ej: Av. Libertador 2254" aria-label="Ej: Av. Libertador 2254" aria-describedby="basic-addon1"><br>
			<label>Mail: </label>
			<input required type="email" name="txtMail" class="form-control" maxlength="40" placeholder="Ej: pepitocibrian@gmail.com" aria-label="Ej: pepitocibrian@gmail.com" aria-describedby="basic-addon1"><br> 
			<label>Teléfono:</label>
			<input required type="number" name="txtTelefono" class="form-control" maxlength="20" placeholder="Ej: 116620458578" aria-label="Ej: 116620458578" aria-describedby="basic-addon1"><br> 
			<input class="btn btn-primary" style="margin-bottom: 20px" type="submit" name="btnAltaCliente" value="Aceptar">
		</div>
	</form>
</body>
</html>