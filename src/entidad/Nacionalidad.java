package entidad;

public class Nacionalidad 
{
	private int idNacionalidad;
	private String nacionalidad;
	
	public int getIdNacionalidad() 
	{
		return idNacionalidad;
	}
	
	public void setIdNacionalidad(int idNacionalidad) 
	{
		this.idNacionalidad = idNacionalidad;
	}
	
	public String getDescripcion() 
	{
		return nacionalidad;
	}
	
	public void setDescripcion(String nacionalidad) 
	{
		this.nacionalidad = nacionalidad;
	}

	public Nacionalidad(int idNacionalidad, String nacionalidad)
	{
		super();
		this.idNacionalidad = idNacionalidad;
		this.nacionalidad = nacionalidad;
	}
	
	public Nacionalidad()
	{
		
	}

	@Override
	public String toString()
	{
		return "Nacionalidad [idNacionalidad=" + idNacionalidad + ", nacionalidad=" + nacionalidad + "]";
	}
}
