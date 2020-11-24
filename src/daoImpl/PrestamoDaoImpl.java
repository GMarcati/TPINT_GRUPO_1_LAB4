package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.EstadoPrestamo;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Prestamo;
import entidad.Provincia;
import entidad.TipoCuenta;
import entidad.Usuario;

public class PrestamoDaoImpl implements PrestamoDao
{
	private Conexion cn;
	
	public PrestamoDaoImpl()
	{
		
	}
	
	//METODO QUE LISTA TODOS LOS PRESTAMOS--> %
	public List<Prestamo> listarPrestamos()
	{
		cn = new Conexion();
		cn.Open();
		
		List<Prestamo> listaP = new ArrayList<Prestamo>();
		
		try
		{
			ResultSet rs= cn.query("select p.idPrestamo, p.idCuenta, p.importeAdevolver, p.fecha, p.montoSolicitado, p.cantidadMeses, p.valorCuota, ep.idEstadoPrestamo, ep.descripcion, p.idEstado from prestamos as p inner join estadosPrestamos as ep on p.idEstadoPrestamo=ep.idEstadoPrestamo");
		    while(rs.next())
			{
		    	Prestamo pres = new Prestamo();
		    	
		    	pres.setIdPrestamo(rs.getLong(1));
		    	Cuenta cuenta = new Cuenta();
		    	cuenta.setIdCuenta(rs.getLong(2));
		    	pres.setCuenta(cuenta);
		    	pres.setImporteAdevolver(rs.getDouble(3));
		    	pres.setFecha(rs.getDate(4));
		    	pres.setMontoSolicitado(rs.getDouble(5));
		    	pres.setCantidadMeses(rs.getInt(6));
		    	pres.setValorCuota(rs.getDouble(7));
		    	EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		    	estadoPrestamo.setIdEstadoPrestamo(rs.getInt(8));
		    	estadoPrestamo.setDescripcion(rs.getString(9));
		    	pres.setEstadoPrestamo(estadoPrestamo);
		    	pres.setEstado(rs.getBoolean(10));
				 
				listaP.add(pres);
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
		return listaP;
	}

	//METODO QUE APRUEBA EL PRESTAMO--> 
	public boolean aceptarPrestamo(long id) 
	{
		boolean estado=true;
		
		cn = new Conexion();
		cn.Open();
		String query = "Update prestamos set idEstado=2 WHERE idPrestamo="+id;
		
		try 
		{
			estado=cn.execute(query);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return estado; 
	}

	//METODO QUE RECHAZA EL PRESTAMO--> 
	public boolean rechazarPrestamo(long id) 
	{
		boolean estado=true;
		
		cn = new Conexion();
		cn.Open();
		String query = "Update prestamos set idEstado=0 WHERE idPrestamo="+id;
		
		try 
		{
			estado=cn.execute(query);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return estado; 
	}
	
	public boolean solicitudPrestamo(Prestamo prestamo) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		String query = "Insert into prestamos (idCuenta, idUsuario, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstadoPrestamo,idEstado) values ('"+prestamo.getCuenta().getIdCuenta()+"', '"+prestamo.getUsuario().getIdUsuario()+"' ,'"+prestamo.getImporteAdevolver()+"','"+prestamo.getFecha()+"', '"+prestamo.getMontoSolicitado()+"', '"+prestamo.getCantidadMeses()+"', '"+prestamo.getValorCuota()+"', '"+prestamo.getEstadoPrestamo().getIdEstadoPrestamo()+"', "+1+")";
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
	
	public List<Prestamo> listarPrestamosPorCliente(long idUsuario){
		
		cn = new Conexion();
		cn.Open();
		
		List<Prestamo> listaP = new ArrayList<Prestamo>();
		
		try
		{
			ResultSet rs= cn.query("select p.idPrestamo,p.idCuenta, p.importeAdevolver, p.fecha, p.montoSolicitado, p.cantidadMeses, p.valorCuota, ep.idEstadoPrestamo, ep.descripcion, p.idEstado from prestamos as p inner join estadosPrestamos as ep on p.idEstadoPrestamo=ep.idEstadoPrestamo where p.idUsuario="+idUsuario+" and ep.idEstadoPrestamo="+2);
		    while(rs.next())
			{
		    	Prestamo pres = new Prestamo();
		    	
		    	pres.setIdPrestamo(rs.getLong(1));
		    	Cuenta cuenta = new Cuenta();
		    	cuenta.setIdCuenta(rs.getLong(2));
		    	pres.setCuenta(cuenta);
		    	pres.setImporteAdevolver(rs.getDouble(3));
		    	pres.setFecha(rs.getDate(4));
		    	pres.setMontoSolicitado(rs.getDouble(5));
		    	pres.setCantidadMeses(rs.getInt(6));
		    	pres.setValorCuota(rs.getDouble(7));
		    	EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		    	estadoPrestamo.setIdEstadoPrestamo(rs.getInt(8));
		    	estadoPrestamo.setDescripcion(rs.getString(9));
		    	pres.setEstadoPrestamo(estadoPrestamo);
		    	pres.setEstado(rs.getBoolean(10));
				 
				listaP.add(pres);
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
		return listaP;
		
	}
	
	public boolean pagarCuota(long idPrestamo)
	{
		boolean estado=true;
		
		cn = new Conexion();
		cn.Open();
		String query = "update prestamos set cantidadMeses=cantidadMeses-1 where idPrestamo="+idPrestamo;
		
		try 
		{
			estado=cn.execute(query);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return estado; 
	}
	
	public Prestamo obtenerPrestamoPorId(long idPrestamo) {
		cn = new Conexion();
		cn.Open();
		Prestamo prestamo = new Prestamo();
		try
		 {
			 String query = "select p.idPrestamo, p.idCuenta, p.importeAdevolver, p.fecha, p.montoSolicitado, p.cantidadMeses, p.valorCuota, ep.idEstadoPrestamo, ep.descripcion, p.idEstado from prestamos as p inner join estadosPrestamos as ep on p.idEstadoPrestamo=ep.idEstadoPrestamo where p.idPrestamo="+idPrestamo;
			 ResultSet rs= cn.query(query);
			 rs.next();
			 
			 	
			 	prestamo.setIdPrestamo(rs.getLong(1));
		    	Cuenta cuenta = new Cuenta();
		    	cuenta.setIdCuenta(rs.getLong(2));
		    	prestamo.setCuenta(cuenta);
		    	prestamo.setImporteAdevolver(rs.getDouble(3));
		    	prestamo.setFecha(rs.getDate(4));
		    	prestamo.setMontoSolicitado(rs.getDouble(5));
		    	prestamo.setCantidadMeses(rs.getInt(6));
		    	prestamo.setValorCuota(rs.getDouble(7));
		    	EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		    	estadoPrestamo.setIdEstadoPrestamo(rs.getInt(8));
		    	estadoPrestamo.setDescripcion(rs.getString(9));
		    	prestamo.setEstadoPrestamo(estadoPrestamo);
		    	prestamo.setEstado(rs.getBoolean(10));
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		return prestamo;
		
	}
	
}
