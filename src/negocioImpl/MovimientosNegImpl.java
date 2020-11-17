package negocioImpl;

import java.util.List;

import dao.MovimientosDao;
import daoImpl.MovimientosDaoImpl;
import entidad.Movimientos;
import negocio.MovimientosNeg;

public class MovimientosNegImpl implements MovimientosNeg {
	
	private MovimientosDao movimientoDao = new MovimientosDaoImpl();
	
	public MovimientosNegImpl(MovimientosDao movimientoDao) {
		
		this.movimientoDao = movimientoDao;
	}
	
	public MovimientosNegImpl() {
		
	}
	
	@Override
	public List<Movimientos> listarMovimientos() {
		// TODO Auto-generated method stub
		return null;
	}

}
