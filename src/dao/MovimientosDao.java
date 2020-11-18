package dao;

import java.util.List;

import entidad.Movimientos;
import entidad.TipoMovimiento;

public interface MovimientosDao {
	
	public List<Movimientos> listarMovimientosPorCuenta(long id);
	public List<TipoMovimiento> listarTipoMovimiento();
	public boolean altaMovimento(Movimientos movimiento);

}
