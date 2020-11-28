package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import dao.UsuarioDao;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.TipoUsuario;
import entidad.Usuario;
import excepciones.AnioValidoException;
import excepciones.MayorEdadException;


public class UsuarioDaoImpl implements UsuarioDao {

	private Conexion cn;
	
	public UsuarioDaoImpl()
	{
		
	}
	
	//metodo que verifica que el futuro usuario sea mayor de edad. Si devuelve true, lo es
	public boolean verificarMayorEdad(String fechaNac) throws MayorEdadException
	{
		Boolean opc=false;
		
		Calendar c = Calendar.getInstance();//se instancia Calendar, con la fecha del sistema
		int anioActual = c.get(Calendar.YEAR);//variable donde se va a guardar a�o del sistema

		String anioSub=fechaNac.substring(0, 4);//capturo de la fecha ingresada por parametro, el a�o de nacimiento
		int anio=Integer.parseInt(anioSub); // se parsea a entero
		int edad= anioActual- anio;
	
		if(edad<18)
		{
			MayorEdadException exc1= new MayorEdadException();
			opc=false;
			throw exc1;
		}
		else
		{
			opc=true;
		}
		return opc;
	}
	
	//metodo que verifica que el futuro usuario ingrese un a�o de nacimiento menor al actual y mayor 
	// a 100 a�os para atras
	public boolean verificarAnioIngresado(String fechaNac) throws AnioValidoException
	{
		Boolean opc=false;
		
		Calendar c = Calendar.getInstance();//se instancia Calendar, con la fecha del sistema
		int anioActual = c.get(Calendar.YEAR);//variable donde se va a guardar a�o del sistema

		String anioIngresado=fechaNac.substring(0, 4);//capturo de la fecha ingresada por parametro, el a�o de nacimiento
		int anio=Integer.parseInt(anioIngresado); // se parsea a entero
		int maximaEdadPermitida= anioActual - 100; 
	
		if(anio> anioActual || anio<maximaEdadPermitida)
		{
			AnioValidoException exc1= new AnioValidoException();
			opc=false;
			throw exc1;
		}
		else
		{
			opc=true;
		}
		return opc;
	}
		
	//METODO QUE VERIFICA QUE EL USUARIO Y LA CONTRASE�A INGRESADOS, ESTEN EN LA DB --> 100%
	public boolean verificarLogin(String usuario, String contra)
	{
		cn = new Conexion();
		cn.Open();
		
		boolean resultado= false; //va a almacenar true, si hubo coincidencia de usuario y contrase�a
		
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
			ResultSet rs= cn.query("select * from usuarios where usuario='"+usuario+"' and contrasenia='"+contra+"' and idEstado=1");
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
		}
		finally
		{
			cn.close();
		}
		return resultado;
	}
	
	public boolean modificar(Usuario usuario) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String query = "Update usuarios set contrasenia='"+usuario.getContrasenia() +"',DNI='"+usuario.getDni()+"',CUIL='"+usuario.getCuil()+"',nombre='"+usuario.getNombre()+"',apellido='"+usuario.getApellido()+"',sexo='"+usuario.getSexo()+"',fechaNacimiento='"+formatter.format(usuario.getFechaNac())+"',direccion='"+usuario.getDireccion()+"',idLocalidad="+usuario.getLocalidad().getIdLocalidad()+",idNacionalidad="+usuario.getNacionalidad().getIdNacionalidad()+",idProvincia="+usuario.getProvincia().getIdProvincia()+",mail='"+usuario.getMail()+"',telefono='"+usuario.getTelefono()+"' where idUsuario="+usuario.getIdUsuario()+"";

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
	
	
	public Usuario obtenerUno(long id) {
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
	
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		
		cn = new Conexion();
		cn.Open();
		Usuario usuario = new Usuario();
		try
		 {
			 ResultSet rs= cn.query("Select U.idUsuario, U.usuario, U.contrasenia, U.idTipoUsuario, U.DNI, U.CUIL, U.nombre, U.apellido, U.sexo, U.fechaNacimiento, U.direccion, N.idNacionalidad, N.descripcion, P.idProvincia, P.descripcion, L.idLocalidad, L.descripcion, U.mail, U.telefono from usuarios as U inner join nacionalidades as N on U.idNacionalidad = N.idNacionalidad inner join provincias as P on U.idProvincia = P.idProvincia inner join localidades as L on U.idLocalidad = L.idLocalidad where U.idEstado = 1 and U.Usuario='"+nombreUsuario+"'");
			 rs.next();
			 usuario.setIdUsuario(rs.getLong("U.idUsuario"));
			 usuario.setUsuario(rs.getString("U.usuario"));
			 usuario.setContrasenia(rs.getString("U.contrasenia"));
			 TipoUsuario tUsuario = new TipoUsuario();
			 tUsuario.setIdTipoUsuario(rs.getInt("U.idTipoUsuario"));
			 usuario.setTipoUsuario(tUsuario);
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
			 //System.out.println(nombreUsuario);
			 
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
	public boolean altaUsuario(Usuario usuario)
	{
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String query = "INSERT INTO usuarios(usuario, contrasenia, idTipoUsuario, DNI, CUIL, nombre, apellido, sexo, fechaNacimiento, direccion, idLocalidad, idNacionalidad, idProvincia, mail, telefono, idEstado)  VALUES ('"+usuario.getUsuario()+"', '"+usuario.getContrasenia()+"', "+2+", '"+usuario.getDni()+"', '"+usuario.getCuil()+"', '"+usuario.getNombre()+"', '"+usuario.getApellido()+"', '"+usuario.getSexo()+"', '"+formatter.format(usuario.getFechaNac())+"', '"+usuario.getDireccion()+"', '"+usuario.getLocalidad().getIdLocalidad()+"', '"+usuario.getNacionalidad().getIdNacionalidad()+"', '"+usuario.getProvincia().getIdProvincia()+"', '"+usuario.getMail()+"', '"+usuario.getTelefono()+"', "+1+")";
		//La siguiente linea es para ver los datos de la query
		//System.out.println(query);
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

	public List<Localidad> listarLocalidades()
	{
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
