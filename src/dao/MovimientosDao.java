package dao;

import java.util.List;

import entidad.Movimientos;
import entidad.TipoMovimiento;

public interface MovimientosDao {
	
	public List<Movimientos> listarMovimientosPorCuenta(long id);
	public List<TipoMovimiento> listarTipoMovimiento();
	public boolean altaMovimento(Movimientos movimiento);
	public boolean AumentarSaldoCuentaDestino(long id, double importe);
	public boolean DescontarSaldoCuentaOrigen(long id, double importe);

}
