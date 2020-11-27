package dao;

import java.util.Date;
import java.util.List;

import entidad.CuotasPrestamo;
import entidad.Prestamo;

public interface PrestamoDao 
{
	public List<Prestamo> listarPrestamos();
	public List<Prestamo> listarPrestamosPorCliente(long idUsuario);
	public boolean aceptarPrestamo(long id);
	public boolean rechazarPrestamo(long id);
	public boolean solicitudPrestamo(Prestamo prestamo);
	public Prestamo obtenerPrestamoPorId(long idPrestamo);
	public boolean generarCuotas(CuotasPrestamo cuotasPrestamo);
	public List<CuotasPrestamo> listarCuotas(long idPrestamo);
	public boolean pagarCuota(long idPrestamo, long cuota);
	///********* PARA EL REPORTE ************//
	public int obtenerCantPrestamosSolicitadoPorFecha(Date fechaIni, Date fechaFin);
	public double obtenerTotalMontoSolicitadoPorFecha(Date fechaIni, Date fechaFin);
}
