package negocio;

import java.util.Date;
import java.util.List;
import entidad.Prestamo;

public interface PrestamoNeg 
{
	public List<Prestamo> listarPrestamos();
	public List<Prestamo> listarPrestamosPorCliente(long idUsuario);
	public boolean aceptarPrestamo(long id);
	public boolean rechazarPrestamo(long id);
	public boolean solicitudPrestamo(Prestamo prestamo);
	public boolean pagarCuota(long idPrestamo);
	public Prestamo obtenerPrestamoPorId(long idPrestamo);
	///********* PARA EL REPORTE ************//
	public int obtenerCantPrestamosSolicitadoPorFecha(Date fechaIni, Date fechaFin);
	public double obtenerTotalMontoSolicitadoPorFecha(Date fechaIni, Date fechaFin);
	
}
