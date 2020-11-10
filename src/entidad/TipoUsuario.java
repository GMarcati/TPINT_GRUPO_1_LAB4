package entidad;

public class TipoUsuario 
{
	private int idTipoUsuario;
	private String descripcion;
	
	public int getIdTipoUsuario() 
	{
		return idTipoUsuario;
	}
	
	public void setIdTipoUsuario(int idTipoUsuario) 
	{
		this.idTipoUsuario = idTipoUsuario;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	public TipoUsuario()
	{
		
	}

	public TipoUsuario(int idTipoUsuario, String descripcion)
	{
		super();
		this.idTipoUsuario = idTipoUsuario;
		this.descripcion = descripcion;
	}

	@Override
	public String toString()
	{
		return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
}
