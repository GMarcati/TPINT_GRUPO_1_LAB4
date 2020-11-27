package negocioImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PrestamoDao;
import dao.UsuarioDao;
import daoImpl.PrestamoDaoImpl;
import daoImpl.UsuarioDaoImpl;
import entidad.CuotasPrestamo;
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



	public boolean generarCuotas(CuotasPrestamo cuotasPrestamo) {
		return prestamoDao.generarCuotas(cuotasPrestamo);
	}
	
	public List<CuotasPrestamo> listarCuotas(long idPrestamo){
		return prestamoDao.listarCuotas(idPrestamo);
	}
	
	public boolean pagarCuota(long idPrestamo, long cuota) {
		return prestamoDao.pagarCuota(idPrestamo, cuota);
	}
	
	public boolean prestamoFinalizado(long idPrestamo){
		return prestamoDao.prestamoFinalizado(idPrestamo);
	}
	
	public int obtenerCuotasEnEstadoInactivo(long idPrestamo) {
		return prestamoDao.obtenerCuotasEnEstadoInactivo(idPrestamo);
	}
}
