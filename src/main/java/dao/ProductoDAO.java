package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Producto;
import utils.HibernateUtil;

public class ProductoDAO {
	public ArrayList<Producto> listarClientes() {
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
			if (tr != null) {
				tr.rollback();
			}
			productos = null;
		} finally {
			session.close();
			sessionF.close();
		}
		return productos;
	}
}
