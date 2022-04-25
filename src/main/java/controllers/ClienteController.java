package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import model.Cliente;
import model.Producto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.ClienteDAO;

/**
 * Servlet implementation class ClienteController
 */
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO cDAO = new ClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("login")) {
			login(request, response);
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia");
		
		Cliente logueado = cDAO.loginCliente(usuario, contrasenia);
		HttpSession session = request.getSession();
		 
		if(logueado != null) {
			session.setAttribute(contrasenia, session);
		}else {
			String msg = "Usuario o Clave son incorrectos.";
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

}
