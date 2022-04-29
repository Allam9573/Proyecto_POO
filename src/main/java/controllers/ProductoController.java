package controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import controllers.*;
import dao.*;

/**
 * Servlet implementation class ProductoController
 */
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductoDAO pDAO = new ProductoDAO();
	RequestDispatcher rd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action.equals("eliminar")) {
			eliminarProducto(request, response);
		}
		if(action.equals("listar")) {
			listaProductos(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("buscar")) {
			buscarProducto(request, response);
		}
	}

	protected void listaProductos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = pDAO.listarProductos();
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		boolean productoCreado = pDAO.actualizarProducto(nombre);
		request.setAttribute("productoCreado", productoCreado);
		listaProductos(request, response);

	}

	protected void buscarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String queryParametro = request.getParameter("queryProducto");
		ArrayList<Producto> productos = pDAO.buscarProductos(queryParametro);
		request.setAttribute("resultado", productos);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productoId = Integer.parseInt(request.getParameter("productoId"));
		 boolean productoEliminado = pDAO.eliminarProducto(productoId);
		 request.setAttribute("productos", productoEliminado);
		 rd = request.getRequestDispatcher("/ProductoController?action=listar");
		 rd.forward(request, response);
	}

}
