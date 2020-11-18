package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.MovimientosDao;
import daoImpl.MovimientosDaoImpl;
import entidad.Movimientos;
import entidad.TipoMovimiento;
import negocio.MovimientosNeg;

public class MovimientosNegImpl implements MovimientosNeg {
	
	private MovimientosDao movimientoDao = new MovimientosDaoImpl();
	
	public MovimientosNegImpl(MovimientosDao movimientoDao) {
		
		this.movimientoDao = movimientoDao;
	}
	
	public MovimientosNegImpl() {
		
	}
	
	@Override
	public List<Movimientos> listarMovimientosPorCuenta(long id) {
		return (ArrayList<Movimientos>) movimientoDao.listarMovimientosPorCuenta(id);
	}

	public List<TipoMovimiento> listarTipoMovimiento() {
		return (ArrayList<TipoMovimiento>) movimientoDao.listarTipoMovimiento();
	}

	public boolean altaMovimento(Movimientos movimiento) {
		return this.movimientoDao.altaMovimento(movimiento);
	}

}
