package excepciones;

//excepcion que toma el a�o de nacimiento ingresado, y se fija que tenga mas de 18 a�os
public class MayorEdadException extends Exception
{
	private static final long serialVersionUID = 1L;

	public MayorEdadException()
	{
		
	}

	//mensaje que va a mostrar si el futuro cliente, es mayor de edad
	@Override
	public String getMessage() 
	{
		return "Debe ser mayor de 18 a�os para poder ser cliente";
	}
	
	
}
