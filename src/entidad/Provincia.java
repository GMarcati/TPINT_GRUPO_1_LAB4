package entidad;

public class Provincia 
{
	private int idProvincia;
	private String descripcion;
	
	public int getIdProvincia()
	{
		return idProvincia;
	}
	
	public void setIdProvincia(int idProvincia) 
	{
		this.idProvincia = idProvincia;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public Provincia(int idProvincia, String descripcion) 
	{
		super();
		this.idProvincia = idProvincia;
		this.descripcion = descripcion;
	}
	
	public Provincia()
	{
		
	}

	@Override
	public String toString()
	{
		return "Provincia [idProvincia=" + idProvincia + ", descripcion=" + descripcion + "]";
	}
	
	
}
