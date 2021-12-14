package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.boot.jaxb.mapping.spi.NamedQuery;

import model.User;
import utils.JpaUtils;

public class UserDao {
	private EntityManager enManager;

	public UserDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public User findByID(Long id) {

		User u = enManager.find(User.class, id);
		return u;
	}

	public User findByUser(User obj) {
		String sql = "SELECT o from User o where email = :email or username = :username";
		TypedQuery<User> query = enManager.createQuery(sql, User.class);
		query.setParameter("email", obj.getEmail());
		query.setParameter("username", obj.getUsername());
		List<User> result = query.getResultList();
		return result.size() == 0 ? null : result.get(0);
	}

	public User save(User obj) {
		// bug when insert user it has not genareate auto key although it has inserted
		try {
			enManager.getTransaction().begin();
			enManager.merge(obj);
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			enManager.getTransaction().rollback();
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}

	public User delete(User obj) {
		try {
			enManager.getTransaction().begin();
			enManager.remove(enManager.contains(obj) ? obj : enManager.merge(obj));
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			enManager.getTransaction().rollback();
		}
		return obj;
	}

	public List<User> findAll() {
		return null;
	}

	public List<User> findAllByPage(Integer from, Integer perpage) {
		String jpql = "SELECT o from User o ORDER BY dateUpdate DESC";
		TypedQuery<User> query = enManager.createQuery(jpql, User.class);
		query.setFirstResult(from);
		query.setMaxResults(perpage);

		List<User> list = query.getResultList();
		return list;
	}

	public Integer findAllPage(Long perPage) {
		String jpql = "SELECT COUNT(id)/:perpage as total from User o";
		Query query = enManager.createQuery(jpql);
		query.setParameter("perpage", perPage);

		Long result = (Long) query.getSingleResult();
		return result.intValue();
	}

	public static void main(String[] args) {
	}
}
