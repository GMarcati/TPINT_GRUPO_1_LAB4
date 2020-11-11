package daoImpl;

import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao{

	private Conexion cn;
	
	public CuentaDaoImpl(){
		
	}
	
	public List<Cuenta> obtenerTodos(){
		
		cn = new Conexion();
		cn.Open();
		 List<Cuenta> list = new ArrayList<Cuenta>();
		 try
		 {
			 
			 //ACA VOY A DESARROLAR EL LISTADO DE CUENTAS
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return list;
	}
	
	public Cuenta obtenerUno(int id) {
		
		Cuenta cuenta = new Cuenta();
		
		return cuenta;
	}
	
	public boolean insertar(Cuenta cuenta) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO cuentas(numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado)  VALUES ('"+cuenta.getNumeroCuenta()+"','"+cuenta.getTipoCuenta().getIdTipoCuenta()+"','"+cuenta.getFechaCreacion()+"','"+cuenta.getCBU()+"','"+cuenta.getSaldo()+"','"+1+"')";
		//La siguiente linea es para ver los datos de la query
		//System.out.println(query);
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
	
	public boolean editar(Cuenta cuenta) {
		
		return true;
	}
	
	public boolean borrar(int id) {
		
		return true;
	}
	
	
}
