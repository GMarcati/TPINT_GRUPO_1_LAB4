package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servletPrestamo")
public class servletPrestamo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
