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
	
	public List<Cuenta> obtenerTodos(){
		
		cn = new Conexion();
		cn.Open();
		
		List<Cuenta> ListadoCuenta = new ArrayList<Cuenta>();
		 try
		 {
			 String query = "SELECT cuentas.idCuenta, cuentas.numeroCuenta, tipoCuentas.idTipoCuenta, tipoCuentas.descripcion, cuentas.fechaCreacion, cuentas.CBU, cuentas.saldo, estados.idEstado, estados.descripcion FROM cuentas inner join tipoCuentas on ( cuentas.idTipoCuenta = tipoCuentas.idTipoCuenta ) inner join on ( cuentas.idEstado = estados.idEstado )"; 
			 //String query = "Select idCuenta, numeroCuenta, CBU, saldo from cuentas"; 
			 ResultSet rs = cn.query(query);
			 while(rs.next())
			 {
				 	Cuenta x = new Cuenta();
				 	x.setIdCuenta(rs.getLong("cuentas.idCuenta"));
				 	x.setNumeroCuenta(rs.getLong("cuenta.numeroCuenta"));
				 	TipoCuenta tCuenta = new TipoCuenta();
				 	tCuenta.setIdTipoCuenta(rs.getInt("tipoCuentas.idTipoCuenta"));
				 	tCuenta.setDescripcion(rs.getString("tipoCuentas.descripcion"));
				 	x.setTipoCuenta(tCuenta);
				 	
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
				 	
				 	x.setCBU(rs.getLong("cuentas.CBU"));
				 	x.setSaldo(rs.getFloat("cuentas.saldo")); //ver

				 	x.setEstado(rs.getBoolean("estado.idEstado"));
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

	

	public Cuenta obtenerUno(int id) {
		cn = new Conexion();
		cn.Open();
		Cuenta cue = new Cuenta();
		try
		 {
			String query = "SELECT cuentas.idCuenta, cuentas.numeroCuenta, tipoCuentas.idTipoCuenta, tipoCuentas.descripcion, cuentas.fechaCreacion, cuentas.CBU, cuentas.saldo, estados.idEstado, estados.descripcion FROM cuentas inner join tipoCuentas on ( cuentas.idTipoCuenta = tipoCuentas.idTipoCuenta ) inner join on ( cuentas.idEstado = estados.idEstado )  where estados.descripcion='Falso' && cuentas.idCuenta="+id;
			 ResultSet rs= cn.query(query);
			 rs.next();
			 
			 	Cuenta x = new Cuenta();
			 	x.setIdCuenta(rs.getLong("cuentas.idCuenta"));
			 	x.setNumeroCuenta(rs.getLong("cuenta.numeroCuenta"));
			 	TipoCuenta tCuenta = new TipoCuenta();
			 	tCuenta.setIdTipoCuenta(rs.getInt("tipoCuentas.idTipoCuenta"));
			 	tCuenta.setDescripcion(rs.getString("tipoCuentas.descripcion"));
			 	x.setTipoCuenta(tCuenta);
			 	
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
			 	
			 	x.setCBU(rs.getLong("cuentas.CBU"));
			 	x.setSaldo(rs.getFloat("cuentas.saldo")); //ver

			 	x.setEstado(rs.getBoolean("estado.idEstado"));

			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return cue;
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
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE  cuentas SET idTipoCuenta='"+cuenta.getTipoCuenta().getIdTipoCuenta()+"', fechaCreacion='"+cuenta.getFechaCreacion()+"', saldo='"+cuenta.getSaldo()+"', idEstado='"+cuenta.getEstado()+"' WHERE idCuenta='"+cuenta.getIdCuenta()+"'";
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
	
	public boolean borrar(int id) {
		boolean estado=true;
		cn = new Conexion();
		cn.Open();		 
		String query = "UPDATE cuenta SET estado=0 WHERE idCuenta="+id;
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
	
	
}
