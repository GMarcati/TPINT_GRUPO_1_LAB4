package entidad;

import java.sql.Date;

public class Movimientos {
	
	private long idMovimiento;
	private Cuenta cuenta; //VER SI USAR CLASE "CUENTA"
	private TipoMovimiento tipoMovimiento;
	private Date fechaCreacion;
	private String detalle;
	private double importe;
	private long cuentaDestino;
	
	public Movimientos(long idMovimiento, Cuenta cuenta, TipoMovimiento tipoMovimiento, Date fechaCreacion,
			String detalle, double importe, long cuentaDestino) {
		super();
		this.idMovimiento = idMovimiento;
		this.cuenta = cuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaCreacion = fechaCreacion;
		this.detalle = detalle;
		this.importe = importe;
		this.cuentaDestino = cuentaDestino;
	}
	
	public Movimientos() {
		
	}

	public long getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta idCuenta) {
		this.cuenta = idCuenta;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}


	public long getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(long cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	
	

}
