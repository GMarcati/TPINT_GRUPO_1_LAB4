package negocio;

import java.util.List;

import entidad.Cuenta;

public interface CuentaNeg {

	public List<Cuenta> listarCuentas();
	public Cuenta obtenerUno(long id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cat);
	public boolean borrar(long id);
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario);
	
}
