package dao;

import entidad.Usuario;

public interface UsuarioDao 
{
	public boolean ingresar(String nombre, String apellido);
	public void altaUsuario(Usuario usu);
	//public boolean bajaUsuario();
	
}
