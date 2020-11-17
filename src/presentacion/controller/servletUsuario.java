package presentacion.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.oa.toa.TOA;

import daoImpl.UsuarioDaoImpl;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;
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
		if(request.getParameter("listadoU")!=null)
		{
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("idModificar")!=null) 
		{
			int id = Integer.parseInt(request.getParameter("idModificar"));
			
			request.setAttribute("usuarioFiltrado", usuarioNeg.obtenerUno(id));	
			request.setAttribute("idModificar", id);	
			request.setAttribute("listaNacionalidad", usuarioNeg.listarNacionalidades());	
			request.setAttribute("listaProvincia", usuarioNeg.listarProvincias());	
			request.setAttribute("listaLocalidad", usuarioNeg.listarLocalidades());	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("btnModificar") != null) 
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
		    
		    
		    /*
		    try {
				usuario.setFechaNac(format.parse(request.getParameter("txtFechaNac")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		    

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
		    
		    //System.out.println(request.getParameter("txtContrasenia")+request.getParameter("txtDni")+request.getParameter("txtCuil")+request.getParameter("txtNombre")+request.getParameter("txtApellido")+request.getParameter("txtSexo")+request.getParameter("nacionalidad")+request.getParameter("provincia")+request.getParameter("localidad")+request.getParameter("txtFechaNac")+request.getParameter("txtDireccion")+request.getParameter("txtMail")+request.getParameter("txtTelefono")  );
		    
		    estado=usuarioNeg.modificar(usuario);
		    
		    request.setAttribute("estadoModificar", estado);
		    request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("idEliminar")!=null) 
		{
			request.setAttribute("usuarioEliminado", usuarioNeg.eliminar(Integer.parseInt(request.getParameter("idEliminar"))));			
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("listarSelects")!=null)
		{
			request.setAttribute("listaNacionalidad", usuarioNeg.listarNacionalidades());	
			request.setAttribute("listaProvincia", usuarioNeg.listarProvincias());	
			request.setAttribute("listaLocalidad", usuarioNeg.listarLocalidades());	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("btnAceptar")!=null)
		{
			boolean estado=true;
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

				usuario.setFechaNac(date);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			usuario.setDireccion(request.getParameter("txtDireccion"));
			usuario.setMail(request.getParameter("txtMail"));
			usuario.setTelefono(request.getParameter("txtTelefono"));
			
			estado=usuarioNeg.altaUsuario(usuario);
			
			//hay que agregar aca el mensaje de exito al agregar usuario
	    	request.setAttribute("estadoUsuario", estado);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCliente.jsp");
			dispatcher.forward(request, response);
			
			/*
			UsuarioDaoImpl udi= new UsuarioDaoImpl();
			
			Usuario usu= new Usuario();
			
			usu.setUsuario(request.getParameter("txtUsuario"));
			usu.setContrasenia(request.getParameter("txtPass"));
			usu.setDni(request.getParameter("txtDni"));
			usu.setCuil(request.getParameter("txtCuil"));
			usu.setNombre(request.getParameter("txtNombre"));
			usu.setApellido(request.getParameter("txtApellido"));
			
			usu.setSexo(request.getParameter("gridRadios"));
			
			//se pasa la fecha ingresada como string a date
			DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
			try
			{
				Date convertido= fecha.parse(request.getParameter("txtFechaNac"));
				usu.setFechaNac(convertido);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			System.out.println("soy: "+ request.getParameter("cbxNacionalidad"));
			
			usu.setDireccion(request.getParameter("txtDireccion"));
			usu.setMail(request.getParameter("txtMail"));
			usu.setTelefono(request.getParameter("txtTelefono"));
			
			if(udi.verificarCampos(usu)==true)
			{
				System.out.println("bien");
			}
			else
			{
				System.out.println("debes completar todos los campos");
			}
			*/
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}
