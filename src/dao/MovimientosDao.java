package dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import entidad.Movimientos;
import entidad.TipoMovimiento;

public interface MovimientosDao {
	
	public List<Movimientos> listarMovimientosPorCuenta(long id);
	public List<TipoMovimiento> listarTipoMovimiento();
	public boolean altaMovimento(Movimientos movimiento);
	public boolean AumentarSaldoCuentaDestino(long numeroCuentaDestino, double importe);
	public boolean DescontarSaldoCuentaOrigen(long idCuentaOrigen, double importe);
	
	///********* PARA EL REPORTE ************//
	public int obtenerCantMovientosPorFecha(Date fechaIni, Date fechaFin);
	public double obtenerTotalImportesMovimientosPorFecha(Date fechaIni, Date fechaFin);

}
