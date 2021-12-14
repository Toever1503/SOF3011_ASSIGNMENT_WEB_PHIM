package controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.FavouriteUsersReportDao;
import DAO.FavouriteVideosReportDao;
import DAO.ShareFriendsReportDao;
import model.FavouriteUsersReport;
import model.FavouriteVideosReport;
import model.MapPage;
import model.ShareFriendsReport;
import model.Video;

@WebServlet(urlPatterns = { "/admin/stats/favoriteVideos", "/admin/stats/favoriteUsers", "/admin/stats/shareFriends" })
public class statsAdmin extends HttpServlet {
//	private Map<Long, Map<Integer, List<FavouriteUsersReport>>> favouriteUsers = new HashMap<Long, Map<Integer, List<FavouriteUsersReport>>>();
//	private Map<Long, Map<Integer, List<ShareFriendsReport>>> shareFriends = new HashMap<Long, Map<Integer, List<ShareFriendsReport>>>();
	private Map<Integer, List<FavouriteVideosReport>> favouriteVideos = new HashMap<Integer, List<FavouriteVideosReport>>();

	private FavouriteUsersReportDao favouriteUsersReportDao;
	private ShareFriendsReportDao shareFriendsReportDao;
	private FavouriteVideosReportDao favouriteVideosReportDao;

	private Map<Long, Long> totalFavouriteUsers;
	private Map<Long, Long> totalshareFriends;
	private Integer totaFavouriteVideos;

	private List<Video> shareVideos;
	private List<Video> userVideos;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		favouriteUsersReportDao = new FavouriteUsersReportDao();
		shareFriendsReportDao = new ShareFriendsReportDao();
		favouriteVideosReportDao = new FavouriteVideosReportDao();

		totalFavouriteUsers = favouriteUsersReportDao.findAllPage(15);
		totalshareFriends = shareFriendsReportDao.findAllPage(15l);
		totaFavouriteVideos = favouriteVideosReportDao.findAllPage(15);
		shareVideos = shareFriendsReportDao.findShareVideos();
		userVideos = favouriteUsersReportDao.findUserVideos();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		String action = null;

		MapPage mapPage = new MapPage();
		try {
			BeanUtils.populate(mapPage, req.getParameterMap());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("video->" + req.getParameter("keyID"));
		System.out.println("page->" + req.getParameter("page"));

		System.out.println("mapPage->" + mapPage);

		String page = req.getParameter("page");
		Long pageNumber = null;

		if (page == null) {
			pageNumber = 0l;
		} else {
			try {
				pageNumber = Long.valueOf(page);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		switch (path) {
		case "/admin/stats/favoriteUsers": {
			if (mapPage.getKeyID() == null) {
				mapPage.setKeyID(userVideos.get(0).getId());
			}
			if (mapPage.getPage() == null) {
				mapPage.setPage(0l);
			}
//			Map<Integer, List<FavouriteUsersReport>> list = favouriteUsers.get(mapPage.getVideo());
//			List<FavouriteUsersReport> reportList;
//			if (list == null) { // this video now is not have, page will not exist
//				list = new HashMap<Integer, List<FavouriteUsersReport>>(); // make page with this video
//				reportList = FavouriteUsersReportDao.findByAllByPage(mapPage.getVideo(), mapPage.getPage().intValue(),
//						15);
//				list.put(mapPage.getPage().intValue(), reportList); // get current page with current video
//				favouriteUsers.put(mapPage.getVideo(), list); // put again map
//			} else {
//				reportList = favouriteUsers.get(mapPage.getVideo()).get(mapPage.getPage());
//			}
			// now video with page will exist

			List<FavouriteUsersReport> reportList = favouriteUsersReportDao.findByAllByPage(mapPage.getKeyID(),
					mapPage.getPage().intValue(), 15);

			String[] columnHeader = { "USERNAME", "FULLNAME", "EMAIL", "FAVORITE DATE" };
			req.setAttribute("columnHeader", columnHeader);
			req.setAttribute("keyID", "&keyID=" + mapPage.getKeyID());
			req.setAttribute("total", totalFavouriteUsers.get(mapPage.getKeyID()));
			req.setAttribute("favoUserList", reportList);
			req.setAttribute("videoList", userVideos);
			action = "favoriteUsers";
			break;
		}
		case "/admin/stats/shareFriends": {
			if (mapPage.getKeyID() == null) {
				mapPage.setKeyID(userVideos.get(0).getId());
			}
			if (mapPage.getPage() == null) {
				mapPage.setPage(0l);
			}

//			Map<Integer, List<ShareFriendsReport>> list = shareFriends.get(mapPage.getVideo());
//			List<ShareFriendsReport> reportList;
//			if (list == null) { // this video now is not have, page will not exist
//				list = new HashMap<Integer, List<ShareFriendsReport>>(); // make page with this video
//				reportList = ShareFriendsReportDao.findByVideo(mapPage.getVideo(), mapPage.getPage().intValue(), 15);
//				
//				list.put(mapPage.getPage().intValue(), reportList); // get current page with current video 
//				//list page 0, list1
//				shareFriends.put(mapPage.getVideo(), list); // put again map
//				// share video, list
//				System.out.println("first->"+list.size());
//				System.out.println("first->"+reportList.size());
//			} else {
//				reportList = shareFriends.get(mapPage.getVideo()).get(mapPage.getPage());
//			}
			// now video with page will exist

			List<ShareFriendsReport> reportList = shareFriendsReportDao.findByVideo(mapPage.getKeyID(),
					mapPage.getPage().intValue(), 15);
			String[] columnHeader = { "SENDER NAME", "SENDER EMAIL", "RECEIVE EMAIL", "SENT DATE" };
			req.setAttribute("columnHeader", columnHeader);
			req.setAttribute("keyID", "&keyID=" + mapPage.getKeyID());
			req.setAttribute("total", totalshareFriends.get(mapPage.getKeyID()));
			req.setAttribute("shareUserList", reportList);
			req.setAttribute("videoList", shareVideos);
			action = "shareFriends";
			break;
		}
		case "/admin/stats/favoriteVideos": {
			List<FavouriteVideosReport> list = favouriteVideos.get(page);
			if (list == null) {
				list = favouriteVideosReportDao.findByAllByPage(pageNumber.intValue(), 15);
				favouriteVideos.put(pageNumber.intValue(), list);
			}

			String[] columnHeader = { "VIDEO TITLE", "FAVORITE COUNT", "LASTEST", "OLDEST DATE" };
			req.setAttribute("columnHeader", columnHeader);
			req.setAttribute("total", totaFavouriteVideos);
			req.setAttribute("favoriteVideoList", list);
			action = "favoriteVideos";
			break;
		}
		default:
			resp.getWriter().print("Something seem wrong but don't worry!, code: -1");
			break;
		}
		if (action != null) {
			req.setAttribute("page", pageNumber);
			req.setAttribute("actionType", action);
			req.getRequestDispatcher("/views/admin/stats.jsp").forward(req, resp);
		}
	}
}
