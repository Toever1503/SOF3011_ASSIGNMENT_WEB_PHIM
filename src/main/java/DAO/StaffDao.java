package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Category;
import model.Staff;
import utils.JpaUtils;

public class StaffDao {
	private EntityManager enManager;

	public StaffDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<Staff> search(String input) {
		String jpql = "SELECT new Staff(s.id, s.name)  FROM Staff s where name like :search";
		TypedQuery<Staff> query = enManager.createQuery(jpql, Staff.class);
		query.setFirstResult(0);
		query.setMaxResults(10);
		query.setParameter("search", input);

		List<Staff> list = query.getResultList();
		return list;
	}

	public Staff save(Staff obj) {
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
