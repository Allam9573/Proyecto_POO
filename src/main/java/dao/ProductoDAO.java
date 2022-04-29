package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Producto;
import utils.HibernateUtil;

public class ProductoDAO {
	public ArrayList<Producto> listarProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			productos = (ArrayList<Producto>) session.createQuery("SELECT c FROM Producto c", Producto.class)
					.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			// si existe transaccion que la cierre
			if (tr != null) {
				tr.rollback();
			}
			productos = null;
		} finally {
			// cierre de sesion con la BD
			session.close();
			sessionF.close();
		}
		return productos;
	}

//	 
	public boolean actualizarProducto(String nombre) {

		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();

		Transaction tr = null;
		try {

			tr = session.beginTransaction();

			Producto producto = session.get(Producto.class, nombre);
			producto.setNombre(nombre);

			session.update(producto);

			tr.commit();

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();

			if (tr != null) {
				tr.rollback();
			}

			return false;
		}

		finally {
			session.close();
			sessionF.close();
		}

	}

	public ArrayList<Producto> buscarProductos(String filtro) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			String sql = "Select p from Producto p ";

			if (filtro != null) {
				sql += "where p.nombre like '%" + filtro + "%'";
				productos = (ArrayList<Producto>) session.createQuery(sql, Producto.class).getResultList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			productos = null;
		}
		return productos;
	}

	public boolean eliminarProducto(int idProducto) {
		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Producto producto = session.get(Producto.class, idProducto);
			session.delete(producto);
			tr.commit();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			return false;
		} finally {
			session.close();
			sessionF.close();
		}
	}

}
