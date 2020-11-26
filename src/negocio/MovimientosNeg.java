package negocio;

import java.util.Date;
import java.util.List;

import entidad.Movimientos;
import entidad.TipoMovimiento;

public interface MovimientosNeg {
	
	public List<Movimientos> listarMovimientosPorCuenta(long id);
	public List<TipoMovimiento> listarTipoMovimiento();
	public boolean altaMovimento(Movimientos movimiento);
	public boolean AumentarSaldoCuentaDestino(long numeroCuentaDestino, double importe);
	public boolean DescontarSaldoCuentaOrigen(long idCuentaOrigen, double importe);
	///********* PARA EL REPORTE ************//
	public int obtenerCantMovimientosPorFecha(Date fechaIni, Date fechaFin);
	public double obtenerTotalImporteMovimientosPorFecha(Date fechaIni, Date fechaFin);

}
