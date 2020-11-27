package presentacion.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.CuotasPrestamo;
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
		//Se valida que el usuario no sea null, de lo contrario redirecciona al jsp Login
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		}
		
		
		//---------------------------------------------------CLIENTE------------------------------------------------------//

		
		//Cuando desde el menu cliente se selecciona la opcion "Ver préstamos adquiridos" 
		if(request.getParameter("listadoPrestamosPorUsuario")!=null) {
			
			Usuario usuario = new Usuario();
			//Se obtiene el usuario por session
			usuario=(Usuario) request.getSession().getAttribute("Usuario");

			//Se carga y envia el listado de prestamos por usuario al jsp ListaPrestamosCliente
	    	request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamosPorCliente(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamosCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		//Recibo el id del prestamo a pagar del jsp ListaPrestamosCliente
		if(request.getParameter("idPrestamoCuota")!=null) {
			
			Usuario usuario = new Usuario();
			//Se obtiene el usuario por session
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			long idPrestamo = Integer.parseInt(request.getParameter("idPrestamoCuota"));
			
			
			Prestamo prestamo = new Prestamo();
			prestamo=prestamoNeg.obtenerPrestamoPorId(idPrestamo);
			
			//Se envian las cuotas a pagar, el prestamo, listado de cuentas por usuario y la lista de prestamo
			request.setAttribute("prestamo", prestamo);
			request.setAttribute("listaCuotas", prestamoNeg.listarCuotas(idPrestamo));
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PagarPrestamo.jsp");
			dispatcher.forward(request, response);
		}
		

		
		//----------------------------------------------------------------------------------------------------------------//
		
		//---------------------------------------------------ADMIN------------------------------------------------------//
		if(request.getParameter("listadoPrestamosxAutorizar")!=null) {
			
			
			request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamos());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamos.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//Cuando se apreta el boton para aprobar el prestamo
		if(request.getParameter("idPrestamoAprobar")!=null) {
			
			long idPrestamo = Integer.parseInt(request.getParameter("idPrestamoAprobar"));
			long idCuenta = Integer.parseInt(request.getParameter("idCuentaAprobada"));
			boolean estadoAprobar=true;
			
			estadoAprobar=prestamoNeg.aceptarPrestamo(idPrestamo);
			
			if(estadoAprobar==true) {
				Movimientos movimiento = new Movimientos();
				LocalDate localDate = LocalDate.now();
				Prestamo prestamo = new Prestamo();
				prestamo=prestamoNeg.obtenerPrestamoPorId(idPrestamo);
				Cuenta cuenta = new Cuenta();
				cuenta=cuentaNeg.obtenerUno(idCuenta);
				
				movimiento.setCuenta(cuenta);
				TipoMovimiento tipoMovimiento = new TipoMovimiento();
				long meses = prestamo.getCantidadMeses();
				int cuota=1;
				//Se generan las cuotas
				while (meses!=0) {
					CuotasPrestamo cuotasPrestamo = new CuotasPrestamo();
					cuotasPrestamo.setCuota(cuota);
					cuotasPrestamo.setPrestamo(prestamo);
					cuotasPrestamo.setFechaPago(localDate.plusMonths(cuota));
					prestamoNeg.generarCuotas(cuotasPrestamo);
					cuota++;
					meses--;
				}
				
				tipoMovimiento.setIdTipoMovimiento(2);
				movimiento.setTipoMovimiento(tipoMovimiento);
				movimiento.setFechaCreacion(java.sql.Date.valueOf(localDate));
				movimiento.setDetalle("Importe a devolver con intereses: "+prestamo.getImporteAdevolver());
				movimiento.setImporte(prestamo.getMontoSolicitado());
				movimiento.setCuentaDestino(cuenta.getNumeroCuenta());
				movimientoNeg.AumentarSaldoCuentaDestino(cuenta.getNumeroCuenta(), prestamo.getMontoSolicitado());
				movimientoNeg.altaMovimento(movimiento);
			}
			
			request.setAttribute("estadoAprobar", estadoAprobar);
			request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamos());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamos.jsp");
			dispatcher.forward(request, response);
		}
		
		//Cuando se apreta el boton para rechazar el prestamo
		if(request.getParameter("idPrestamoRechazar")!=null) {
			
			long idPrestamo = Integer.parseInt(request.getParameter("idPrestamoRechazar"));
			boolean estadoRechazar=true;
			estadoRechazar=prestamoNeg.rechazarPrestamo(idPrestamo);
			request.setAttribute("estadoRechazar", estadoRechazar);
			request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamos());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamos.jsp");
			dispatcher.forward(request, response);
		}
		
		//----------------------------------------------------------------------------------------------------------------//
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		//---------------------------------------------------CLIENTE------------------------------------------------------//

		
		//Cuando se apreta el boton btnPagarCuota del jsp PagarPrestamo
		if(request.getParameter("btnPagarCuota")!=null) {
			
			
			boolean estadoPrestamo=true;
			boolean estadoMovimiento=true;
			boolean estadoDescuentoSaldoDestino=true;
			double saldo = 0;
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			int idPrestamo = Integer.parseInt(request.getParameter("txtIdPrestamo"));
			int idCuentaOrigen = Integer.parseInt(request.getParameter("CuentaUsuario"));
			int idCuota = Integer.parseInt(request.getParameter("idCuota"));
			//System.out.println(idCuota+" - "+request.getParameter("idCuota")+" - "+ idCuentaOrigen);
			saldo=cuentaNeg.obtenerSaldoPorIdCuenta(idCuentaOrigen);
			
			Prestamo prestamo = new Prestamo();
			prestamo=prestamoNeg.obtenerPrestamoPorId(idPrestamo);
			
			Movimientos movimiento = new Movimientos();
			Cuenta cuenta = new Cuenta();
			cuenta.setIdCuenta(idCuentaOrigen);
			movimiento.setCuenta(cuenta);
			TipoMovimiento tmovimiento = new TipoMovimiento();
			tmovimiento.setIdTipoMovimiento(3);
			movimiento.setTipoMovimiento(tmovimiento);
			LocalDate localDate = LocalDate.now();
			movimiento.setFechaCreacion(java.sql.Date.valueOf(localDate));
			movimiento.setDetalle("Pago de cuota");
			movimiento.setImporte(Double.parseDouble(request.getParameter("txtValorCuota")));
			movimiento.setCuentaDestino(1);
			//Se verifica si el saldo es mayor o igual al valor de la cuota a pagar del prestamo
			if(saldo>=prestamo.getValorCuota()) {
				estadoPrestamo=prestamoNeg.pagarCuota(idPrestamo, idCuota);
				
				estadoDescuentoSaldoDestino = movimientoNeg.DescontarSaldoCuentaOrigen(idCuentaOrigen, prestamo.getValorCuota());
				estadoMovimiento=movimientoNeg.altaMovimento(movimiento);
				
				
			} else {
				
				estadoPrestamo=false;
				estadoDescuentoSaldoDestino=false;
			}
			
			
			request.setAttribute("estadoPrestamo", estadoPrestamo);
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
			request.setAttribute("listaPrestamos", prestamoNeg.listarPrestamosPorCliente(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaPrestamosCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		//Cuando se apreta el boton btnSolicitar del jsp SolicitarPrestamo
		if(request.getParameter("btnSolicitar")!=null) {
			
			Prestamo prestamo = new Prestamo();
			boolean estado=true;
			double intereses=0;
			Cuenta cuenta = new Cuenta();
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			LocalDate localDate = LocalDate.now();
			
			//Depende de las cuotas que seleccione el interes va a variar
			switch (Integer.parseInt(request.getParameter("selectCantidadCuotas"))) {
			case 3:
				intereses=0.30;
				break;
			case 6:
				intereses=0.60;
				break;
			case 12:
				intereses=1.20;
				break;
			case 18:
				intereses=1.80;
				break;
			case 24:
				intereses=2.40;
				break;

			default:
				intereses=0.10;
				break;
			}
			
			cuenta.setIdCuenta(Integer.parseInt(request.getParameter("idCuentaDestino")));
			prestamo.setCuenta(cuenta);
			prestamo.setUsuario(usuario);
			Double importeAdevolver = Double.parseDouble(request.getParameter("txtMontoSolicitado"))+Double.parseDouble(request.getParameter("txtMontoSolicitado"))*intereses;
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
		
		//----------------------------------------------------------------------------------------------------------------//
		
		

		
	}

}
