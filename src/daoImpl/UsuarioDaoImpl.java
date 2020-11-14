package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;


public class UsuarioDaoImpl implements UsuarioDao {

	private Conexion cn;
	
	public UsuarioDaoImpl()
	{
		
	}
		
	//METODO QUE VERIFICA QUE EL USUARIO Y LA CONTRASEÑA INGRESADOS, ESTEN EN LA DB --> 100%
	public boolean verificarLogin(String usuario, String contra)
	{
		cn = new Conexion();
		cn.Open();
		
		boolean resultado= false; //va a almacenar true, si hubo coincidencia de usuario y contraseña
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			ResultSet rs= cn.query("select * from usuarios where usuario='"+usuario+"' and contrasenia='"+contra+"'");
			if(rs.next())
			{
				resultado=true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			resultado=false;
		}
		finally
		{
			cn.close();
		}
		return resultado;
	}
	
	//METODO QUE VERIFICA QUE TIPO DE USUARIO ES EL QUE INGRESO AL LOGIN-->
	public boolean verificarTipoUsuario(String usuario)
	{
		cn = new Conexion();
		cn.Open();
		
		boolean resultado= false; //va a almacenar true, si es administrador y false si es cliente
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			ResultSet rs= cn.query("select idTipoUsuario from usuarios where usuario= '"+usuario+"'");
			if(rs.next())
			{
				int tipo= rs.getInt(1);
				if(tipo==1)
				{
					resultado=true;
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			resultado=false;
		}
		finally
		{
			return resultado;
		}
	}
	
	public boolean modificar(Usuario usuario) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "Update usuarios set contrasenia='"+usuario.getContrasenia() +"',idTipoUsuario='"+usuario.getTipoUsuario().getIdTipoUsuario()+"',DNI='"+usuario.getDni()+"',CUIL='"+usuario.getCuil()+"',nombre='"+usuario.getNombre()+"',apellido='"+usuario.getApellido()+"',sexo='"+usuario.getSexo()+"',fechaNacimiento='"+usuario.getFechaNac()+"',direccion='"+usuario.getDireccion()+"',idLocalidad='"+usuario.getLocalidad().getIdLocalidad()+"',idNacionalidad='"+usuario.getNacionalidad().getIdNacionalidad()+"',idProvincia='"+usuario.getProvincia().getIdProvincia()+"',mail='"+usuario.getMail()+"',telefono='"+usuario.getTelefono()+"' where idUsuario='"+usuario.getIdUsuario()+"' and idEstado = 1";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado; 
	}
	
