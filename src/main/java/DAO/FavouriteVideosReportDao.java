package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.FavouriteVideosReport;
import utils.JpaUtils;

public class FavouriteVideosReportDao {
	private EntityManager enManager;

	public FavouriteVideosReportDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<FavouriteVideosReport> findByAllByPage(Integer from, Integer to) {
		TypedQuery<FavouriteVideosReport> query = enManager.createQuery(
				"SELECT new FavouriteVideosReport(v.title, count(f.userId), min(f.likeDate) , max(f.likeDate) ) FROM Video v JOIN Favorite f on f.videoId = v.id GROUP BY v.id ORDER BY f.likeDate DESC ",
				FavouriteVideosReport.class);
		query.setFirstResult(from * to);
		query.setMaxResults(to);
		List<FavouriteVideosReport> s = query.getResultList();
		return s;
	}

	public Integer findAllPage(Integer perpage) {
		Query query = enManager.createQuery("SELECT COUNT(DISTINCT f.videoId)/:perpage FROM Favorite f");
		query.setParameter("perpage", Long.valueOf(perpage));
		Integer total = ((Long) query.getSingleResult()).intValue();
		return total;
	}

	public static void main(String[] args) {
	}
}
