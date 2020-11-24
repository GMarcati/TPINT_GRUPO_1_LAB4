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
		
		Usuario usuarioLog = new Usuario();
		usuarioLog=(Usuario) request.getSession().getAttribute("Usuario");
		if(usuarioLog==null) {
	    	 RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
	         dispatcher.forward(request, response);
		} 
		
		if(request.getParameter("AsignarCuenta") != null) {
			
			
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarCuentaACliente.jsp");
			dispatcher.forward(request, response);
			
		}
		
		if(request.getParameter("listadoC")!=null)
		{
			
			
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");
			dispatcher.forward(request, response);
		
		}
		
		if(request.getParameter("idModificar") != null) {
			
			long idCuenta = Integer.parseInt(request.getParameter("idModificar"));
			request.setAttribute("idModificar", idCuenta);	
			request.setAttribute("cuentaFiltrada", cuentaNeg.obtenerUno(idCuenta));	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
			dispatcher.forward(request, response);
			
			
		}
		
		if(request.getParameter("btnModificar")!=null) {
			
			Cuenta cuenta = new Cuenta();
			boolean estado=true;
			
			cuenta.setIdCuenta(Integer.parseInt(request.getParameter("txtIdCuenta")));
			cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("txtNumeroCuenta")));
			cuenta.setSaldo(Double.parseDouble(request.getParameter("txtSaldo")));
			cuenta.setCBU(Integer.parseInt(request.getParameter("txtCBU")));
			TipoCuenta tipoCuenta = new TipoCuenta();
			tipoCuenta.setIdTipoCuenta(Integer.parseInt(request.getParameter("tipoCuenta")));
			cuenta.setTipoCuenta(tipoCuenta);
			
		    estado=cuentaNeg.editar(cuenta);
		    
		    request.setAttribute("estadoModificar", estado);
		    request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");
			dispatcher.forward(request, response);
			
		}
		

		
		
		//*********************** INSERTAR CUENTA**************************************//
		if(request.getParameter("btnAceptar")!=null) {
			
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
				
				if(estado==true) {
					Movimientos movimiento = new Movimientos();
					Cuenta cuentaAux = new Cuenta();
					cuentaAux = cuentaNeg.obtenerCuentaPorCBU(cuenta.getCBU());
					
					movimiento.setIdCuenta(cuentaAux.getIdCuenta());
					TipoMovimiento tipoMovimiento = new TipoMovimiento();
					tipoMovimiento.setIdTipoMovimiento(1);
					movimiento.setTipoMovimiento(tipoMovimiento);
					movimiento.setFechaCreacion(java.sql.Date.valueOf(localDate));
					movimiento.setDetalle("Apertura de cuenta numero "+cuentaAux.getNumeroCuenta());
					movimiento.setImporte(10000);
					movimiento.setCuentaDestino(cuentaAux.getNumeroCuenta());
					movimientosNeg.altaMovimento(movimiento);
				}
				
		    	//request.setAttribute("listaCat", negCat.obtenerTodos());
		    	request.setAttribute("estadoCuenta", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCuenta.jsp");
				dispatcher.forward(request, response);
		}
		//*********************** LISTAR CUENTA**************************************//		
		if(request.getParameter("btnTodasCuentas")!=null)
			{	
					request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());	
					RequestDispatcher dispatcher1 = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");
					dispatcher1.forward(request, response);
			}
		
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
			
			request.setAttribute("listaUsuario", usuarioNeg.listarUsuarios());
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());
	    	request.setAttribute("estadoAsignarCuenta", estado);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarCuentaACliente.jsp");
			dispatcher.forward(request, response);
			
			}
		
		
		if(request.getParameter("idEliminar") != null) {
			
			boolean estado=true;
			Long id = Long.parseLong(request.getParameter("idEliminar").toString()) ;
			estado=cuentaNeg.borrar(id);
			
			request.setAttribute("listaCuenta", cuentaNeg.listarCuentas());	
			request.setAttribute("estadoEliminar", estado);
			RequestDispatcher rd = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");   
	        rd.forward(request, response);
			
			
		}
		
		if(request.getParameter("PrincipalClienteCuentas")!=null) {
			
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaCuentasCliente.jsp");
			dispatcher.forward(request, response);
			
		}
		
		if(request.getParameter("listaCuentasPrestamos")!=null) {
			
			Usuario usuario = new Usuario();
			usuario=(Usuario) request.getSession().getAttribute("Usuario");
			request.setAttribute("listaCuentasPorUsuario", cuentaNeg.listarCuentasPorUsuario(usuario.getIdUsuario()));
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			dispatcher.forward(request, response);
			
			
		}
		
			
		}
	
	
		
		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
