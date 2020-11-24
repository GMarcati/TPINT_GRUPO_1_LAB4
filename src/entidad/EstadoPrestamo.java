package entidad;

public class EstadoPrestamo {
	
	private int idEstadoPrestamo;
	private String descripcion;
	
	
	public EstadoPrestamo() {
		
	}
	
	public EstadoPrestamo(int idEstadoPrestamo, String descripcion) {
		super();
		this.idEstadoPrestamo = idEstadoPrestamo;
		this.descripcion = descripcion;
	}
	
	public int getIdEstadoPrestamo() {
		return idEstadoPrestamo;
	}
	public void setIdEstadoPrestamo(int idEstadoPrestamo) {
		this.idEstadoPrestamo = idEstadoPrestamo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "EstadoPrestamo [idEstadoPrestamo=" + idEstadoPrestamo + ", descripcion=" + descripcion + "]";
	}
	
	

}
