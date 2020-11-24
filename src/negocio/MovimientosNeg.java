package negocio;

import java.util.List;

import entidad.Movimientos;
import entidad.TipoMovimiento;

public interface MovimientosNeg {
	
	public List<Movimientos> listarMovimientosPorCuenta(long id);
	public List<TipoMovimiento> listarTipoMovimiento();
	public boolean altaMovimento(Movimientos movimiento);
	public boolean AumentarSaldoCuentaDestino(long numeroCuentaDestino, double importe);
	public boolean DescontarSaldoCuentaOrigen(long idCuentaOrigen, double importe);

}
