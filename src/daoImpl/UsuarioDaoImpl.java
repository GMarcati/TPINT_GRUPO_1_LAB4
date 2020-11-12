package daoImpl;

import java.sql.ResultSet;

import dao.UsuarioDao;
import entidad.Usuario;


public class UsuarioDaoImpl implements UsuarioDao {

	private Conexion cn;
	
	public UsuarioDaoImpl()
	{
		
	}
		
	//METODO QUE VERIFICA QUE EL USUARIO Y LA CONTRASEÑA INGRESADOS, ESTEN EN LA DB --> 100%
	public boolean ingresar(String usuario, String contra)
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
	public void altaUsuario(Usuario usu)
	{
		
	}
	
	
}
