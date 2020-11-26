package negocioImpl;

import java.util.ArrayList;
import java.util.Date;
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
	
	
	
	public PrestamoNegImpl() {

	}



	public ArrayList<Prestamo> listarPrestamos() 
	{
		return (ArrayList<Prestamo>) prestamoDao.listarPrestamos();
	}
	
	public List<Prestamo> listarPrestamosPorCliente(long idUsuario){
		return (ArrayList<Prestamo>) prestamoDao.listarPrestamosPorCliente(idUsuario);
	}

	public boolean aceptarPrestamo(long id) {

		return prestamoDao.aceptarPrestamo(id);
	}

	public boolean rechazarPrestamo(long id) {

		return prestamoDao.rechazarPrestamo(id);
	}

	public boolean solicitudPrestamo(Prestamo prestamo) {
		
		return prestamoDao.solicitudPrestamo(prestamo);
	}
	
	public boolean pagarCuota(long idPrestamo) {
		return prestamoDao.pagarCuota(idPrestamo);
	}
	
	public Prestamo obtenerPrestamoPorId(long idPrestamo) {
		return prestamoDao.obtenerPrestamoPorId(idPrestamo);
	}



	@Override
	public int obtenerCantPrestamosSolicitadoPorFecha(Date fechaIni, Date fechaFin) {
		return prestamoDao.obtenerCantPrestamosSolicitadoPorFecha(fechaIni,fechaFin);
	}



	@Override
	public double obtenerTotalMontoSolicitadoPorFecha(Date fechaIni, Date fechaFin) {
		return prestamoDao.obtenerTotalMontoSolicitadoPorFecha(fechaIni,fechaFin);
	}
	
}
