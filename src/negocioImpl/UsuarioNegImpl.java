package negocioImpl;

import java.util.ArrayList;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;
import negocio.UsuarioNeg;

public class UsuarioNegImpl implements UsuarioNeg{

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	
	public UsuarioNegImpl(UsuarioDao usuarioDao) 
	{
		this.usuarioDao = usuarioDao;
	}
	
	public UsuarioNegImpl() 
	{
		
	}
	
	public boolean verificarLogin(String nombre, String apellido) 
	{
		return usuarioDao.verificarLogin(nombre, apellido);
	}
	
	public boolean verificarTipoUsuario(String usuario)
	{
		return usuarioDao.verificarTipoUsuario(usuario);
	}
	
	public boolean altaUsuario()
	{
		return false;
	}
	
	public ArrayList<Usuario> listarUsuarios() 
	{
		return (ArrayList<Usuario>) usuarioDao.listarUsuarios();
	}
	
	public Usuario obtenerUno(int id) 
	{
		return usuarioDao.obtenerUno(id);
	}
	
	public boolean modificar(Usuario usuario) 
	{
		return usuarioDao.modificar(usuario);
	}
	
	public boolean eliminar(int id) 
	{
		return usuarioDao.eliminar(id);
	}
	
	public ArrayList<Nacionalidad> listarNacionalidades() 
	{
		return (ArrayList<Nacionalidad>) usuarioDao.listarNacionalidades();
	}
	
	public ArrayList<Provincia> listarProvincias() 
	{
		return (ArrayList<Provincia>) usuarioDao.listarProvincias();
	}
	
	public ArrayList<Localidad> listarLocalidades() 
	{
		return (ArrayList<Localidad>) usuarioDao.listarLocalidades();
	}
}
