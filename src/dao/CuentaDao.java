package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {


	public List<Cuenta> listarCuentas();
	public Cuenta obtenerUno(long id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cuenta);
	public boolean borrar(long id);
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario);
	
}
