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
	

	public ArrayList<Cuenta> listarCuentas() {
		return (ArrayList<Cuenta>) cuentaDao.listarCuentas();
	}

	@Override
	public Cuenta obtenerUno(long id) {
		return cuentaDao.obtenerUno(id);
	}

	@Override
	public boolean editar(Cuenta cuenta) {
		return cuentaDao.editar(cuenta);
	}

	@Override
	public boolean borrar(long id) {
		return cuentaDao.borrar(id);
	}
	
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario) {
		
		return cuentaDao.AsignarCuentaACliente(idCuenta, idUsuario);
	}

}
