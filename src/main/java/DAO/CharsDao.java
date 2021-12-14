package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Chars;
import model.Studio;
import utils.JpaUtils;

public class CharsDao {
	private EntityManager enManager;

	public CharsDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<Chars> search(String input) {
		String jpql = "SELECT new Chars(c.id, c.name) FROM Chars c where name like :search";
		TypedQuery<Chars> query = enManager.createQuery(jpql, Chars.class);
		query.setFirstResult(0);
		query.setMaxResults(10);
		query.setParameter("search", input);

		List<Chars> list = query.getResultList();
		return list;
	}

	public Chars save(Chars obj) {
		try {
			enManager.getTransaction().begin();
			obj = enManager.merge(obj);
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			enManager.getTransaction().rollback();
		}
		return obj;
	}
}
