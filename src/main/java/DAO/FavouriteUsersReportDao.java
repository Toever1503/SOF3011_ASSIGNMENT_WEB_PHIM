package DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.iterators.ObjectArrayListIterator;

import model.FavouriteUsersReport;
import model.MapPage;
import model.Video;
import utils.JpaUtils;

public class FavouriteUsersReportDao {
	private EntityManager enManager;

	public FavouriteUsersReportDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<FavouriteUsersReport> findByAllByPage(Long videoId, Integer from, Integer to) {
		TypedQuery<FavouriteUsersReport> query = enManager.createQuery(
				"SELECT distinct new FavouriteUsersReport(u.username, u.fullname, u.email, f.likeDate) FROM User u JOIN Favorite f on f.userId = u.id JOIN Video v on v.id = f.videoId WHERE v.id =:videoID ORDER BY f.likeDate DESC",
				FavouriteUsersReport.class);
		query.setParameter("videoID", videoId);
		query.setFirstResult(from * to);
		query.setMaxResults(to);
		List<FavouriteUsersReport> s = query.getResultList();
		return s;
	}

	public Map<Long, Long> findAllPage(Integer perpage) {
		TypedQuery<MapPage> query = enManager.createQuery(
				"SELECT new MapPage(videoId, COUNT(userId)/:perpage) FROM Favorite f GROUP BY videoId ORDER BY f.likeDate DESC",
				MapPage.class);
		query.setParameter("perpage", Long.valueOf(perpage));
		Map<Long, Long> page = null;
		try {
			List<MapPage> list = query.getResultList();
			page = list.stream().collect(Collectors.toMap(MapPage::getKeyID, MapPage::getPage));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return page;
	}

	public List<Video> findUserVideos() {
		TypedQuery<Video> query = enManager.createQuery(
				"SELECT v FROM Video v JOIN Favorite f on f.videoId = v.id GROUP BY f.videoId", Video.class);
		List<Video> list = null;
		try {
			list = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public void main(String[] args) {
		System.out.println(findByAllByPage(1l, 0, 10).size());
	}
}
