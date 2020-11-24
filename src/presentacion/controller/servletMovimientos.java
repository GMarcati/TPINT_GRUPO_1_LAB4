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
		
		//Se valida que el usuario no sea null, de lo contrario redirecciona al jsp Login
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		}
		
		//---------------------------------------------------CLIENTE------------------------------------------------------//
		//Cuando desde el menu cliente se selecciona la opcion "Transferir"
		if(request.getParameter("listarSelects")!=null)
		{
			
			Usuario usuario = new Usuario();
			//Se obtiene el usuario a traves de session
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			//Se envian los listados de cuentas por usuario al jsp Transferir para cargar los select	
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Transferir.jsp");
			dispatcher.forward(request, response);
		}
		
		

		//Cuando desde el jsp ListarCuentasCliente se selecciona el boton Movimientos y se envia el idCuenta & el numero de cuenta por url
		if(request.getParameter("idCuenta")!=null) {
			
			int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
			long numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
			//Se carga y envia el listado de movimiento por cuenta
			request.setAttribute("listaMovimientosXCuenta", movimientoNeg.listarMovimientosPorCuenta(idCuenta));
			//Se envia el numero de cuenta
			request.setAttribute("numeroCuenta", numeroCuenta);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/MovimientosCuentaCliente.jsp");
			dispatcher.forward(request, response);
		}
		//----------------------------------------------------------------------------------------------------------------//
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Se valida que el usuario no sea null, de lo contrario redirecciona al jsp Login
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		}
		
	 	//---------------------------------------------------CLIENTE------------------------------------------------------//
		//Cuando se apreta el boton btnTransferir del jsp Transferir
		if(request.getParameter("btnTransferir")!=null)
		{
			boolean estado=true;
			boolean estadoDescuentoSaldoDestino=true;
			boolean estadoAumentoSaldoOrigen=true;
			double saldo = 0;
			
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			
			Movimientos movimientos = new Movimientos();
	
			movimientos.setIdCuenta(Long.parseLong(request.getParameter("CuentaUsuario")));
			
			TipoMovimiento tmovimiento = new TipoMovimiento();
			tmovimiento.setIdTipoMovimiento(4);
			movimientos.setTipoMovimiento(tmovimiento);
			
			
			long cbu=Long.parseLong(request.getParameter("txtCBU"));
			Cuenta cuentaDestino= new Cuenta();
			cuentaDestino = cuentaNeg.obtenerCuentaPorCBU(cbu);
			movimientos.setCuentaDestino(cuentaDestino.getNumeroCuenta());
			
			LocalDate localDate = LocalDate.now();
			movimientos.setFechaCreacion(java.sql.Date.valueOf(localDate));
			movimientos.setDetalle(request.getParameter("txtDetalle"));
			
			movimientos.setImporte(Double.parseDouble(request.getParameter("txtMonto")));
			
			
			double importe = Double.parseDouble(request.getParameter("txtMonto"));
			long idCuentaOrigen = Long.parseLong(request.getParameter("CuentaUsuario"));
			
			//Se obtiene el saldo de la cuenta
			saldo=cuentaNeg.obtenerSaldoPorIdCuenta(idCuentaOrigen);
			
			//Se verifica que tenga saldo disponible
			if(saldo >= importe && cuentaDestino.getIdCuenta() != 0) {
				
				estadoDescuentoSaldoDestino = movimientoNeg.DescontarSaldoCuentaOrigen(idCuentaOrigen, importe);
				estadoAumentoSaldoOrigen = movimientoNeg.AumentarSaldoCuentaDestino(cuentaDestino.getNumeroCuenta(), importe);
				estado=movimientoNeg.altaMovimento(movimientos);
			} else {
				estadoDescuentoSaldoDestino=false;
				estadoAumentoSaldoOrigen=false;
				estado=false;
			}
			
			//Se envia el estado para ver si se pudo realizar la transferencia
	    	request.setAttribute("estadoTransferencia", estado);
	    	//Se carga y envia el listado de cuentas por usuario al jsp Transferir
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Transferir.jsp");
			dispatcher.forward(request, response);
			
		}
	 	//----------------------------------------------------------------------------------------------------------------//
		
		
	}

}
