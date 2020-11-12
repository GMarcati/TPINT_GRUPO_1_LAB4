package negocio;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

public interface UsuarioNeg 
{
	public boolean ingresar(String nombre, String apellido);
	public boolean altaUsuario();
	//public boolean bajaUsuario();
	//public boolean modificarUsuario();
	//public boolean listarUsuarios();
	
}
