package negocioImpl;

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
	
}
