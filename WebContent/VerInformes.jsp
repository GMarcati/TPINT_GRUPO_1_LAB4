<%@page import="entidad.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informes</title>
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
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<!-- Javascript para datatable -->
<script src="https://code.jquery.com/jquery-3.5.1.js">
	
</script>
<script
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js">
	
</script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						$('#table_id')
								.DataTable(
										{
											"language" : {
												"url" : "http://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
											}

										}

								);

					});
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		style="margin-bottom: 1em;">
	<div class="container">

		<span class="navbar-brand mb-0 h1">Banco Sarasa--> Sesión de <%=session.getAttribute("NombreUsuario")%></span>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse " id="navbarSupportedContent">

			<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<li class="nav-text dropdown"><a
					class="nav-link dropdown-toggle " href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Menu admin </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="servletUsuario?listarSelects">Alta
							cliente</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletUsuario?listadoU">Listar/Modificar/Dar
							de baja cliente</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="AltaCuenta.jsp">Alta cuenta</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletCuenta?listadoC">Listar/Modificar/Dar
							de baja cuenta</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletCuenta?AsignarCuenta">Asignar
							cuenta a cliente</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletPrestamo?listadoPrestamosxAutorizar">Autorizar
							préstamos</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="VerInformes.jsp">Ver informes</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="servletLogin?Logoff">Cerrar
							sesion</a>
					</div></li>

			</ul>


		</div>
	</div>

	</nav>

	<form action="servletInforme" method="get">

		<%
			int CantidadCuenta = 0;
			if (request.getAttribute("CantCuenta") != null) {
				CantidadCuenta = (int) request.getAttribute("CantCuenta");
			}
			double TotalSaldoCuenta = 0;
			if (request.getAttribute("TotSaldoCuenta") != null) {
				TotalSaldoCuenta = (double) request.getAttribute("TotSaldoCuenta");
			}
			int CantidadMovimientos = 0;
			if (request.getAttribute("CantMovimientos") != null) {
				CantidadMovimientos = (int) request.getAttribute("CantMovimientos");
			}
			double TotalImporteMovimiento = 0;
			if (request.getAttribute("TotImporteMovimientos") != null) {
				TotalImporteMovimiento = (double) request.getAttribute("TotImporteMovimientos");
			}
			int CantidadPrestamoSolicitado = 0;
			if (request.getAttribute("CantPrestamoSolicitado") != null) {
				CantidadPrestamoSolicitado = (int) request.getAttribute("CantPrestamoSolicitado");
			}
			double TotalImportePrestamoSolicitado = 0;
			if (request.getAttribute("TotImportePrestamoSolicitado") != null) {
				TotalImportePrestamoSolicitado = (double) request.getAttribute("TotImportePrestamoSolicitado");
			}
		%>



		<div class="container body-content" style="margin-top: 5em;">

				<div>
					<h2>INFORMES POR FECHA</h2>
					<br>
				</div>
				<label>Fecha de Inicio</label> <input required type="date"
					name="txtFechaInicio" class="form-control"
					placeholder="Ej: 20/05/1890" aria-label="Ej: 20/05/1890"
					aria-describedby="basic-addon1"><br> <label>Fecha
					de Fin</label> <input required type="date" name="txtFechaFin"
					class="form-control" placeholder="Ej: 20/05/1890"
					aria-label="Ej: 20/05/1890" aria-describedby="basic-addon1"><br>
				<input class="btn btn-primary" style="margin-bottom: 20px"
					type="submit" name="btnAceptar" value="Aceptar">
					
					
					
					
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Cantidad de cuentas creadas: </label> <input
						readonly="readonly" type="text" name="txtCantidadCuentasCreadas"
						class="form-control" value="<%=CantidadCuenta%>">
				</div>
				<div class="form-group col-md-6">
					<label>Total saldo cuentas: </label> <input readonly="readonly"
						type="text" name="txtTotalSaldo" class="form-control"
						value="$<%=TotalSaldoCuenta%>">
				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Cantidad de movimientos realizados: </label> <input
						readonly="readonly" type="text" name="txtCantidadMovimientos"
						class="form-control" value="<%=CantidadMovimientos%>">
				</div>
				<div class="form-group col-md-6">
					<label>Total importe de movimientos realizados: </label> <input
						readonly="readonly" type="text" name="txtTotalImporteMovimientos"
						class="form-control" value="$<%=TotalImporteMovimiento%>">
				</div>
			</div>


			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Cantidad de prestamos solicitados:</label> <input
						readonly="readonly" type="text"
						name="txtCantidadPrestamosSolicitados" class="form-control"
						value="<%=CantidadPrestamoSolicitado%>">
				</div>
				<div class="form-group col-md-6">
					<label>Total importe de prestamos solicitados: </label> <input
						readonly="readonly" type="text" name="txtTotalImportePrestamos"
						class="form-control" value="$<%=TotalImportePrestamoSolicitado%>">
				</div>
			</div>
		</div>


	</form>
</body>
</html>