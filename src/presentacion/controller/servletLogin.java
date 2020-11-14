package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
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
		boolean estado=true;
				
		if(request.getParameter("btnIngresar")!=null) //si se presiono el boton ingresar en el login
		{
			String usuario=request.getParameter("txtUsuario");
			String contra=request.getParameter("txtContra");
			
			if(!usuario.isEmpty() && !contra.isEmpty())//si el nombre de usuario NO esta vacio
			{
				if(usuarioNeg.verificarLogin(usuario, contra)==true)
				{
					if(usuarioNeg.verificarTipoUsuario(usuario)==true)//funcion que selecciona tipo de usuario, si es true, es adm
					{
						//PONER MENSAJE DE INGRESO EXITOSO
						Usuario.setNombreUsu(usuario);
						request.setAttribute("estadoCuenta", estado);
						RequestDispatcher rd= request.getRequestDispatcher("/PrincipalAdministrador.jsp");
						rd.forward(request, response);		
					}
					else
					{
						//PONER MENSAJE DE INGRESO EXITOSO
						Usuario.setNombreUsu(usuario);
						request.setAttribute("estadoCuenta", estado);
						RequestDispatcher rd= request.getRequestDispatcher("/PrincipalCliente.jsp");
						rd.forward(request, response);
					}
				}
				else
				{
					//Cuando no coincide el usuario y la contraseña
					request.getSession().setAttribute("Error", "El usuario/contraseña es incorrecto.");
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
