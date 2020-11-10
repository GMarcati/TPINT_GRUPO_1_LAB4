package daoImpl;

import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao{

	
	public CuentaDaoImpl(){
		
	}
	
	public List<Cuenta> obtenerTodos(){
		
		List<Cuenta> listaCuenta = new ArrayList<Cuenta>();
		
		
		return listaCuenta;
	}
	
	public Cuenta obtenerUno(int id) {
		
		Cuenta cuenta = new Cuenta();
		
		return cuenta;
	}
	public boolean insertar(Cuenta cuenta) {
		
		return true;
	}
	public boolean editar(Cuenta cuenta) {
		
		return true;
	}
	public boolean borrar(int id) {
		
		return true;
	}
	
	
}
