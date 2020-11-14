package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {


	public List<Cuenta> obtenerTodos();
	public Cuenta obtenerUno(int id);
	public boolean insertar(Cuenta cuenta);
	public boolean editar(Cuenta cuenta);
	public boolean borrar(int id);
	
}
