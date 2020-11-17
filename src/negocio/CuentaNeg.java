package negocio;

import java.util.List;

import entidad.Cuenta;

public interface CuentaNeg {

	public List<Cuenta> listarCuentas();
	public List<Cuenta> listarNumeroCuentas();
	public List<Cuenta> listarCuentasPorUsuario(long idUsuario);
	public Cuenta obtenerUno(long id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cuenta);
	public boolean borrar(long id);
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario);
	
}
