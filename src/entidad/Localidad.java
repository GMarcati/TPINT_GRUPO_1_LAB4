package entidad;

public class Localidad 
{
	private int idLocalidad;
	private String descripcion;
	
	public int getIdLocalidad() 
	{
		return idLocalidad;
	}
	
	public void setIdLocalidad(int idLocalidad) 
	{
		this.idLocalidad = idLocalidad;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public Localidad(int idLocalidad, String descripcion)
	{
		super();
		this.idLocalidad = idLocalidad;
		this.descripcion = descripcion;
	}
	
	public Localidad()
	{
		
	}

	@Override
	public String toString() 
	{
		return "Localidad [idLocalidad=" + idLocalidad + ", descripcion=" + descripcion + "]";
	}
	
	
}
