package negocioImpl;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public boolean AumentarSaldoCuentaDestino(long numeroCuentaDestino, double importe) {
		return this.movimientoDao.AumentarSaldoCuentaDestino(numeroCuentaDestino, importe);
	}

	@Override
	public boolean DescontarSaldoCuentaOrigen(long idCuentaOrigen, double importe) {
		return this.movimientoDao.DescontarSaldoCuentaOrigen(idCuentaOrigen, importe);
	}

	@Override
	public int obtenerCantMovimientosPorFecha(Date fechaIni, Date fechaFin) {
		return movimientoDao.obtenerCantMovientosPorFecha(fechaIni,fechaFin);
	}

	@Override
	public double obtenerTotalImporteMovimientosPorFecha(Date fechaIni, Date fechaFin) {
		return movimientoDao.obtenerTotalImportesMovimientosPorFecha(fechaIni,fechaFin);
	}

}
