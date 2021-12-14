package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Chars;
import model.Tag;
import utils.JpaUtils;

public class TagDao {
	private EntityManager enManager;

	public TagDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<Tag> search(String input) {
		String jpql = "SELECT new Tag(t.id, t.name) FROM Tag t where name like :search";
		TypedQuery<Tag> query = enManager.createQuery(jpql, Tag.class);
		query.setFirstResult(0);
		query.setMaxResults(10);
		query.setParameter("search", input);

		List<Tag> list = query.getResultList();
		return list;
	}

	public Tag save(Tag obj) {
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
