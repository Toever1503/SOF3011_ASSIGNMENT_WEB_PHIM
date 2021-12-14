package DAO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.MapPage;
import model.ShareFriendsReport;
import model.Video;
import utils.JpaUtils;

public class ShareFriendsReportDao {
	private EntityManager enManager;

	public ShareFriendsReportDao() {
		// TODO Auto-generated constructor stub
		enManager = JpaUtils.getManager();
	}

	public List<ShareFriendsReport> findByVideo(Long videoId, Integer from, Integer to) {
		TypedQuery<ShareFriendsReport> query = enManager.createQuery(
				"SELECT new ShareFriendsReport(o.fullname, o.email, s.email, s.shareDate)"
						+ " FROM User o JOIN Share s ON o.id = s.userId JOIN Video v on v.id = s.videoId where v.id =:videoId",
				ShareFriendsReport.class);
		query.setParameter("videoId", videoId);
		query.setFirstResult(from * to);
		query.setMaxResults(to);
		List<ShareFriendsReport> s = query.getResultList();
		return s;
	}

	public Map<Long, Long> findAllPage(Long perpage) {
		TypedQuery<MapPage> query = enManager.createQuery(
				"SELECT new MapPage(videoId, count(id)/:perpage) FROM Share s GROUP BY videoId", MapPage.class);
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

	public List<Video> findShareVideos() {
		TypedQuery<Video> query = enManager.createQuery(
				"SELECT v FROM Video v JOIN Share s on s.videoId = v.id GROUP BY s.videoId ORDER BY s.shareDate DESC",
				Video.class);

		List<Video> list = null;
		try {
			list = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public static void main(String[] args) {
	}
}
