package presentacion.controller;

import java.io.IOException;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocioImpl.CuentaNegImpl;


/**
 * Servlet implementation class servletCuenta
 */
@WebServlet("/servletCuenta")
public class servletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CuentaNeg cuentaNeg = new CuentaNegImpl();
       
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
			
			
				
			estado=cuentaNeg.AsignarCuentaACliente(Integer.parseInt(request.getParameter("txtIdCuenta")), Integer.parseInt(request.getParameter("txtIdCliente")));
			
			
			
			
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
		
			
		}
	
	
		
		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
