<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALTA CLIENTE</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>

<!-- Aca va el form direccionando al servlet -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container body-content">
		<span class="navbar-brand mb-0 h1">Banco Sarasa</span>
        
    	<span class="navbar-text">
   	   	<a class="nav-item" href="PrincipalAdministrador.jsp">Menu admin</a>
    	</span>
  		</div>
  		
  
</nav>


<div class="container body-content" style="margin-top: 1em;">
<div><h2>ALTA CLIENTE</h2><br></div>
<div>
 
    
  <label>Usuario: </label><input type="text" name="txtUsuario" class="form-control" placeholder="Ej: THerrera " aria-label="Ej: THerrera" aria-describedby="basic-addon1"><br>
  <label>Constrase�a: </label><input type="text" name="txtPass" class="form-control" placeholder="Ej: b4nc0s4r4s4 " aria-label="Ej: b4nc0s4r4s4" aria-describedby="basic-addon1"><br>
  <label>DNI: </label><input type="text" name="txtDni" class="form-control" placeholder="Ej: 25358754 - ingrese el dni sin puntos " aria-label="Ej: 25358754 - ingrese el dni sin puntos" aria-describedby="basic-addon1"><br>
  <label>CUIL: </label><input type="text" name="txtCuil" class="form-control" placeholder="Ej: 30253587542 - ingreso el CUIL sin barra " aria-label="Ej: 30253587542 - ingreso el CUIL sin barra" aria-describedby="basic-addon1"><br>
  <label>Nombre: </label><input type="text" name="txtNombre" class="form-control" placeholder="Ej: Pepito" aria-label="Ej: Pepito" aria-describedby="basic-addon1"><br>
  <label>Apellido: </label><input type="text" name="txtApellido" class="form-control" placeholder="Ej: Cibrian" aria-label="Ej: Cibrian" aria-describedby="basic-addon1"><br>
  <fieldset class="form-group">
    <div class="row">
      <legend class="col-form-label col-sm-2 pt-0">Sexo: </legend>
      <div class="col-sm-10">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="option1" checked>
          <label class="form-check-label" for="gridRadios1">
            Masculino
          </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="gridRadios" id="RdSexo" value="option2">
          <label class="form-check-label" for="gridRadios2">
            Femenino
          </label>
        </div>
      </div>
    </div>
  </fieldset>
   <label class="my-1 mr-2" for="inlineFormCustomSelectPref">Nacionalidad: 	</label>
  <select class="custom-select my-1 mr-sm-2" id="cbxNacionalidad">
    <option selected>Eliga el Pais...</option>
    <option value="1">Argentina</option>
    <option value="2">Brasil</option>
    <option value="3">Bolivia</option>
    <option value="4">Chile</option>
    <option value="5">Paraguay</option>
    <option value="6">Uruguay</option>
    <option value="7">Colombia</option>
    <option value="8">Ecuador</option>
    <option value="9">Mexico</option>
    <option value="10">EEUU</option>
    <option value="11">Canada</option>
  </select>
        
  <label>Fecha de Nacimiento</label><input type="text" name="txtFechaNac" class="form-control" placeholder="Ej: 20/05/1890" aria-label="Ej: 20/05/1890" aria-describedby="basic-addon1"><br>
  <label>Direccion</label><input type="text" name="txtDireccion" class="form-control" placeholder="Ej: Av. Libertador 2254" aria-label="Ej: Av. Libertador 2254" aria-describedby="basic-addon1"><br>
  <label>Localidad</label><input type="text" name="txtLocalidad" class="form-control" placeholder="Ej: General Pacheco" aria-label="Ej: General Pacheco" aria-describedby="basic-addon1"><br>
  <label>Provincia: </label><input type="text" name="txtProvincia" class="form-control" placeholder="Ej: Buenos Aires" aria-label="Ej: Buenos Aires" aria-describedby="basic-addon1"><br>
  <label>Mail: </label><input type="text" name="txtMail" class="form-control" placeholder="Ej: pepitocibrian@gmail.com" aria-label="Ej: pepitocibrian@gmail.com" aria-describedby="basic-addon1"><br>
  <label>Telefono: </label><input type="text" name="txtTelefono" class="form-control" placeholder="Ej: 116620458578" aria-label="Ej: 116620458578" aria-describedby="basic-addon1"><br>
  <input type="submit" name="btnAceptar" value="Aceptar">
 </div>
  </div>
	
</body>
</html>