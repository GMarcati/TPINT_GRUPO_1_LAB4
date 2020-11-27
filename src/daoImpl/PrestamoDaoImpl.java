package daoImpl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.CuotasPrestamo;
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
			ResultSet rs= cn.query("select p.idPrestamo, p.idCuenta, p.idUsuario, c.numeroCuenta, u.usuario, p.importeAdevolver, p.fecha, p.montoSolicitado, p.cantidadMeses, p.valorCuota, ep.idEstadoPrestamo, ep.descripcion, p.idEstado from prestamos as p inner join estadosPrestamos as ep on p.idEstadoPrestamo=ep.idEstadoPrestamo inner join cuentas as c on c.idCuenta=p.idCuenta inner join usuarios as u on u.idUsuario=p.idUsuario");
		    while(rs.next())
			{
		    	Prestamo pres = new Prestamo();
		    	
		    	pres.setIdPrestamo(rs.getLong(1));
		    	Cuenta cuenta = new Cuenta();
		    	cuenta.setIdCuenta(rs.getLong(2));
		    	Usuario usuario = new Usuario();
		    	usuario.setIdUsuario(rs.getLong(3));
		    	cuenta.setNumeroCuenta(rs.getLong(4));
		    	usuario.setUsuario(rs.getString(5));
		    	pres.setCuenta(cuenta);
		    	pres.setUsuario(usuario);
		    	pres.setImporteAdevolver(rs.getDouble(6));
		    	pres.setFecha(rs.getDate(7));
		    	pres.setMontoSolicitado(rs.getDouble(8));
		    	pres.setCantidadMeses(rs.getInt(9));
		    	pres.setValorCuota(rs.getDouble(10));
		    	EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		    	estadoPrestamo.setIdEstadoPrestamo(rs.getInt(11));
		    	estadoPrestamo.setDescripcion(rs.getString(12));
		    	pres.setEstadoPrestamo(estadoPrestamo);
		    	pres.setEstado(rs.getBoolean(13));
				 
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
		String query = "Update prestamos set idEstadoPrestamo=2 WHERE idPrestamo="+id;
		
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
		String query = "Update prestamos set idEstadoPrestamo=3 WHERE idPrestamo="+id;
		
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
	
	@Override
	public int obtenerCantPrestamosSolicitadoPorFecha(Date fechaIni, Date fechaFin) {
		cn = new Conexion();
		cn.Open();
		
		int cant = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString1 = format.format(fechaIni);
		String dateString2 = format.format(fechaFin);
		
		 try
		 {
			 ResultSet rs = cn.query("SELECT count(*) as cantprestamo FROM prestamos WHERE fecha BETWEEN '" + dateString1 + "' AND '" + dateString2 + "'");
			 //System.out.print(rs);
			 while(rs.next()) {
				 cant = rs.getInt("cantprestamo");
				 //System.out.print(cant);
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
		 return cant;
	}

	@Override
	public double obtenerTotalMontoSolicitadoPorFecha(Date fechaIni, Date fechaFin) {
		cn = new Conexion();
		cn.Open();
		
		double TotImporteSolicitado = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString1 = format.format(fechaIni);
		String dateString2 = format.format(fechaFin);
		
		 try
		 {
			 ResultSet rs = cn.query("SELECT SUM(montoSolicitado) as TotImporteSolicitado FROM prestamos WHERE fecha BETWEEN '" + dateString1 + "' AND '" + dateString2 + "'");
			 //System.out.print(rs);
			 while(rs.next()) {
				 TotImporteSolicitado = rs.getDouble("TotImporteSolicitado");
				 //System.out.print(saldoTot);
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
		 return TotImporteSolicitado;
	}
	
	public boolean generarCuotas(CuotasPrestamo cuotasPrestamo) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		String query = "Insert into cuotasPrestamo (cuota, idPrestamo, fechaPago, idEstado) values ('"+cuotasPrestamo.getCuota()+"', '"+cuotasPrestamo.getPrestamo().getIdPrestamo()+"' ,'"+cuotasPrestamo.getFechaPago()+"',"+1+")";
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
	
	public List<CuotasPrestamo> listarCuotas(long idPrestamo) {
		
		cn = new Conexion();
		cn.Open();
		
		List<CuotasPrestamo> listaCuotas = new ArrayList<CuotasPrestamo>();
		
		try
		{
			ResultSet rs= cn.query("select cp.cuota, cp.idPrestamo, cp.fechaPago, cp.idEstado from cuotasPrestamo as cp where cp.idPrestamo="+idPrestamo);
		    while(rs.next())
			{
		    	CuotasPrestamo cuotasPrestamo = new CuotasPrestamo();
		    	cuotasPrestamo.setCuota(rs.getLong("cp.cuota"));
		    	Prestamo prestamo = new Prestamo();
		    	prestamo.setIdPrestamo(idPrestamo);
		    	cuotasPrestamo.setPrestamo(prestamo);
		    	cuotasPrestamo.setFechaPago(rs.getDate("cp.fechaPago").toLocalDate());
		    	cuotasPrestamo.setEstado(rs.getBoolean("cp.idEstado"));

		    	listaCuotas.add(cuotasPrestamo);
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
		return listaCuotas;
		
	}
	
	public boolean pagarCuota(long idPrestamo, long cuota) {
		boolean estado=true;
		
		cn = new Conexion();
		cn.Open();
		String query = "update cuotasPrestamo set idEstado=0 where idPrestamo="+idPrestamo+" and cuota="+cuota;
		System.out.println(query);
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

	
}
