package presentacion.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.oa.toa.TOA;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import daoImpl.UsuarioDaoImpl;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;
import excepciones.MayorEdadException;
import negocio.UsuarioNeg;
import negocioImpl.UsuarioNegImpl;

@WebServlet("/servletUsuario")
public class servletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
     
    public servletUsuario() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Se valida que el usuario no sea null, de lo contrario redirecciona al jsp Login
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		}
		
		//---------------------------------------------------ADMIN------------------------------------------------------//
		//Cuando desde el menu admin se selecciona la opcion "Listar/Modificar/Dar de baja cliente"
		if(request.getParameter("listadoU")!=null)
		{
			//Se envia el listado de los usuarios al jsp ListaEliminarClientes
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			dispatcher.forward(request, response);
		}
		
		//Cuando desde el menu admin se selecciona la opcion "Alta Cliente"
		if(request.getParameter("listarSelects")!=null)
		{
			//Se envian los listados de nacionalidades, provincias, localidades al jsp AltaCliente para cargar los select
			request.setAttribute("listaNacionalidad", usuarioNeg.listarNacionalidades());	
			request.setAttribute("listaProvincia", usuarioNeg.listarProvincias());	
			request.setAttribute("listaLocalidad", usuarioNeg.listarLocalidades());	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		//Recibo el id a modificar del jsp ListaEliminarClientes
		if(request.getParameter("idModificar")!=null) 
		{
			//Recibo el id pasado por url
			int id = Integer.parseInt(request.getParameter("idModificar"));
			
			//Con el id obtengo el usuario de la bd y se lo paso al jsp ModificarCliente
			request.setAttribute("usuarioFiltrado", usuarioNeg.obtenerUno(id));	
			//Envio el id al jsp ModificarCliente
			request.setAttribute("idModificar", id);	
			//Se envian los listados de nacionalidades, provincias, localidades al jsp ModificarCliente para cargar los select
			request.setAttribute("listaNacionalidad", usuarioNeg.listarNacionalidades());	
			request.setAttribute("listaProvincia", usuarioNeg.listarProvincias());	
			request.setAttribute("listaLocalidad", usuarioNeg.listarLocalidades());	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		//Se recibe el id del cliente a eliminar
		if(request.getParameter("idEliminar")!=null) 
		{
			//Se elimina cliente de la bd
			request.setAttribute("usuarioEliminado", usuarioNeg.eliminar(Integer.parseInt(request.getParameter("idEliminar"))));
			//Se actualiza lista de clientes y se envia al jsp ListaEliminarClientes
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			dispatcher.forward(request, response);
		}
		//--------------------------------------------------------------------------------------------------------------//
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//---------------------------------------------------ADMIN------------------------------------------------------//
		//Se valida que el usuario no sea null, de lo contrario redirecciona al jsp Login
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		}
		
		//Cuando se apreta el boton btnAltaCliente del jsp AltaCliente
		if(request.getParameter("btnAltaCliente")!=null)
		{
			boolean estado=true;
			boolean estadoPass=true;
			Usuario usuario = new Usuario();
			
			usuario.setUsuario(request.getParameter("txtUsuario"));
			usuario.setContrasenia(request.getParameter("txtPass"));
			usuario.setDni(request.getParameter("txtDni"));
			usuario.setCuil(request.getParameter("txtCuil"));
			usuario.setNombre(request.getParameter("txtNombre"));
			usuario.setApellido(request.getParameter("txtApellido"));
			usuario.setSexo(request.getParameter("gridRadios"));
			
			Nacionalidad nacionalidad = new Nacionalidad();
			nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("nacionalidad")));
		    usuario.setNacionalidad(nacionalidad);
			
			Provincia provincia = new Provincia();
			provincia.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));			
		    usuario.setProvincia(provincia);
		    
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(Integer.parseInt(request.getParameter("localidad")));			
		    usuario.setLocalidad(localidad);
			
			try
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				Date date= formatter.parse(request.getParameter("txtFechaNac"));

				if(usuarioNeg.verificarMayorEdad(request.getParameter("txtFechaNac"))==true)
				{
					usuario.setFechaNac(date);
				}
				else
				{
					//aca hay que manejar la excepcion, porque el cliente es menor de edad
				}
			} 
			catch (ParseException | MayorEdadException e) 
			{
				e.printStackTrace();
			}
			
			usuario.setDireccion(request.getParameter("txtDireccion"));
			usuario.setMail(request.getParameter("txtMail"));
			usuario.setTelefono(request.getParameter("txtTelefono"));
			
			if(request.getParameter("txtPass").equals(request.getParameter("txtPassConfirm"))) {
				estado=usuarioNeg.altaUsuario(usuario);
				
				
			} else {
				estado=false;
				estadoPass=false;
			}
			
			request.setAttribute("estadoPass", estadoPass);
	    	request.setAttribute("estadoUsuario", estado);
			request.setAttribute("listaNacionalidad", usuarioNeg.listarNacionalidades());	
			request.setAttribute("listaProvincia", usuarioNeg.listarProvincias());	
			request.setAttribute("listaLocalidad", usuarioNeg.listarLocalidades());	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCliente.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//Cuando se apreta el boton btnModificarCliente del jsp ModificarCliente
		if(request.getParameter("btnModificarCliente") != null) 
		{
			Usuario usuario= new Usuario();
			boolean estado=true;
			
			usuario.setIdUsuario(Integer.parseInt((request.getParameter("txtIdUsuario"))));
			usuario.setContrasenia(request.getParameter("txtContrasenia"));
			usuario.setDni(request.getParameter("txtDni"));
			usuario.setCuil(request.getParameter("txtCuil"));
			usuario.setNombre(request.getParameter("txtNombre"));
			usuario.setApellido(request.getParameter("txtApellido"));
			usuario.setSexo(request.getParameter("gridRadios"));
			
			Nacionalidad nacionalidad = new Nacionalidad();
			nacionalidad.setIdNacionalidad(Integer.parseInt(request.getParameter("nacionalidad")));
		    usuario.setNacionalidad(nacionalidad);
			
			Provincia provincia = new Provincia();
			provincia.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));			
		    usuario.setProvincia(provincia);
		    
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(Integer.parseInt(request.getParameter("localidad")));			
		    usuario.setLocalidad(localidad);
		    
		    
		    try 
		    {
		    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				usuario.setFechaNac(format.parse(request.getParameter("txtFechaNac")));
			} 
		    catch (ParseException e) 
		    {
				e.printStackTrace();
			}

		    usuario.setDireccion(request.getParameter("txtDireccion"));
		    usuario.setMail(request.getParameter("txtMail"));
		    usuario.setTelefono(request.getParameter("txtTelefono"));
		    
		    
		    estado=usuarioNeg.modificar(usuario);
		    
		    request.setAttribute("estadoModificar", estado);
		    request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			dispatcher.forward(request, response);
		}
		//--------------------------------------------------------------------------------------------------------------//
		
		
	}
}
