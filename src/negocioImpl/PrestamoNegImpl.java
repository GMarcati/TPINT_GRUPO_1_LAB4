package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import dao.UsuarioDao;
import daoImpl.PrestamoDaoImpl;
import daoImpl.UsuarioDaoImpl;
import entidad.Prestamo;
import entidad.Usuario;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg
{
	private PrestamoDao prestamoDao = new PrestamoDaoImpl();
	
	public PrestamoNegImpl(PrestamoDao prestamoDao) 
	{
		this.prestamoDao = prestamoDao;
	}
	
	public ArrayList<Prestamo> listarPrestamos() 
	{
		return (ArrayList<Prestamo>) prestamoDao.listarPrestamos();
	}
}
