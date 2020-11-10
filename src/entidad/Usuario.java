package entidad;

import java.sql.Date;

public class Usuario 
{
	private int idUsuario;
	private String usuario;
	private String contrasenia;
	private TipoUsuario tipoUsuario;
	private int dni;
	private int cuil;
	private String nombre;
	private String apellido;
	private String sexo;
	private Date fechaNac;
	private String direccion;
	private Localidad localidad;
	private Nacionalidad nacionalidad;
	private Provincia provincia;
	private String mail;
	private String telefono;
	private Estado estado;
	
	//getters y setters
	public int getIdUsuario() 
	{
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) 
	{
		this.idUsuario = idUsuario;
	}
	
	public String getUsuario() 
	{
		return usuario;
	}
	
	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}
	
	public String getContrasenia() 
	{
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) 
	{
		this.contrasenia = contrasenia;
	}
	
	public TipoUsuario getTipoUsuario() 
	{
		return tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}
	
	public int getDni() 
	{
		return dni;
	}
	
	public void setDni(int dni)
	{
		this.dni = dni;
	}
	
	public int getCuil() 
	{
		return cuil;
	}
	
	public void setCuil(int cuil) 
	{
		this.cuil = cuil;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getApellido() 
	{
		return apellido;
	}
	
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	
	public String getSexo() 
	{
		return sexo;
	}
	
	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}
	
	public Date getFechaNac() 
	{
		return fechaNac;
	}
	
	public void setFechaNac(Date fechaNac) 
	{
		this.fechaNac = fechaNac;
	}
	
	public String getDireccion() 
	{
		return direccion;
	}
	
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	
	public Localidad getLocalidad() 
	{
		return localidad;
	}
	
	public void setLocalidad(Localidad localidad) 
	{
		this.localidad = localidad;
	}
	
	public Nacionalidad getNacionalidad()
	{
		return nacionalidad;
	}
	
	public void setNacionalidad(Nacionalidad nacionalidad) 
	{
		this.nacionalidad = nacionalidad;
	}
	
	public Provincia getProvincia() 
	{
		return provincia;
	}
	
	public void setProvincia(Provincia provincia) 
	{
		this.provincia = provincia;
	}
	
	public String getMail() 
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	public String getTelefono()
	{
		return telefono;
	}
	
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	
	public Estado getEstado() 
	{
		return estado;
	}
	
	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	//constructores
	public Usuario(int idUsuario, String usuario, String contrasenia, TipoUsuario tipoUsuario, int dni, int cuil,
			String nombre, String apellido, String sexo, Date fechaNac, String direccion, Localidad localidad,
			Nacionalidad nacionalidad, Provincia provincia, String mail, String telefono, Estado estado)
	{
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.tipoUsuario = tipoUsuario;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.direccion = direccion;
		this.localidad = localidad;
		this.nacionalidad = nacionalidad;
		this.provincia = provincia;
		this.mail = mail;
		this.telefono = telefono;
		this.estado = estado;
	}
	
	public Usuario()
	{
		
	}

	@Override
	public String toString() 
	{
		return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasenia=" + contrasenia
				+ ", tipoUsuario=" + tipoUsuario + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", sexo=" + sexo + ", fechaNac=" + fechaNac + ", direccion=" + direccion
				+ ", localidad=" + localidad + ", nacionalidad=" + nacionalidad + ", provincia=" + provincia + ", mail="
				+ mail + ", telefono=" + telefono + ", estado=" + estado + "]";
	}
}
