package daoImpl;

import java.sql.ResultSet;

import dao.UsuarioDao;


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
		
	
}
