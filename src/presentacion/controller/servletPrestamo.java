package presentacion.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocio.UsuarioNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.PrestamoNegImpl;
import negocioImpl.UsuarioNegImpl;


@WebServlet("/servletPrestamo")
public class servletPrestamo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	CuentaNeg cuentaNeg = new CuentaNegImpl();
   
    public servletPrestamo() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("listadoP")!=null)
		{
			//request.setAttribute("listaPrestamo", prestamoNeg.listarPrestamos());	
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarClientes.jsp");
			//dispatcher.forward(request, response);
		}
		
		if(request.getParameter("btnSolicitar")!=null) {
			
			Prestamo prestamo = new Prestamo();
			boolean estado=true;
			Cuenta cuenta = new Cuenta();
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			
			cuenta.setIdCuenta(Integer.parseInt(request.getParameter("idCuentaDestino")));
			prestamo.setCuenta(cuenta);
			Double importeAdevolver = (double) Integer.parseInt(request.getParameter("txtMontoSolicitado"))+Integer.parseInt(request.getParameter("txtMontoSolicitado"))*0.12;
			prestamo.setImporteAdevolver(importeAdevolver);
			LocalDate localDate = LocalDate.now();
			prestamo.setFecha(java.sql.Date.valueOf(localDate));
			prestamo.setMontoSolicitado(Integer.parseInt(request.getParameter("txtMontoSolicitado")));
			prestamo.setCantidadMeses(Integer.parseInt(request.getParameter("selectCantidadCuotas")));
			
			Double cuota = importeAdevolver/Integer.parseInt(request.getParameter("selectCantidadCuotas"));
			prestamo.setValorCuota(cuota);
			
			estado=prestamoNeg.solicitudPrestamo(prestamo);
			
	    	request.setAttribute("estadoPrestamo", estado);
	    	request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
