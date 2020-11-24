package presentacion.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.EstadoPrestamo;
import entidad.Movimientos;
import entidad.Prestamo;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.MovimientosNeg;
import negocio.PrestamoNeg;
import negocio.UsuarioNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.MovimientosNegImpl;
import negocioImpl.PrestamoNegImpl;
import negocioImpl.UsuarioNegImpl;


@WebServlet("/servletPrestamo")
public class servletPrestamo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	MovimientosNeg movimientoNeg = new MovimientosNegImpl();
	
    public servletPrestamo() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		}
		*/
		
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
			LocalDate localDate = LocalDate.now();
			
			cuenta.setIdCuenta(Integer.parseInt(request.getParameter("idCuentaDestino")));
			prestamo.setCuenta(cuenta);
			prestamo.setUsuario(usuario);
			Double importeAdevolver = Double.parseDouble(request.getParameter("txtMontoSolicitado"))+Double.parseDouble(request.getParameter("txtMontoSolicitado"))*0.12;
			prestamo.setImporteAdevolver(importeAdevolver);
			prestamo.setFecha(java.sql.Date.valueOf(localDate));
			prestamo.setMontoSolicitado(Double.parseDouble(request.getParameter("txtMontoSolicitado")));
			prestamo.setCantidadMeses(Integer.parseInt(request.getParameter("selectCantidadCuotas")));
			
			Double cuota = importeAdevolver/Integer.parseInt(request.getParameter("selectCantidadCuotas"));
			prestamo.setValorCuota(cuota);
			EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
			estadoPrestamo.setIdEstadoPrestamo(1);
			prestamo.setEstadoPrestamo(estadoPrestamo);
			
			estado=prestamoNeg.solicitudPrestamo(prestamo);
			
	    	request.setAttribute("estadoPrestamo", estado);
	    	request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			dispatcher.forward(request, response);
			
		}
		
		if(request.getParameter("listadoPrestamosPorUsuario")!=null) {
			
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamosPorCliente(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamosCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("idPrestamoCuota")!=null) {
			
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			long idPrestamo = Integer.parseInt(request.getParameter("idPrestamoCuota"));
			long cuotas = Integer.parseInt(request.getParameter("CuotasAPagar"));
			Prestamo prestamo = new Prestamo();
			prestamo=prestamoNeg.obtenerPrestamoPorId(idPrestamo);
			
			request.setAttribute("cuotasApagar", cuotas);
			request.setAttribute("prestamo", prestamo);
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamosPorCliente(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PagarPrestamo.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("btnPagarCuota")!=null) {
			
			boolean estadoPrestamo=true;
			boolean estadoMovimiento=true;
			boolean estadoDescuentoSaldoDestino=true;
			double saldo = 0;
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			long idPrestamo = Long.parseLong(request.getParameter("txtIdPrestamo"));
			long idCuentaOrigen = Long.parseLong(request.getParameter("CuentaUsuario"));
			
			saldo=cuentaNeg.obtenerSaldoPorIdCuenta(idCuentaOrigen);
			
			Prestamo prestamo = new Prestamo();
			prestamo=prestamoNeg.obtenerPrestamoPorId(idPrestamo);
			
			Movimientos movimiento = new Movimientos();
			movimiento.setIdCuenta(idCuentaOrigen);
			TipoMovimiento tmovimiento = new TipoMovimiento();
			tmovimiento.setIdTipoMovimiento(3);
			movimiento.setTipoMovimiento(tmovimiento);
			LocalDate localDate = LocalDate.now();
			movimiento.setFechaCreacion(java.sql.Date.valueOf(localDate));
			movimiento.setDetalle("Pago de cuota");
			movimiento.setImporte(Double.parseDouble(request.getParameter("txtCuota")));
			movimiento.setCuentaDestino(1);
			
			if(saldo>=prestamo.getValorCuota()) {
				
				estadoPrestamo=prestamoNeg.pagarCuota(idPrestamo);
				estadoDescuentoSaldoDestino = movimientoNeg.DescontarSaldoCuentaOrigen(idCuentaOrigen, prestamo.getValorCuota());
				estadoMovimiento=movimientoNeg.altaMovimento(movimiento);
				
				
			} else {
				
				estadoPrestamo=false;
				estadoDescuentoSaldoDestino=false;
			}
			
			
			request.setAttribute("estadoPrestamo", estadoPrestamo);
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
			request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamosPorCliente(usuario.getIdUsuario()));
	    	request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamosPorCliente(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamosCliente.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
