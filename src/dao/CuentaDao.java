package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;

public interface CuentaDao 
{
	public List<Cuenta> listarCuentas();
	public List<Cuenta> listarNumeroCuentas(); //Para COMBO jsp transferir CUENTAS ACTIVAS
	public List<Cuenta> listarCuentasPorUsuario(long idUsuario); //Para COMBO jsp transferir
	public Cuenta obtenerUno(long id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cuenta);
	public boolean borrar(long id);
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario);
	public boolean ContCuentasPorCliente(long idUsuario);
	
	public long obtenerCuenta (long id);
}
