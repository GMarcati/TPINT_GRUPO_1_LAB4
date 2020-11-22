package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Prestamo;
import entidad.Provincia;
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
			ResultSet rs= cn.query("SELECT * FROM PRESTAMOS");
		    while(rs.next())
			{
		    	Prestamo pres = new Prestamo();
		    	
		    	pres.setIdPrestamo(rs.getLong(0));
		    	Cuenta cuenta = new Cuenta();
		    	cuenta.setIdCuenta(rs.getLong(1));
		    	pres.setCuenta(cuenta);
		    	pres.setImporteAdevolver(rs.getDouble(2));
		    	pres.setFecha(rs.getDate(3));
		    	pres.setMontoSolicitado(rs.getDouble(4));
		    	pres.setCantidadMeses(rs.getInt(5));
		    	pres.setValorCuota(rs.getDouble(6));
		    	pres.setEstado(rs.getBoolean(7));
				 
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

		String query = "Insert into prestamos (idCuenta, importeAdevolver, fecha, montoSolicitado, cantidadMeses, valorCuota, idEstado) values ('"+prestamo.getCuenta().getIdCuenta()+"','"+prestamo.getImporteAdevolver()+"','"+prestamo.getFecha()+"','"+prestamo.getMontoSolicitado()+"', '"+prestamo.getCantidadMeses()+"', '"+prestamo.getValorCuota()+"', 2)";
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
