package negocio;

import java.util.List;

import entidad.Movimientos;
import entidad.TipoMovimiento;

public interface MovimientosNeg {
	
	public List<Movimientos> listarMovimientosPorCuenta(long id);
	public List<TipoMovimiento> listarTipoMovimiento();
	public boolean altaMovimento(Movimientos movimiento);

}
