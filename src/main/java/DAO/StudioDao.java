package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Studio;
import utils.JpaUtils;

public class StudioDao {
	private EntityManager enManager;

	public StudioDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<Studio> search(String input) {
		String jpql = "SELECT new Studio(s.id, s.name) FROM Studio s where name like :search";
		TypedQuery<Studio> query = enManager.createQuery(jpql, Studio.class);
		query.setFirstResult(0);
		query.setMaxResults(10);
		query.setParameter("search", input);

		List<Studio> list = query.getResultList();
		return list;
	}

	public Studio save(Studio obj) {
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