	public boolean eliminar (int id) {
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "Update usuarios set idEstado=0 WHERE idUsuario="+id;
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
	
	
	public Usuario obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		Usuario usuario = new Usuario();
		try
		 {
			 ResultSet rs= cn.query("Select U.idUsuario, U.contrasenia, U.DNI, U.CUIL, U.nombre, U.apellido, U.sexo, U.fechaNacimiento, U.direccion, N.idNacionalidad, N.descripcion, P.idProvincia, P.descripcion, L.idLocalidad, L.descripcion, U.mail, U.telefono , U.idEstado from usuarios as U inner join nacionalidades as N on U.idNacionalidad = N.idNacionalidad inner join provincias as P on U.idProvincia = P.idProvincia inner join localidades as L on U.idLocalidad = L.idLocalidad where U.idTipoUsuario = 2 and U.idEstado = 1 and U.idUsuario="+id);
			 rs.next();
			 usuario.setIdUsuario(rs.getLong("U.idUsuario"));
			 usuario.setContrasenia(rs.getString("U.contrasenia"));
			 usuario.setDni(rs.getString("U.DNI"));
			 usuario.setCuil(rs.getString("U.CUIL"));
			 usuario.setNombre(rs.getString("U.nombre"));
			 usuario.setApellido(rs.getString("U.apellido"));
			 usuario.setSexo(rs.getString("U.sexo"));
			 usuario.setFechaNac(rs.getDate("U.fechaNacimiento"));
			 usuario.setDireccion(rs.getString("U.direccion"));
			 
			 Nacionalidad nacionalidad = new Nacionalidad();
			 nacionalidad.setIdNacionalidad(rs.getInt("N.idNacionalidad"));
			 nacionalidad.setDescripcion(rs.getString("N.descripcion"));
			 

			 Provincia provincia = new Provincia();
			 provincia.setIdProvincia(rs.getInt("P.idProvincia"));
			 provincia.setDescripcion(rs.getString("P.descripcion"));
			 
			 Localidad localidad = new Localidad();
			 localidad.setIdLocalidad(rs.getInt("L.idLocalidad"));
			 localidad.setDescripcion(rs.getString("L.descripcion"));
			 
			 usuario.setMail(rs.getString("U.mail"));
			 usuario.setTelefono(rs.getString("U.telefono"));
			 
			 usuario.setNacionalidad(nacionalidad);
			 usuario.setProvincia(provincia);
			 usuario.setLocalidad(localidad);
			 usuario.setEstado(rs.getBoolean("U.idEstado"));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return usuario;
	}
	
	
	
	
	//metodo que verifica que los campos no esten vacios -->
	public boolean verificarCampos(Usuario usu)
	{
		boolean resultado=false;
		
		if(usu.getUsuario().isEmpty() || usu.getContrasenia().isEmpty() || usu.getDni().isEmpty() || usu.getCuil().isEmpty()
			|| usu.getNombre().isEmpty() || usu.getApellido().isEmpty() || usu.getDireccion().isEmpty() || usu.getMail().isEmpty()
			|| usu.getTelefono().isEmpty())
		{
			
		}
		else
		{
			resultado=true;
		}
		return resultado;
	}
	
	//metodo
	public boolean altaUsuario(Usuario usu)
	{
		
		return true;
	}
	
public List<Usuario> listarUsuarios(){
		
		cn = new Conexion();
		cn.Open();
		 List<Usuario> listaU = new ArrayList<Usuario>();
		 try
		 {
			 ResultSet rs= cn.query("Select U.idUsuario, U.usuario, U.contrasenia, U.DNI, U.CUIL, U.nombre, U.apellido, U.sexo, U.fechaNacimiento, U.direccion, N.idNacionalidad, N.descripcion, P.idProvincia, P.descripcion, L.idLocalidad, L.descripcion, U.mail, U.telefono , U.idEstado from usuarios as U inner join nacionalidades as N on U.idNacionalidad = N.idNacionalidad inner join provincias as P on U.idProvincia = P.idProvincia inner join localidades as L on U.idLocalidad = L.idLocalidad where U.idTipoUsuario = 2 and U.idEstado = 1");
			 while(rs.next())
			 {
				 Usuario usuario = new Usuario();
				 usuario.setIdUsuario(rs.getLong("U.idUsuario"));
				 usuario.setUsuario(rs.getString("U.usuario"));
				 usuario.setContrasenia(rs.getString("U.contrasenia"));
				 usuario.setDni(rs.getString("U.DNI"));
				 usuario.setCuil(rs.getString("U.CUIL"));
				 usuario.setNombre(rs.getString("U.nombre"));
				 usuario.setApellido(rs.getString("U.apellido"));
				 usuario.setSexo(rs.getString("U.sexo"));
				 usuario.setFechaNac(rs.getDate("U.fechaNacimiento"));
				 usuario.setDireccion(rs.getString("U.direccion"));
				 
				 Nacionalidad nacionalidad = new Nacionalidad();
				 nacionalidad.setIdNacionalidad(rs.getInt("N.idNacionalidad"));
				 nacionalidad.setDescripcion(rs.getString("N.descripcion"));
				 

				 Provincia provincia = new Provincia();
				 provincia.setIdProvincia(rs.getInt("P.idProvincia"));
				 provincia.setDescripcion(rs.getString("P.descripcion"));
				 
				 Localidad localidad = new Localidad();
				 localidad.setIdLocalidad(rs.getInt("L.idLocalidad"));
				 localidad.setDescripcion(rs.getString("L.descripcion"));
				 
				 usuario.setMail(rs.getString("U.mail"));
				 usuario.setTelefono(rs.getString("U.telefono"));
				 
				 usuario.setNacionalidad(nacionalidad);
				 usuario.setProvincia(provincia);
				 usuario.setLocalidad(localidad);
				 
				 listaU.add(usuario);
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		
		return listaU;
	}

public List<Nacionalidad> listarNacionalidades(){
	
	cn = new Conexion();
	cn.Open();
	 List<Nacionalidad> listaNacionalidad = new ArrayList<Nacionalidad>();
	 try
	 {
		 ResultSet rs= cn.query("select * from nacionalidades");
		 while(rs.next())
		 {
			 
			 Nacionalidad nacionalidad = new Nacionalidad();
			 nacionalidad.setIdNacionalidad(rs.getInt("idNacionalidad"));
			 nacionalidad.setDescripcion(rs.getString("descripcion"));
			 
			 
			 
			 listaNacionalidad.add(nacionalidad);
		 }
		 
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
		 cn.close();
	 }
	
	return listaNacionalidad;
}

public List<Provincia> listarProvincias(){
	
	cn = new Conexion();
	cn.Open();
	 List<Provincia> listaProvincia = new ArrayList<Provincia>();
	 try
	 {
		 ResultSet rs= cn.query("select * from provincias");
		 while(rs.next())
		 {
			 
			 Provincia provincia = new Provincia();
			 provincia.setIdProvincia(rs.getInt("idProvincia"));
			 provincia.setDescripcion(rs.getString("descripcion"));
			 
			 
			 
			 listaProvincia.add(provincia);
		 }
		 
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
		 cn.close();
	 }
	
	return listaProvincia;
}

public List<Localidad> listarLocalidades(){
	
	cn = new Conexion();
	cn.Open();
	 List<Localidad> listaLocalidad = new ArrayList<Localidad>();
	 try
	 {
		 ResultSet rs= cn.query("select * from localidades");
		 while(rs.next())
		 {
			 
			 Localidad localidad = new Localidad();
			 localidad.setIdLocalidad(rs.getInt("idLocalidad"));
			 localidad.setDescripcion(rs.getString("descripcion"));
			 
			 
			 
			 listaLocalidad.add(localidad);
		 }
		 
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
		 cn.close();
	 }
	
	return listaLocalidad;
}


	
	
}
