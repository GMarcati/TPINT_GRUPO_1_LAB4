package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.UsuarioNeg;
import negocioImpl.UsuarioNegImpl;


@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
    
    public servletLogin() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		int filas=1;
		
		/*
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
					
					
					if(usuarioNeg.ingresar(usuario, contra)==true)
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
		*/
		
		if(request.getParameter("btnIngresar")!=null) //si se presiono el boton ingresar en el login
		{
			String usuario=request.getParameter("txtUsuario");
			String contra=request.getParameter("txtContra"
					+ "");
			if(!usuario.isEmpty() && !contra.isEmpty())//si el nombre de usuario NO esta vacio
			{
										
					if(usuarioNeg.ingresar(usuario, contra)==true)
					{
						//PONER MENSAJE DE INGRESO EXITOSO
						RequestDispatcher rd= request.getRequestDispatcher("/PrincipalAdministrador.jsp");
						rd.forward(request, response);//System.out.println("bien");
					}
					else
					{
						//codigo para avisar que se ingreso algun dato mal
						request.getSession().setAttribute("Error", "Usuario o contraseña incorrectos.");
						RequestDispatcher rd= request.getRequestDispatcher("/ErrorLogin.jsp");
						rd.forward(request, response);
					}

			}
			else
			{
				//Cuando no se ingrese nada en los campos de usuario y contraseña
				request.getSession().setAttribute("Error", "Los campos usuario y/o contraseña estan vacios.");
				RequestDispatcher rd= request.getRequestDispatcher("/ErrorLogin.jsp");
				rd.forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
