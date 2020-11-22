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
		if(request.getParameter("Logoff")!=null) {
			
			request.getSession().removeAttribute("NombreUsuario");
			request.setAttribute("estadoUsuario", estado);
			RequestDispatcher rd= request.getRequestDispatcher("/Principal.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		if(request.getParameter("btnIngresar")!=null) //si se presiono el boton ingresar en el login
		{
			boolean estado=false;
			String usuario=request.getParameter("txtUsuario");
			String contra=request.getParameter("txtContra");
			

				if(usuarioNeg.verificarLogin(usuario, contra)==true)
				{
					if(usuarioNeg.verificarTipoUsuario(usuario)==true)//funcion que selecciona tipo de usuario, si es true, es adm
					{
						
						request.getSession().setAttribute("NombreUsuario", usuario);
						request.setAttribute("estadoCuenta", estado);
						RequestDispatcher rd= request.getRequestDispatcher("/PrincipalAdministrador.jsp");
						rd.forward(request, response);		
					}
					else
					{
						
						request.getSession().setAttribute("Usuario", usuarioNeg.obtenerUsuarioPorNombreUsuario(usuario));
						request.getSession().setAttribute("NombreUsuario", usuario);
						request.setAttribute("estadoCuenta", estado);
						RequestDispatcher rd= request.getRequestDispatcher("/PrincipalCliente.jsp");
						rd.forward(request, response);
					}
				}
				else
				{
					
					
					//Cuando no coincide el usuario y la contraseña
					request.setAttribute("estadoLoginError", estado);
					RequestDispatcher rd= request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
				}

		}
		

		
	}

}
