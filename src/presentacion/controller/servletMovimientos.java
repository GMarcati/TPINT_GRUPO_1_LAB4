package presentacion.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Localidad;
import entidad.Movimientos;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.MovimientosNeg;
import negocio.UsuarioNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.MovimientosNegImpl;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletMovimientos
 */
@WebServlet("/servletMovimientos")
public class servletMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovimientosNeg movimientoNeg = new MovimientosNegImpl();
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletMovimientos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("listarSelects")!=null)
		{
			

			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			request.setAttribute("listaNumeroCuentas", cuentaNeg.listarNumeroCuentas());	
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));//PARA PROBAR USE USUARIO 2 PASAR POR SESISON	
			request.setAttribute("listaTipoMovimiento", movimientoNeg.listarTipoMovimiento());	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Transferir.jsp");
			dispatcher.forward(request, response);
		}
		
		////******** ACEPTAR TRANSFERENCIA *******/////
		if(request.getParameter("btnAceptar")!=null)
		{
			boolean estado=true;
			boolean estadoDescuentoSaldoDestino=true;
			boolean estadoAumentoSaldoOrigen=true;
			
			
			Movimientos movimientos = new Movimientos();
	
			movimientos.setIdCuenta(Long.parseLong(request.getParameter("CuentaUsuario")));
			
			TipoMovimiento tmovimiento = new TipoMovimiento();
			tmovimiento.setIdTipoMovimiento(Integer.parseInt(request.getParameter("TipoMovimiento")));
			movimientos.setTipoMovimiento(tmovimiento);
			
			movimientos.setCuentaDestino(Long.parseLong(request.getParameter("NumeroCuentaDestino")));
			
			LocalDate localDate = LocalDate.now();
			movimientos.setFechaCreacion(java.sql.Date.valueOf(localDate));
			movimientos.setDetalle(request.getParameter("txtDetalle"));
			
			movimientos.setImporte(Double.parseDouble(request.getParameter("txtMonto"))); //VER XQ PUSE INTEGER ES DECIMAL
			
			estado=movimientoNeg.altaMovimento(movimientos);
			
			double importe = Double.parseDouble(request.getParameter("txtMonto"));
			long cuentaOrigen = Long.parseLong(request.getParameter("CuentaUsuario"));
			long cuentaDestino = Long.parseLong(request.getParameter("NumeroCuentaDestino"));
			
			estadoDescuentoSaldoDestino = movimientoNeg.DescontarSaldoCuentaOrigen(cuentaOrigen, importe);
			estadoAumentoSaldoOrigen = movimientoNeg.AumentarSaldoCuentaDestino(cuentaDestino, importe);
			
			//hay que agregar aca el mensaje de exito al agregar usuario
	    	request.setAttribute("estadoMovimiento", estado);
			request.setAttribute("listaNumeroCuentas", cuentaNeg.listarNumeroCuentas());	
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(2));//PARA PROBAR USE USUARIO 2 PASAR POR SESISON	
			request.setAttribute("listaTipoMovimiento", movimientoNeg.listarTipoMovimiento());	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Transferir.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		if(request.getParameter("idCuenta")!=null) {
			
			int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
			long numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
			request.setAttribute("listaMovimientosXCuenta", movimientoNeg.listarMovimientosPorCuenta(idCuenta));
			request.setAttribute("numeroCuenta", numeroCuenta);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/MovimientosCuentaCliente.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		
		//doGet(request, response);
	}

}
