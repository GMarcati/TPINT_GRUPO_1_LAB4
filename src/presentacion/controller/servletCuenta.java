package presentacion.controller;

import java.io.IOException;

//import java.sql.Date;
import java.time.LocalDate;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import entidad.Cuenta;
import entidad.Movimientos;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.MovimientosNeg;
import negocio.UsuarioNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.MovimientosNegImpl;
import negocioImpl.UsuarioNegImpl;


/**
 * Servlet implementation class servletCuenta
 */
@WebServlet("/servletCuenta")
public class servletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	MovimientosNeg movimientosNeg = new MovimientosNegImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCuenta() {
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
		
		
		//---------------------------------------------------ADMIN------------------------------------------------------//
		//Cuando desde el menu admin se selecciona la opcion "Asignar cuenta a cliente"
		if(request.getParameter("AsignarCuenta") != null) {
			
			//Se cargan y envian la lista de usuarios y de cuentas al jsp AsignarCuentaACliente
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarCuentaACliente.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//Cuando desde el menu admin se selecciona la opcion "Listar/Modificar/Dar de baja cuenta"
		if(request.getParameter("listadoC")!=null)
		{
			
			//Se carga y envia el listado de cuentas al jsp ListaEliminarCuentas
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");
			dispatcher.forward(request, response);
		
		}
		
		//Recibo el id a modificar del jsp ListaEliminarCuentas
		if(request.getParameter("idModificar") != null) {
			
			//Recibo el id pasado por url
			long idCuenta = Integer.parseInt(request.getParameter("idModificar"));
			//Envio el id al jsp ModificarCuenta
			request.setAttribute("idModificar", idCuenta);
			//Con el id obtengo la cuenta de la bd y se lo paso al jsp ModificarCuenta
			request.setAttribute("cuentaFiltrada", cuentaNeg.obtenerUno(idCuenta));	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
			dispatcher.forward(request, response);
			
			
		}
		
		
		//Se recibe el id de la cuenta a eliminar
		if(request.getParameter("idEliminar") != null) {
			
			boolean estado=true;
			Long id = Long.parseLong(request.getParameter("idEliminar").toString()) ;
			//Se elimina cliente de la bd
			estado=cuentaNeg.borrar(id);
			
			//Se actualiza lista de cuentas y se envia al jsp ListaEliminarCuentas
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());	
			//Se envia el estado para ver si se pudo eliminar o hubo error
			request.setAttribute("estadoEliminar", estado);
			RequestDispatcher rd = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");   
	        rd.forward(request, response);
			
			
		}
		//--------------------------------------------------------------------------------------------------------------//
		
		
		
		//---------------------------------------------------CLIENTE------------------------------------------------------//
		//Cuando desde el menu cliente se selecciona la opcion "Cuentas"
		if(request.getParameter("PrincipalClienteCuentas")!=null) {
			
			Usuario usuario = new Usuario();
			//Se obtiene por session el usuario
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			//Se carga y envia el listado de cuentas por usuario al jsp ListaCuentasCliente
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaCuentasCliente.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//Cuando desde el menu cliente se selecciona la opcion "Solicitar préstamo"
		if(request.getParameter("listaCuentasPrestamos")!=null) {
			
			Usuario usuario = new Usuario();
			//Se obtiene por session el usuario
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			//Se carga y envia el listado de cuentas por usuario al jsp SolicitarPrestamo
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			dispatcher.forward(request, response);
			
			
		}
		//--------------------------------------------------------------------------------------------------------------//
		
			
		}
		
		
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//---------------------------------------------------ADMIN------------------------------------------------------//
		//Se valida que el usuario no sea null, de lo contrario redirecciona al jsp Login
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		} 
		
		//Cuando se apreta el boton btnAltaCuenta del jsp AltaCuenta
		if(request.getParameter("btnAltaCuenta")!=null) {
			
				Cuenta cuenta = new Cuenta();
				boolean estado=true;
				
				cuenta.setNumeroCuenta(cuenta.GenerarNumeroCuenta());
				cuenta.setCBU(cuenta.GenerarNumeroCBU());
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("tipoCuenta")));
				cuenta.setTipoCuenta(tipoCuenta);
				
				LocalDate localDate = LocalDate.now();
				cuenta.setFechaCreacion(java.sql.Date.valueOf(localDate));
				
				cuenta.setSaldo(10000);
				
				estado=cuentaNeg.insertar(cuenta);
				
				//Si se pudo insertar la cuenta entonces se genera el movimiento "Alta de Cuenta"
				if(estado==true) {
					Movimientos movimiento = new Movimientos();
					Cuenta cuentaAux = new Cuenta();
					cuentaAux = cuentaNeg.obtenerCuentaPorCBU(cuenta.getCBU());
					
					movimiento.setCuenta(cuentaAux);
					TipoMovimiento tipoMovimiento = new TipoMovimiento();
					tipoMovimiento.setIdTipoMovimiento(1);
					movimiento.setTipoMovimiento(tipoMovimiento);
					movimiento.setFechaCreacion(java.sql.Date.valueOf(localDate));
					movimiento.setDetalle("Apertura de cuenta numero "+cuentaAux.getNumeroCuenta());
					movimiento.setImporte(10000);
					movimiento.setCuentaDestino(cuentaAux.getNumeroCuenta());
					
					
					
					
					movimientosNeg.altaMovimento(movimiento);
				}
				
				//Se envia el estado para ver si se pudo o no insertar la cuenta al jsp AltaCuenta
		    	request.setAttribute("estadoCuenta", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCuenta.jsp");
				dispatcher.forward(request, response);
		}
		
		//Cuando se apreta el boton btnModificarCuenta del jsp ModificarCuenta
		if(request.getParameter("btnModificarCuenta")!=null) {
			
			Cuenta cuenta = new Cuenta();
			boolean estado=true;
			
			cuenta.setIdCuenta(Integer.parseInt(request.getParameter("txtIdCuenta")));
			cuenta.setNumeroCuenta(Long.parseLong(request.getParameter("txtNumeroCuenta")));
			cuenta.setSaldo(Double.parseDouble(request.getParameter("txtSaldo")));
			cuenta.setCBU(Long.parseLong(request.getParameter("txtCBU")));
			TipoCuenta tipoCuenta = new TipoCuenta();
			tipoCuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("tipoCuenta")));
			cuenta.setTipoCuenta(tipoCuenta);
			
		    estado=cuentaNeg.editar(cuenta);
		    
		    request.setAttribute("estadoModificar", estado);
		    request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//Cuando se apreta el boton btnAsignarCuenta del jsp AsignarCuentaACliente
		if(request.getParameter("btnAsignarCuenta")!=null) {
			boolean estado=true;
			boolean estadoAux=true;
			
			//Verifica que el cliente no tenga mas de 3 cuentas
			estadoAux=cuentaNeg.ContCuentasPorCliente(Integer.parseInt(request.getParameter("txtIdCliente")));
			if(estadoAux==true) {
				estado=cuentaNeg.AsignarCuentaACliente(Integer.parseInt(request.getParameter("txtIdCuenta")), Integer.parseInt(request.getParameter("txtIdCliente")));
			} else {
				estado=false;
			}
			//Se cargan y envian la lista de usuarios y de cuentas al jsp AsignarCuentaACliente 
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
			//Se envia el estado para saber si se pudo o no asignar la cuenta
	    	request.setAttribute("estadoAsignarCuenta", estado);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarCuentaACliente.jsp");
			dispatcher.forward(request, response);
			
			}
		//--------------------------------------------------------------------------------------------------------------//
		
		
	}

}
