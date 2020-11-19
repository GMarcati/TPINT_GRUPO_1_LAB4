package daoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.CuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao{

	private Conexion cn;
	
	public CuentaDaoImpl(){
		
	}
	
	public List<Cuenta> listarCuentas(){
		
		cn = new Conexion();
		cn.Open();
		
		List<Cuenta> ListadoCuenta = new ArrayList<Cuenta>();
		 try
		 {
			 String query = "SELECT C.idCuenta, C.numeroCuenta, TC.idTipoCuenta, TC.descripcion, C.fechaCreacion, C.CBU, C.saldo, C.idEstado FROM cuentas as C inner join tipoCuentas as TC on C.idTipoCuenta = TC.idTipoCuenta where C.idEstado=1"; 
			 //String query = "SELECT cuentas.idCuenta, cuentas.numeroCuenta, cuentas.saldo FROM cuentas"; 
			 ResultSet rs = cn.query(query);
			 while(rs.next())
			 {
				 	Cuenta x = new Cuenta();
				 	x.setIdCuenta(rs.getLong("C.idCuenta"));
				 	x.setNumeroCuenta(rs.getLong("C.numeroCuenta"));
				 	TipoCuenta tCuenta = new TipoCuenta();
				 	tCuenta.setIdTipoCuenta(rs.getInt("TC.idTipoCuenta"));
				 	tCuenta.setDescripcion(rs.getString("TC.descripcion"));
				 	x.setTipoCuenta(tCuenta);
				 	x.setFechaCreacion(rs.getDate("C.fechaCreacion"));
				 	x.setCBU(rs.getLong("C.CBU"));
				 	x.setSaldo(rs.getDouble("C.saldo"));
				 	x.setEstado(rs.getInt("C.idEstado"));
				 	
				 	// **** ver el como castearlo
				 	/*DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
				 	try
				 	{
				 		LocalDate convertido= (LocalDate)fecha.parse(rs.getDate("cuentas.fechaCreacion"));
				 		x.setFechaCreacion(convertido);
				 	}
				 	catch(ParseException e)
				 	{
				 		e.printStackTrace();
				 	}*/
				 	
				 	//x.setCBU(rs.getLong("cuentas.CBU"));
				 	//x.setSaldo(rs.getDouble("cuentas.saldo")); //ver
	
				 	ListadoCuenta.add(x);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return ListadoCuenta;
		
	}

	

	public Cuenta obtenerUno(long id) {
		cn = new Conexion();
		cn.Open();
		Cuenta cuenta = new Cuenta();
		try
		 {
			 String query = "SELECT C.idCuenta, C.numeroCuenta, TC.idTipoCuenta, TC.descripcion, C.fechaCreacion, C.CBU, C.saldo FROM cuentas as C inner join tipoCuentas as TC on C.idTipoCuenta = TC.idTipoCuenta where C.idEstado=1 && C.idCuenta="+id;
			 ResultSet rs= cn.query(query);
			 rs.next();
			 
			 	
			 	cuenta.setIdCuenta(rs.getLong("C.idCuenta"));
			 	cuenta.setNumeroCuenta(rs.getLong("C.numeroCuenta"));
			 	TipoCuenta tCuenta = new TipoCuenta();
			 	tCuenta.setIdTipoCuenta(rs.getInt("TC.idTipoCuenta"));
			 	tCuenta.setDescripcion(rs.getString("TC.descripcion"));
			 	cuenta.setTipoCuenta(tCuenta);
			 	
			 	// **** ver el como castearlo
			 	/*DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
			 	try
			 	{
			 		LocalDate convertido= (LocalDate)fecha.parse(rs.getDate("cuentas.fechaCreacion"));
			 		x.setFechaCreacion(convertido);
			 	}
			 	catch(ParseException e)
			 	{
			 		e.printStackTrace();
			 	}*/
			 	cuenta.setFechaCreacion(rs.getDate("C.fechaCreacion"));
			 	cuenta.setCBU(rs.getLong("C.CBU"));
			 	cuenta.setSaldo(rs.getDouble("C.saldo")); //ver
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return cuenta;
	}
	
	public boolean insertar(Cuenta cuenta) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO cuentas(numeroCuenta, idTipoCuenta, fechaCreacion, CBU, saldo, idEstado)  VALUES ('"+cuenta.getNumeroCuenta()+"','"+cuenta.getTipoCuenta().getIdTipoCuenta()+"','"+cuenta.getFechaCreacion()+"','"+cuenta.getCBU()+"','"+cuenta.getSaldo()+"',"+1+")";
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
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE cuentas SET numeroCuenta='"+cuenta.getNumeroCuenta()+"', idTipoCuenta='"+cuenta.getTipoCuenta().getIdTipoCuenta()+"', CBU='"+cuenta.getCBU()+"', saldo='"+cuenta.getSaldo()+"' WHERE idCuenta='"+cuenta.getIdCuenta()+"'";
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
	
	public boolean borrar(long id) {
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE cuentas SET idEstado=0 WHERE idCuenta="+id;
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
	
	public boolean AsignarCuentaACliente(long idCuenta, long idUsuario) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		//String query = "Insert into cuentas(idUsuario) VALUES ("+idUsuario+") where idCuenta="+idCuenta;
		String query = "Update cuentas set idUsuario="+idUsuario+" where idCuenta="+idCuenta;
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
	
	public boolean ContCuentasPorCliente(long idUsuario)
	{
		cn = new Conexion();
		cn.Open();
		
		boolean estado= true;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			ResultSet rs= cn.query("select COUNT(idUsuario) from cuentas where idUsuario="+idUsuario+" and idEstado=1");
			rs.next();
			
			int cont= rs.getInt(1);
			if(cont>=3)
			{
				estado=false;
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
	

	//******** LISTAR NUMEROCUENTA PARA JSP TRANSFERENCIA **********///
	public List<Cuenta> listarNumeroCuentas(){
		cn = new Conexion();
		cn.Open();
		
		List<Cuenta> ListadoNroCuenta = new ArrayList<Cuenta>();
		 try
		 {
			 String query = "SELECT C.idCuenta, C.numeroCuenta FROM cuentas as C where C.idEstado=1"; 
			 ResultSet rs = cn.query(query);
			 while(rs.next())
			 {
				 	Cuenta x = new Cuenta();
				 	x.setIdCuenta(rs.getLong("C.idCuenta"));
				 	x.setNumeroCuenta(rs.getLong("C.numeroCuenta"));
	
				 	ListadoNroCuenta.add(x);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return ListadoNroCuenta;
	}
	
	//******** LISTAR CUENTA POR USUARIO PARA JSP TRANSFERENCIA **********///
	public List<Cuenta> listarCuentasPorUsuario(long idUsuario){
		cn = new Conexion();
		cn.Open();
		
		List<Cuenta> ListadoCuentaXUsuario = new ArrayList<Cuenta>();
		 try
		 {
			 String query = "SELECT C.idCuenta, C.numeroCuenta FROM cuentas as C where C.idUsuario="+idUsuario; 
			 ResultSet rs = cn.query(query);
			 while(rs.next())
			 {
				 	Cuenta x = new Cuenta();
				 	x.setIdCuenta(rs.getLong("C.idCuenta"));
				 	x.setCBU(rs.getLong("C.numeroCuenta"));
				 	
	
				 	ListadoCuentaXUsuario.add(x);
			 }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return ListadoCuentaXUsuario;
	}
	
	
	
}
