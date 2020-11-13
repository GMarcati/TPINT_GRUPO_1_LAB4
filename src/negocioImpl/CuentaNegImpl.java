package negocioImpl;

import java.util.ArrayList;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {

	private CuentaDao cuentaDao = new CuentaDaoImpl();
	
	public CuentaNegImpl(CuentaDao cuentaDao) {
		
		this.cuentaDao = cuentaDao;
	}
	
	public CuentaNegImpl() {
		
	}
	
	public boolean insertar(Cuenta cuenta) {
		
		return this.cuentaDao.insertar(cuenta);
		
	}
	

	public ArrayList<Cuenta> ListarCuentas() {
		return (ArrayList<Cuenta>) cuentaDao.obtenerTodos();
	}

	@Override
	public Cuenta obtenerUno(int id) {
		return cuentaDao.obtenerUno(id);
	}

	@Override
	public boolean editar(Cuenta cat) {
		return cuentaDao.editar(cat);
	}

	@Override
	public boolean borrar(int id) {
		return cuentaDao.borrar(id);
	}

}
