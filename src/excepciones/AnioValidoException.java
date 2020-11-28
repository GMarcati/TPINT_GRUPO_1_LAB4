package excepciones;

//excepcion que toma el a�o de nacimiento ingresado, y se fija que no sea mayor al a�o actual
//o mayor a 100 a�os de edad 
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
		return "El a�o ingresado no corresponde";
	}

}
