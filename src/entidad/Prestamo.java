package entidad;

import java.util.Date;

public class Prestamo 
{
	private long idPrestamo;
	private Cuenta cuenta;
	private double importeAdevolver;
	private Date fecha;
	private double montoSolicitado;
	private int cantidadMeses;
	private double valorCuota;
	private String estado; 
	
	public long getIdPrestamo()
	{
		return idPrestamo;
	}
	
	public void setIdPrestamo(long idPrestamo) 
	{
		this.idPrestamo = idPrestamo;
	}
	
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	
	public String getEstado()
	{
		return estado;
	}
	
	public void setEstado(String estado) 
	{
		this.estado = estado;
	}
	
	public Prestamo()
	{
		
	}

	public Prestamo(long idPrestamo, Cuenta cuenta, float importeAdevolver, Date fecha, float montoSolicitado,
			int cantidadMeses, float valorCuota, String estado) 
	{
		super();
		this.idPrestamo = idPrestamo;
		this.cuenta = cuenta;
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
		return "Prestamo [idPrestamo=" + idPrestamo + ", cuenta=" + cuenta + ", importeAdevolver=" + importeAdevolver
				+ ", fecha=" + fecha + ", montoSolicitado=" + montoSolicitado + ", cantidadMeses=" + cantidadMeses
				+ ", valorCuota=" + valorCuota + ", estado=" + estado + "]";
	}
	
}
