package excepciones;

//excepcion que toma el año de nacimiento ingresado, y se fija que no sea mayor al año actual
//o mayor a 100 años de edad 
public class AnioValidoException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public AnioValidoException()
	{
		
	}

	//mensaje que va a mostrar si se ingreso una fecha valida
	@Override
	public String getMessage() 
	{
		return "El año ingresado no corresponde";
	}

}
