package daoImpl;

import java.sql.Date;
import java.sql.ResultSet;
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

}
