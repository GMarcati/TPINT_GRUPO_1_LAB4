package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

import dao.PrestamoDao;
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
		    	UsuarioDaoImpl usDaoImpl= new UsuarioDaoImpl();
		    	
		    	pres.setIdPrestamo(rs.getLong(0));
		    	pres.setUsuario(usDaoImpl.obtenerUno(rs.getLong(1)));
		    	pres.setImporteAdevolver(rs.getDouble(2));
		    	pres.setFecha(rs.getDate(3));
		    	pres.setMontoSolicitado(rs.getDouble(4));
		    	pres.setCantidadMeses(rs.getInt(5));
		    	pres.setValorCuota(rs.getDouble(6));
		    	pres.setEstado(rs.getInt(7));
				 
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
	
}
