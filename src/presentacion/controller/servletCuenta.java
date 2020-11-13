package presentacion.controller;

import java.io.IOException;
//import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;
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
				cuenta.setFechaCreacion(localDate);
				
				cuenta.setSaldo(10000);				
				
				
				estado=cuentaNeg.insertar(cuenta);
				
		    	//request.setAttribute("listaCat", negCat.obtenerTodos());
		    	request.setAttribute("estadoCuenta", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AltaCuenta.jsp");
				dispatcher.forward(request, response);
				
		//*********************** LISTAR CUENTA**************************************//		
		if(request.getParameter("btnTodasCuentas")!=null)
			{	
					request.setAttribute("listaCuenta", cuentaNeg.ListarCuentas());	
					RequestDispatcher dispatcher1 = request.getRequestDispatcher("/ListaEliminarCuentas.jsp");
					dispatcher1.forward(request, response);
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
