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
import entidad.TipoCuenta;
import negocio.CuentaNeg;
import negocio.MovimientosNeg;
import negocio.PrestamoNeg;
import negocioImpl.CuentaNegImpl;
import negocioImpl.MovimientosNegImpl;
import negocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class servletInforme
 */
@WebServlet("/servletInforme")
public class servletInforme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	MovimientosNeg movimientoNeg = new MovimientosNegImpl();
	PrestamoNeg prestamoNeg = new PrestamoNegImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletInforme() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnAceptar")!=null) {
			
			boolean estado=true;
			int cantCuenta=0;
			double totSaldoCuenta=0;
			int cantMovimientos=0;
			double totImporteMovimientos=0;
			int cantPrestamoSolicitado=0;
			double totImportePrestamoSolicitado=0;
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date dateIni = null;
			try {
				dateIni = formatter.parse(request.getParameter("txtFechaInicio"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date dateFin = null;
			try {
				dateFin = formatter.parse(request.getParameter("txtFechaFin"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			cantCuenta=cuentaNeg.obtenerCantCuentasCreadasPorFecha(dateIni,dateFin);
			totSaldoCuenta=cuentaNeg.obtenerTotalSaldoCuentasPorFecha(dateIni,dateFin);
			cantMovimientos = movimientoNeg.obtenerCantMovimientosPorFecha(dateIni,dateFin);
			totImporteMovimientos = movimientoNeg.obtenerTotalImporteMovimientosPorFecha(dateIni,dateFin);
			
			cantPrestamoSolicitado = prestamoNeg.obtenerCantPrestamosSolicitadoPorFecha(dateIni,dateFin);
			totImportePrestamoSolicitado = prestamoNeg.obtenerTotalMontoSolicitadoPorFecha(dateIni,dateFin);
			
			
		    request.setAttribute("CantCuenta", cantCuenta);
		    request.setAttribute("TotSaldoCuenta", totSaldoCuenta);
		    request.setAttribute("CantMovimientos", cantMovimientos);
		    request.setAttribute("TotImporteMovimientos", totImporteMovimientos);
		    request.setAttribute("CantPrestamoSolicitado", cantPrestamoSolicitado);
		    request.setAttribute("TotImportePrestamoSolicitado", totImportePrestamoSolicitado);
		    
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/VerInformes.jsp");
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
