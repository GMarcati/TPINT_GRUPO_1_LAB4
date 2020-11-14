<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTAR CUENTAS</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
	<%
		List<Cuenta> listaC =  new ArrayList<Cuenta>();
		if (request.getAttribute("listaCuenta") != null) {
			System.out.print("pasa");
			listaC = (ArrayList<Cuenta>) request.getAttribute("listaCuenta");
		}
	%>


	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  style="margin-bottom: 1em;">
        <div class="container">

			<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">

                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<li class="nav-text dropdown">
				<a class="nav-link dropdown-toggle " href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Menu admin
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="AltaCliente.jsp" >Alta cliente</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletUsuario?listadoU">Listar/Modificar/Dar de baja cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AltaCuenta.jsp" >Alta cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="ListaEliminarCuentas.jsp" >Listar/Modificar/Dar de baja cuenta</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AsignarCuentaACliente.jsp">Asignar cuenta a cliente</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="ListaPrestamos.jsp">Autorizar préstamos</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="VerInformes.jsp" >Ver informes</a> 
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="Logoff.jsp" >Cerrar sesion</a> 
					</div>
				</li>
						 					
                </ul>


            </div>
        </div>

    </nav>
		
	

<form action="servletCuenta" method="get">

<div class="container-fluid" style="margin-top: 5em;">
	<div><h2>LISTADO DE CUENTAS</h2><!-- Ingresar USUARIO SESION --><br></div>
	<label>Busqueda por TODAS LAS CUENTAS: </label><input type="submit" name="btnTodasCuentas" value="Aceptar"><br>

	<label>Busqueda por nro de cuenta: </label> 	
		<div class="input-group mb-3">
  			<div class="input-group-prepend">
 	</div>
	<input type="text" name="txtNroCuenta" class="form-control" placeholder="Ej: 213213242" aria-label="Username" aria-describedby="basic-addon1">
	<input type="submit" name="btnPorCuenta" value="Aceptar">
</div><br>

<label>Busqueda por tipo de cuenta: </label> 	
<div class="input-group mb-3">
  <div class="input-group-prepend">
  </div>
	<input type="text" name="txtTipoCuenta" class="form-control" placeholder="Ej: Cuenta Corriente" aria-label="Username" aria-describedby="basic-addon1">
	<input type="submit" name="btnTipoCuenta" value="Aceptar">
</div><br>


<label>Busqueda por CBU: </label> 	
<div class="input-group mb-3">
  <div class="input-group-prepend">
  </div>
	<input type="text" name="txtCBU" class="form-control" placeholder="Ej: 20252525257" aria-label="Username" aria-describedby="basic-addon1">
	<input type="submit" name="btnPorCBU" value="Aceptar">
</div><br>
</form>
 
	
<table class="table">
  <thead>
    <tr>
      <th scope="col">IdCuenta</th>
      <th scope="col">NroCuenta</th>
      <th scope="col">TipoCuenta</th>
      <th scope="col">FechaCreacion</th>
      <th scope="col">CBU</th>
      <th scope="col">Saldo</th>
      <th scope="col">Estado</th>
      <th scope="col">Accion</th>
      <th scope="col"></th>
    </tr>
  </thead>
  
  		<%	
			for(Cuenta c : listaC) {
		%>
  
  <tbody>
    <tr>
     		 <td><%=c.getIdCuenta()%></td>
     		 <td><%=c.getNumeroCuenta()%></td>
     		 <td><%=c.getTipoCuenta().getDescripcion()%></td>
     		 <td><%=c.getFechaCreacion()%></td>
     		 <td><%=c.getCBU()%></td>
     		 <td><%=c.getSaldo()%></td>
     		 <td><%=c.getEstado().getDescripcion()%></td>
     		 
     		 <td><a href="ModificarCuenta.jsp?id" class="btn btn-primary" >Modificar</a> <br></td>
     		 <td><a href="ListaEliminarCuentas.jsp?id" class="btn btn-danger" >Eliminar</a> <br></td>

    </tr>
    	<%
			}
		%>
    
  </tbody>
</table>
</div>


</body>
</html>