package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;

public interface CuentaNeg {

	public ArrayList<Cuenta> ListarCuentas();
	public Cuenta obtenerUno(long id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cat);
	public boolean borrar(long id);
	
}
