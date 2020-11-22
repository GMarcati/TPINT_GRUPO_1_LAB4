package negocio;

import java.util.List;
import entidad.Prestamo;

public interface PrestamoNeg 
{
	public List<Prestamo> listarPrestamos();
	public boolean aceptarPrestamo(long id);
	public boolean rechazarPrestamo(long id);
	public boolean solicitudPrestamo(Prestamo prestamo);
}
