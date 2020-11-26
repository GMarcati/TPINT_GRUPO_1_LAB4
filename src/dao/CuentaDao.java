package dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {


	public List<Cuenta> listarCuentas();
	public List<Cuenta> listarNumeroCuentas(); //Para COMBO jsp transferir CUENTAS ACTIVAS
	public List<Cuenta> listarCuentasPorUsuario(long idUsuario); //Para COMBO jsp transferir
	public Cuenta obtenerUno(long id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cuenta);
	public boolean borrar(long id);
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario);
	public boolean ContCuentasPorCliente(long idUsuario);
	public Cuenta obtenerCuentaPorCBU(long cbu);
	public double obtenerSaldoPorIdCuenta(long idCuenta);
	///********* PARA EL REPORTE ************//
	public int obtenerCantCuentasCreadasPorFecha(Date fechaIni, Date fechaFin);
	public double obtenerTotalSaldoCuentasPorFecha(Date fechaIni, Date fechaFin);
	
}
