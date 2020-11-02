<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar cuenta</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container body-content">
		<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
        
    	<span class="navbar-text">
   	   	<a class="nav-item" href="PrincipalAdministrador.jsp">Menu admin</a>
    	</span>
  		</div>
  		
  
</nav>


<div class="container body-content" style="margin-top: 1em;">
<div><h2>MODIFICAR CUENTA</h2><br></div>
<div>
 
    
  
  <label>Numero de cuenta: </label><input type="text" name="txtNumeroCuenta" class="form-control" placeholder="Ej: 452535875436 - ingrese el numero de cuenta sin puntos " aria-label="Ej: 253587549014 - ingrese el numero de cuenta sin puntos" aria-describedby="basic-addon1"><br>
  <label>Saldo: </label><input type="text" name="txtMontoInicial" class="form-control" placeholder="Monto inicial $ 10000 " aria-label="Monto inicial $ 10000" aria-describedby="basic-addon1"><br>
  <label>Tipo de cuenta: </label><input type="text" name="txtTipoDeCuenta" class="form-control" placeholder="Caja de ahorro - Cuenta corriente" aria-label="Caja de ahorro - Cuenta corriente" aria-describedby="basic-addon1"><br>
  <label>CBU: </label><input type="text" name="txtCBU" class="form-control" placeholder="Ej: 00007562415899635412510 - 22 digitos" aria-label="Ej: 00007562415899635412510 - 22 digitos" aria-describedby="basic-addon1"><br>
<input type="submit" name="btnAceptar" value="Aceptar">
 </div>
  </div>
</body>
</html>