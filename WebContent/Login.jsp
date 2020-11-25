<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<title>Login</title>

  <script>
$(document).ready(function () {
  $('#mostrar_contrasena').click(function () {
    if ($('#mostrar_contrasena').is(':checked')) {
      $('#txtContra').attr('type', 'text');
    } else {
      $('#txtContra').attr('type', 'password');
    }
  });
});
</script>

</head>

<body>

		<%
		if(request.getAttribute("estadoLoginError")!=null){
		%>
		<script>swal("El usuario/contrase�a es incorrecto.", "", "error")</script>
		<%
		}

		%>


    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin-bottom: 1em;">
    	<div class="container body-content">
  			<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
  		</div>
	</nav>

	<form action="servletLogin" method="post">
		<div class="container body-content"> <br />
            <div class="jumbotron jumbotron-fluid text-center rounded-pill">
                <div class="container">
                    <h1 class="display-4">Iniciar sesi�n</h1>
                </div>
            </div>
            <hr />
            <div class="form-group">
                <label for="exampleInputEmail1">Usuario</label>
                <input required type="text" name="txtUsuario" maxlength="20" class="form-control small" Style="width: 500px">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Contrase�a</label>
                <input required type="password" id="txtContra" name="txtContra" maxlength="20" class="form-control" Style="width: 500px" >
            <div style="margin-top:10px;">
          	<input style="margin-left:20px;" type="checkbox" id="mostrar_contrasena" title="Click para mostrar contrase�a"/>
          	&nbsp;&nbsp;Mostrar contrase�a
         	</div>
            </div>
     
            
            <input class="btn btn-primary btn-lg" type="submit" value="Ingresar" name="btnIngresar">
            
           
            
        <hr />
		<footer>
			<p>&copy; 2020 - Banco Sarasa</p>
		</footer>
		</div>
	</form>
</body>
</html>