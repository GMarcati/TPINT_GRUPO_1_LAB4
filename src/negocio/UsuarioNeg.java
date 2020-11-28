package negocio;

import java.util.List;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;
import excepciones.AnioValidoException;
import excepciones.MayorEdadException;

public interface UsuarioNeg 
{
	public boolean verificarLogin(String nombre, String apellido);
	public boolean verificarTipoUsuario(String usuario);
	public boolean verificarMayorEdad(String fechaNac) throws MayorEdadException;
	public boolean verificarAnioIngresado(String fechaNac) throws AnioValidoException;
	public boolean altaUsuario(Usuario usuario);
	public boolean modificar(Usuario usuario);
	public boolean eliminar(int id);
	public List<Usuario> listarUsuarios();
	public Usuario obtenerUno(long id);
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario);
	public List<Nacionalidad> listarNacionalidades();
	public List<Provincia> listarProvincias();
	public List<Localidad> listarLocalidades();
}
