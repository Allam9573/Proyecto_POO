package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Pedido;
import utils.HibernateUtil;

public class PedidoDAO {
	public ArrayList<Pedido> listaPedidos() {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			pedidos = (ArrayList<Pedido>) session.createQuery("SELECT p FROM pedido p", Pedido.class)
					.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			pedidos = null;
		} finally {
			session.close();
			sessionF.close();
		}
		return pedidos;
	}

}
