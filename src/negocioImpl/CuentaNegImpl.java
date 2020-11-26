package negocioImpl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public boolean ContCuentasPorCliente(long idUsuario) {
		
		return cuentaDao.ContCuentasPorCliente(idUsuario);
	}

	@Override
	public List<Cuenta> listarNumeroCuentas() {
		return (ArrayList<Cuenta>) cuentaDao.listarNumeroCuentas();
	}

	@Override
	public List<Cuenta> listarCuentasPorUsuario(long idUsuario) {
		return (ArrayList<Cuenta>) cuentaDao.listarCuentasPorUsuario(idUsuario);
	}
	
	public Cuenta obtenerCuentaPorCBU(long cbu) {
		return cuentaDao.obtenerCuentaPorCBU(cbu);
	}
	
	public double obtenerSaldoPorIdCuenta(long idCuenta) {
		return cuentaDao.obtenerSaldoPorIdCuenta(idCuenta);
	}

	@Override
	public int obtenerCantCuentasCreadasPorFecha(Date fechaIni, Date fechaFin) {
		return cuentaDao.obtenerCantCuentasCreadasPorFecha(fechaIni,fechaFin);
	}

	@Override
	public double obtenerTotalSaldoCuentasPorFecha(Date fechaIni, Date fechaFin) {
		return cuentaDao.obtenerTotalSaldoCuentasPorFecha(fechaIni,fechaFin);
	}

}
