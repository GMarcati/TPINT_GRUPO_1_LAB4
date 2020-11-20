package entidad;

import java.util.Date;

public class Prestamo 
{
	private long idPrestamo;
	private Usuario usuario;
	private double importeAdevolver;
	private Date fecha;
	private double montoSolicitado;
	private int cantidadMeses;
	private double valorCuota;
	private int estado; // si es 1, se encuentra pendiente. Si es 0, rechazado. Si es 2, aprobado
	
	public long getIdPrestamo()
	{
		return idPrestamo;
	}
	
	public void setIdPrestamo(long idPrestamo) 
	{
		this.idPrestamo = idPrestamo;
	}
	
	public Usuario getUsuario() 
	{
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}
	
	public double getImporteAdevolver() 
	{
		return importeAdevolver;
	}
	
	public void setImporteAdevolver(double importeAdevolver)
	{
		this.importeAdevolver = importeAdevolver;
	}
	
	public Date getFecha() 
	{
		return fecha;
	}
	
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}
	
	public double getMontoSolicitado()
	{
		return montoSolicitado;
	}
	
	public void setMontoSolicitado(double montoSolicitado)
	{
		this.montoSolicitado = montoSolicitado;
	}
	
	public int getCantidadMeses() 
	{
		return cantidadMeses;
	}
	
	public void setCantidadMeses(int cantidadMeses)
	{
		this.cantidadMeses = cantidadMeses;
	}
	
	public double getValorCuota() 
	{
		return valorCuota;
	}
	
	public void setValorCuota(double valorCuota) 
	{
		this.valorCuota = valorCuota;
	}
	
	public int getEstado()
	{
		return estado;
	}
	
	public void setEstado(int estado) 
	{
		this.estado = estado;
	}
	
	public Prestamo()
	{
		
	}

	public Prestamo(long idPrestamo, Usuario usuario, float importeAdevolver, Date fecha, float montoSolicitado,
			int cantidadMeses, float valorCuota, int estado) 
	{
		super();
		this.idPrestamo = idPrestamo;
		this.usuario = usuario;
		this.importeAdevolver = importeAdevolver;
		this.fecha = fecha;
		this.montoSolicitado = montoSolicitado;
		this.cantidadMeses = cantidadMeses;
		this.valorCuota = valorCuota;
		this.estado = estado;
	}

	@Override
	public String toString() 
	{
		return "Prestamo [idPrestamo=" + idPrestamo + ", usuario=" + usuario + ", importeAdevolver=" + importeAdevolver
				+ ", fecha=" + fecha + ", montoSolicitado=" + montoSolicitado + ", cantidadMeses=" + cantidadMeses
				+ ", valorCuota=" + valorCuota + ", estado=" + estado + "]";
	}
	
}
