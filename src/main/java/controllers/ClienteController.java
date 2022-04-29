package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	RequestDispatcher rd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("listar")) {
			listaClientes(request, response);
		}
		if (action.equals("eliminar")) {
			eliminarCliente(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("login")) {
			login(request, response);
		}
	}

	protected void listaClientes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cliente> clientes = cDAO.listarClientes();
		request.setAttribute("clientes", clientes);
		request.getRequestDispatcher("/listarClientes.jsp").forward(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("contrasenia");

		Cliente logueado = cDAO.loginCliente(usuario, password);
		ArrayList<Cliente> clientes = cDAO.listarClientes();

		HttpSession session = request.getSession();

		if (logueado != null) {
			session.setAttribute("logueado", logueado);
			session.setAttribute("clientes", clientes);
			rd = request.getRequestDispatcher("/pedido.jsp");
			rd.forward(request, response);

		} else {
			String msg = "Usuario y/o password son incorrectos";
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

	protected void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int clienteId = Integer.parseInt(request.getParameter("clienteId"));
		boolean productoEliminado = cDAO.eliminarCliente(clienteId);
		request.setAttribute("productos", productoEliminado);
		rd = request.getRequestDispatcher("/ClienteController?action=listar");
		rd.forward(request, response);
	}

	protected void buscarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String queryParametro = request.getParameter("queryCliente");
		ArrayList<Cliente> clientes = cDAO.buscarCliente(queryParametro);
		request.setAttribute("resultado", clientes);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		boolean clienteCreado = cDAO.actualizarCliente(nombre);
		request.setAttribute("clienteCreado", clienteCreado);
		listaClientes(request, response);

	}
}
