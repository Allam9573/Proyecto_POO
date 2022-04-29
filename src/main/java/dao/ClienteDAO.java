package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import model.Cliente;
import utils.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class ClienteDAO {

	public ArrayList<Cliente> listarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			clientes = (ArrayList<Cliente>) session.createQuery("SELECT c FROM Cliente c", Cliente.class)
					.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			clientes = null;
		} finally {
			session.close();
			sessionF.close();
		}
		return clientes;
	}

	public Cliente loginCliente(String usuario, String contrasenia) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cliente = null;
		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();
		Transaction tr = null;

		try {
			tr = session.beginTransaction();
			Query<Cliente> user = (Query<Cliente>) session.createQuery(
					"SELECT c FROM Cliente c WHERE c.usuario=" + usuario + " AND c.contrasenia=" + contrasenia,
					Cliente.class);
			List<Cliente> logUser = user.list();

			if (logUser.size() > 0) {
				cliente = logUser.get(0);
				return cliente;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			return null;
		} finally {
			session.close();
			sessionF.close();
		}

	}

	public boolean eliminarCliente(int idCliente) {
		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Cliente cliente = session.get(Cliente.class, idCliente);
			session.delete(cliente);
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

	public ArrayList<Cliente> buscarCliente(String filtro) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();

		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			String sql = "Select c from Cliente c ";

			if (filtro != null) {
				sql += "where c.usuario like '%" + filtro + "%'";
				clientes = (ArrayList<Cliente>) session.createQuery(sql, Cliente.class).getResultList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			clientes = null;
		}
		return clientes;
	}

	public boolean actualizarCliente(String nombre) {

		SessionFactory sessionF = new HibernateUtil().getSessionFactory();
		Session session = sessionF.getCurrentSession();

		Transaction tr = null;
		try {

			tr = session.beginTransaction();

			Cliente cliente = session.get(Cliente.class, nombre);
			cliente.setNombre(nombre);

			session.update(cliente);

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
}
