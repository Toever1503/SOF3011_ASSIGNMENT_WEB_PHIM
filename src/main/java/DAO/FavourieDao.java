package DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Favorite;
import utils.JpaUtils;

public class FavourieDao {
	private EntityManager enManager;

	public FavourieDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public Favorite findByFavorite(Favorite obj) {
		TypedQuery<Favorite> query = enManager
				.createQuery("SELECT f FROM Favorite f WHERE user_id=:user and video_id=:video", Favorite.class);
		query.setParameter("user", obj.getUserId());
		query.setParameter("video", obj.getVideoId());

		Favorite f = null;
		try {
			f = query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return f;
	}

	public Favorite save(Favorite obj) {
		try {
			enManager.getTransaction().begin();
			enManager.merge(obj);
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			enManager.getTransaction().rollback();
			obj = null;
		}
		return obj;
	}

	public Favorite delete(Favorite obj) {
		try {
			enManager.getTransaction().begin();
			enManager.remove(enManager.contains(obj) ? obj : enManager.merge(obj));
			enManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			enManager.getTransaction().rollback();
			obj = null;
		}
		return obj;
	}

	public static void main(String[] args) {
	}
}
