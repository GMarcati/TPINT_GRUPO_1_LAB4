package negocioImpl;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import negocio.UsuarioNeg;

public class UsuarioNegImpl implements UsuarioNeg{

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	
	public UsuarioNegImpl(UsuarioDao usuarioDao) {
		
		this.usuarioDao = usuarioDao;
		
	}
	
	public UsuarioNegImpl() {
		
		
	}
	
	public boolean ingresar(String nombre, String apellido) {
		
		return usuarioDao.ingresar(nombre, apellido);
		
	}
	
}
