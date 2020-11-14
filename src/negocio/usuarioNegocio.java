package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class usuarioNegocio 
{
	private String host = "jdbc:mysql://localhost:3306/";
	private String dbName = "tpintegrador_bd";
	private String user = "root";
	private String pass = "root";
	
	public usuarioNegocio()
	{
		
	}
	
	//METODO QUE VERIFICA QUE EL USUARIO Y LA CONTRASEÑA INGRESADOS, ESTEN EN LA DB --> 100%
	public boolean ingresar(String usuario, String contra)
	{
		boolean resultado= false; //va a almacenar true, si hubo coincidencia de usuario y contraseña
		
		Connection cn = null;
		
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
			String query = "select * from usuarios where usuario='"+usuario+"' and contrasenia='"+contra+"'";
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			ResultSet rs=st.executeQuery(query);
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
			return resultado;
		}
	}
	
	public boolean verificarTipoUsuario(String usuario)
	{
		boolean resultado= false; //va a almacenar true, si es administrador y false si es cliente
		
		Connection cn = null;
		
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
			String query = "select idTipoUsuario from usuarios where usuario= '"+usuario+"'";
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.next())
			{
				int tipo= rs.getInt(0);
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
	
	//METODO PARA AGREGAR EL USUARIO A LA BASE DE DATOS
	public boolean altaUsuario()
	{
		//falta
		return true;
	}
	
}
