<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Modificar Cliente</title>
</head>
<body>

	<%
		Usuario usuario = new Usuario();
		if (request.getAttribute("usuarioFiltrado") != null) {
			usuario = (Usuario)request.getAttribute("usuarioFiltrado");
		}
	%>
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
		
		
		


<jsp:include page="MenuAdmin.jsp"></jsp:include>

<form method="get" action="servletUsuario">
<div class="container body-content" style="margin-top: 5em;">
<div><h2>MODIFICAR CLIENTE</h2><br></div>
<div>
 <%
 String x="";
 if(request.getAttribute("idModificar")!=null)
 {
	 
	 x=request.getAttribute("idModificar").toString();
	 
 }	 %>
 
  <label>Id Usuario: </label><input readonly="readonly" type="text" name="txtIdUsuario" class="form-control" value="<%=x%>" ><br>
  <label>Constraseña: </label><input required type="text" name="txtContrasenia" class="form-control" value="<%=usuario.getContrasenia()%>" ><br>
  <label>DNI: </label><input required type="number" min="11111111" max="99999999" name="txtDni" class="form-control" value="<%=usuario.getDni()%>"><br>
  <label>CUIL: </label><input required type="number" min="11111111111" max="99999999999" name="txtCuil" class="form-control" value="<%=usuario.getCuil()%>"><br>
  <label>Nombre: </label><input required type="text" name="txtNombre" class="form-control" value="<%=usuario.getNombre()%>"><br>
  <label>Apellido: </label><input required type="text" name="txtApellido" class="form-control" value="<%=usuario.getApellido()%>"><br>
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
  
  <label>Fecha de Nacimiento</label><input required type="date" name="txtFechaNac" class="form-control" value="<%=usuario.getFechaNac()%>"><br>
  <label>Direccion</label><input required type="text" name="txtDireccion" class="form-control" value="<%=usuario.getDireccion()%>"><br>
  <label>Mail: </label><input required type="email" name="txtMail" class="form-control"  value="<%=usuario.getMail()%>"><br>
  <label>Telefono: </label><input required type="number" name="txtTelefono" class="form-control" value="<%=usuario.getTelefono()%>"><br>
  <input type="submit" name="btnModificar" value="Aceptar" class="btn btn-primary">
 </div>
  </div>
  </form>
  
<div style="margin-top: 5em;">

</div>

</body>
</html>