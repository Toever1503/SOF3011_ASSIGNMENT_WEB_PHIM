package DAO;

import javax.persistence.EntityManager;

import model.Share;
import utils.JpaUtils;

public class ShareDao {
	private EntityManager enManager;

	public ShareDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public Share save(Share obj) {
		Share s = null;
		try {
			enManager.getTransaction().begin();
			s = enManager.merge(obj);
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			enManager.getTransaction().rollback();
		}
		return s;
	}
}
