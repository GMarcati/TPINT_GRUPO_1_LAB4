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
	<title>Abonar cuota préstamo</title>
	
	<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
	
	
<!-- DATATABLE -->
<!-- CSS para datatable -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<!-- Javascript para datatable -->
<script src="https://code.jquery.com/jquery-3.5.1.js">  </script>
<script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js">  </script>
 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

 
 <script>
 $(document).ready( function () {
 $('#table_id').DataTable({
  "language": {
                "url": "http://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
            }
 
 }
 
 );

 } );
 </script>
	
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"  style="margin-bottom: 1em;">
	        <div class="container">
	
				<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesión de <%= session.getAttribute("NombreUsuario")%></span>
	            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	            <div class="collapse navbar-collapse " id="navbarSupportedContent">
	
	                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-text dropdown">
							<a class="nav-link dropdown-toggle " href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Menu cliente</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="servletCuenta?PrincipalClienteCuentas" >Cuentas</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="servletMovimientos?listarSelects">Transferencias</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="servletCuenta?listaCuentasPrestamos" >Solicitar préstamo</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="servletPrestamo?listadoPrestamosPorUsuario" >Ver préstamos adquiridos</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="DatosPersonales.jsp" >Mis datos</a> 
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="servletLogin?Logoff" >Cerrar sesión</a> 
							</div>
						</li>					
	                </ul>
	            </div>
	        </div>
	    </nav>


		
		
		<%
		Prestamo prestamo = new Prestamo();
		if (request.getAttribute("prestamo") != null) {
			prestamo= (Prestamo) request.getAttribute("prestamo") ;
		}
		%>
		
		<%
		List<CuotasPrestamo> listaCuotas = new ArrayList<CuotasPrestamo>();
		if (request.getAttribute("listaCuotas") != null) {
			listaCuotas = (List<CuotasPrestamo>) request.getAttribute("listaCuotas");
		}
		%>
		
		<%
		long idCuenta = 0;
		if (request.getAttribute("idCuenta") != null) {
			idCuenta= (long)request.getAttribute("idCuenta") ;
		}
		%>
		
		
		



<form action="servletPrestamo" method="post">
<div class="container body-content " style="margin-top: 5em;">	
	<h2> PAGAR PRESTAMO</h2>



		<div class="form-group" >
<table id="table_id" style="width:100%" class="table table-striped table-bordered " >
<thead >
		<tr class="text-center">
			<th>CUOTA	</th>
			<th>VALOR	</th>
			<th>FECHA VENCIMIENTO</th>
			<th>ESTADO	</th>
			<th>ACCION</th>
		</tr>
		</thead>


		<tbody>
		<%
			for (CuotasPrestamo cuotasPrestamo : listaCuotas) {
		%>
		<tr>

			<td><%=cuotasPrestamo.getCuota()%><input type="hidden" name="idCuota" value="<%=cuotasPrestamo.getCuota()%>"></td>
			<td>$<%=prestamo.getValorCuota()%><input type="hidden" name="txtValorCuota" value="<%=prestamo.getValorCuota()%>" ></td>
			<td><%=cuotasPrestamo.getFechaPago()%></td>
			<td>
			<%
			if(cuotasPrestamo.getEstado()==true){
			%>	
				Impaga
			<% 
			} else{
			%>
				Pagada
			<%
			}
			
			%>
			</td>
			<td class="text-center">
			<%
			if(cuotasPrestamo.getEstado()==true){
			%>	

				<a href="servletPrestamo?idCuota=<%=cuotasPrestamo.getCuota()%>&idCuenta=<%=idCuenta %>" class="btn btn-secondary btn-sm" style="width: 80px;">Pagar</a>
				
			<% 
			} else{
			%>
			<%
			}
			
			%>
			<br><br> 
			</td>
			
		</tr>
		

		<%
			}
		%>
		</tbody>


	</table>

		</div><br>

    </div>
</div>
</form>
	
</body>
</html>