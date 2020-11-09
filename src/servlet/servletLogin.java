package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.usuarioNegocio;

@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public servletLogin() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("hola nico");
		
		int filas=1;
		
		if(request.getParameter("btnIngresar")!=null) //si se presiono el boton ingresar en el login
		{
			String usuario=request.getParameter("txtUsuario");
			String contra=request.getParameter("txtContra"
					+ "");
			if(!usuario.isEmpty())//si el nombre de usuario NO esta vacio
			{
				if(!contra.isEmpty()) //si el campo contraseña NO esta vacio
				{
					System.out.println("atroden");
					
					usuarioNegocio usun= new usuarioNegocio();
					if(usun.ingresar(usuario, contra)==true)
					{
						//PONER MENSAJE DE INGRESO EXITOSO
						RequestDispatcher rd= request.getRequestDispatcher("/PrincipalAdministrador.jsp");
						rd.forward(request, response);//System.out.println("bien");
					}
					else
					{
						//codigo para avisar que se ingreso algun dato mal
						System.out.println("mal");
					}
				}
				else
				{
					//ingrese la contraseña 
					System.out.println("falta contra");
				}
			}
			else
			{
				//ingrese el usuario
				System.out.println("falta usuairo");
			}
		}
		
		System.out.println("chau nico");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
