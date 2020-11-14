package dao;

import java.util.List;

import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;

public interface UsuarioDao 
{
	public boolean verificarLogin(String nombre, String apellido);
	public boolean verificarTipoUsuario(String usuario);
	public boolean altaUsuario(Usuario usu);
	public boolean modificar(Usuario usuario);
	public boolean eliminar(int id);
	public List<Usuario> listarUsuarios();
	public Usuario obtenerUno(int id);
	public List<Nacionalidad> listarNacionalidades();
	public List<Provincia> listarProvincias();
	public List<Localidad> listarLocalidades();
}
