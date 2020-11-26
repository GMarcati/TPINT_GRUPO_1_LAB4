package daoImpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientosDao;
import entidad.Movimientos;
import entidad.TipoMovimiento;

public class MovimientosDaoImpl implements MovimientosDao {

	
	private Conexion cn;
	
	public MovimientosDaoImpl(){
		
	}
	
	@Override ///*********** LISTA DE TODOS LOS MOVIMIENTO POR IDCUENTA *******////
	public List<Movimientos> listarMovimientosPorCuenta(long id) {
		
		cn = new Conexion();
		cn.Open();
		 List<Movimientos> listaM = new ArrayList<Movimientos>();
		 try
		 {
			 ResultSet rs= cn.query("select m.idMovimiento, m.idCuenta, m.idTipoMovimiento, tm.descripcion, m.fecha, m.detalle, m.importe, m.cuentaDestino " + 
			 						"from movimientos as m " + 
			 						"inner join tipoMovimientos as tm on m.idTipoMovimiento = tm.idTipoMovimiento " + 
			 						"where m.idCuenta="+id);
			 while(rs.next())
			 {
				 
				 
				 
				 Movimientos movimientos = new Movimientos();
				 movimientos.setIdMovimiento(rs.getLong("m.idMovimiento"));
				 movimientos.setIdCuenta(rs.getLong("m.idCuenta"));
				 
				 TipoMovimiento tmovimiento = new TipoMovimiento(); 
				 tmovimiento.setIdTipoMovimiento(rs.getInt("m.idTipoMovimiento"));
				 tmovimiento.setDescripcion(rs.getString("tm.descripcion"));
				 movimientos.setTipoMovimiento(tmovimiento);
				 
				 movimientos.setFechaCreacion(rs.getDate("m.fecha"));
				 movimientos.setDetalle(rs.getString("m.detalle"));
				 movimientos.setImporte(rs.getDouble("m.importe"));
				 movimientos.setCuentaDestino(rs.getLong("m.cuentaDestino"));
				  
				 listaM.add(movimientos);
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
		
		return listaM;
	}
	
	

	@Override
	public List<TipoMovimiento> listarTipoMovimiento() {
		cn = new Conexion();
		cn.Open();
		 List<TipoMovimiento> listaTM = new ArrayList<TipoMovimiento>();
		 try
		 {
			 ResultSet rs= cn.query("select * from tipoMovimientos");
			 while(rs.next())
			 {
				 
				 TipoMovimiento tipoMovimiento = new TipoMovimiento();
				 tipoMovimiento.setIdTipoMovimiento(rs.getInt("idTipoMovimiento"));
				 tipoMovimiento.setDescripcion(rs.getString("descripcion"));
				 
				 listaTM.add(tipoMovimiento);
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
		
		return listaTM;
	}
	

	@Override
	public boolean altaMovimento(Movimientos movimiento) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	
		String query = "INSERT INTO movimientos(idCuenta, idTipoMovimiento, fecha, detalle, importe, cuentaDestino)  VALUES ('"+movimiento.getIdCuenta()+"','"+movimiento.getTipoMovimiento().getIdTipoMovimiento()+"','"+movimiento.getFechaCreacion()+"','"+movimiento.getDetalle()+"','"+movimiento.getImporte()+"','"+movimiento.getCuentaDestino()+"')";
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
	
	public boolean AumentarSaldoCuentaDestino(long numeroCuentaDestino, double importe) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "Update cuentas set saldo=saldo+"+importe+" where numeroCuenta="+numeroCuentaDestino;
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
	
	public boolean DescontarSaldoCuentaOrigen(long idCuentaOrigen, double importe) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "Update cuentas set saldo=saldo-"+importe+" where idCuenta="+idCuentaOrigen;
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

	@Override
	public int obtenerCantMovientosPorFecha(java.util.Date fechaIni, java.util.Date fechaFin) {
		cn = new Conexion();
		cn.Open();
		
		int cant = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString1 = format.format(fechaIni);
		String dateString2 = format.format(fechaFin);
		
		 try
		 {
			 ResultSet rs = cn.query("SELECT count(*) as CantMovimento FROM movimientos WHERE fecha BETWEEN '" + dateString1 + "' AND '" + dateString2 + "'");
			 //System.out.print(rs);
			 while(rs.next()) {
				 cant = rs.getInt("CantMovimento");
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
	public double obtenerTotalImportesMovimientosPorFecha(java.util.Date fechaIni, java.util.Date fechaFin) {
		cn = new Conexion();
		cn.Open();
		
		double importeTot = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString1 = format.format(fechaIni);
		String dateString2 = format.format(fechaFin);
		
		 try
		 {
			 ResultSet rs = cn.query("SELECT SUM(importe) as TotImporte FROM movimientos WHERE fecha BETWEEN '" + dateString1 + "' AND '" + dateString2 + "'");
			 //System.out.print(rs);
			 while(rs.next()) {
				 importeTot = rs.getDouble("TotImporte");
				 //System.out.print(importeTot);
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
		 return importeTot;
	}
	

}
