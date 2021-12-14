package controller.ajax;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;

import DAO.CharsDao;
import DAO.FavourieDao;
import DAO.ShareDao;
import DAO.StaffDao;
import DAO.StudioDao;
import DAO.TagDao;
import DAO.UserDao;
import DAO.VideoDao;
import model.Chars;
import model.Favorite;
import model.Share;
import model.Staff;
import model.Studio;
import model.Tag;
import model.User;
import model.Video;
import utils.SendMail;

@WebServlet(urlPatterns = { "/ajaxAdmin" }, loadOnStartup = 3)
public class AjaxController extends HttpServlet {
	private StaffDao staffDao;
	private StudioDao studioDao;
	private TagDao tagDao;
	private CharsDao charsDao;
	private ShareDao shareDao;
	private VideoDao videoDao;
	private FavourieDao favourieDao;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("ajax servlet init");
		staffDao = new StaffDao();
		studioDao = new StudioDao();
		tagDao = new TagDao();
		charsDao = new CharsDao();
		shareDao = new ShareDao();
		videoDao = new VideoDao();
		favourieDao = new FavourieDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");
		System.out.println(action);

		if (action != null) {
			switch (action) {
			case "staffSearch":
				staffSearch(req, resp);
				break;
			case "likeVideo":
				likeVideo(req, resp);
				break;
			case "shareVideo":
				videoShare(req, resp);
				break;
			case "studioSearch":
				studioSearch(req, resp);
				break;
			case "charSearch":
				charSearch(req, resp);
				break;
			case "tagSearch":
				tagSearch(req, resp);
				break;
			default:
				break;
			}
		} else {
			resp.sendError(404, "Nani mo?");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		System.out.println(action);
		if (action != null) {
			switch (action) {
			case "shareVideo":
				videoShare(req, resp);
				break;
			default:
				break;
			}
		} else {
			resp.sendError(404, "Nani mo?");
		}
	}

	public void staffSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");

		if (u != null && u.getAdmin() == 1) {
			String search = req.getParameter("search");

			System.out.println("ajaxAdmin staffSeach");

			Map<String, Object> result = new HashedMap();
			List<Staff> list = staffDao.search("%" + search + "%");

			if (list.size() != 0) {
				result.put("result", "success");
				result.put("data", list);
			} else
				result.put("result", "error");
			System.out.println("staff search sizes->"+list.size());
			resp.getWriter().print(new JSONObject(result));
		} else {
			resp.sendError(404, "Miss permission!");
		}
	}

	public void studioSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");

		if (u != null && u.getAdmin() == 1) {
			String search = req.getParameter("search");

			System.out.println("ajaxAdmin studio search");

			Map<String, Object> result = new HashedMap();
			List<Studio> list = studioDao.search("%" + search + "%");

			if (list.size() != 0) {
				result.put("result", "success");
				result.put("data", list);
			} else
				result.put("result", "error");
			resp.getWriter().print(new JSONObject(result));
		} else {
			resp.sendError(404, "Miss permission!");
		}
	}

	public void charSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");

		if (u != null && u.getAdmin() == 1) {
			String search = req.getParameter("search");

			System.out.println("ajaxAdmin char search");

			Map<String, Object> result = new HashedMap();
			List<Chars> list = charsDao.search("%" + search + "%");

			if (list.size() != 0) {
				result.put("result", "success");
				result.put("data", list);
			} else
				result.put("result", "error");
			resp.getWriter().print(new JSONObject(result));
		} else {
			resp.sendError(404, "Miss permission!");
		}
	}

	public void tagSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");

		if (u != null && u.getAdmin() == 1) {
			String search = req.getParameter("search");

			System.out.println("ajaxAdmin tag search");

			Map<String, Object> result = new HashedMap();
			List<Tag> list = tagDao.search("%" + search + "%");

			if (list.size() != 0) {
				result.put("result", "success");
				result.put("data", list);
			} else
				result.put("result", "error");
			resp.getWriter().print(new JSONObject(result));
		} else {
			resp.sendError(404, "Miss permission!");
		}
	}

	public void likeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");
		System.out.println("like");
		if (u != null) {
			Favorite f = new Favorite();
			f.setUserId(u.getId());
			f.setVideoId(Long.valueOf(req.getParameter("video")));

			Favorite fCheck = favourieDao.findByFavorite(f);

			Map<String, String> res = new HashMap<String, String>();

			if (fCheck == null) { // add
				f.setLikeDate(Calendar.getInstance().getTime());
				if (favourieDao.save(f) != null) { // success
					res.put("result", "success");
					res.put("type", "add");
				} else { // error
					res.put("result", "error");
					res.put("message", "cannot add");
				}
			} else { // remove
				if (favourieDao.delete(fCheck) != null) { // success
					res.put("result", "success");
					res.put("type", "remove");
				} else { // error
					res.put("result", "error");
					res.put("message", "cannot remove");
				}
			}
			resp.getWriter().print(new JSONObject(res));
		} else {
			resp.sendError(404, "You need login!");
		}
	}

	public void videoShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("shareVideo");

		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");
		if (u != null) {
			Share s = new Share();
			try {
				BeanUtils.populate(s, req.getParameterMap());
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.setShareDate(Calendar.getInstance().getTime());
			s.setUserId(u.getId());
			Video video = videoDao.findByID(s.getVideoId());

			String subject = u.getFullname() + " invite you watch this video!";
			String videoLink = "<a href='http://localhost:8080/SOF3011_ASSIGNMENT/film/'" + video.getTitle() + "/"
					+ video.getId() + "Link here</a>";
			String content = "Hi you, visit here to watch video:\n" + videoLink;

			Map<String, String> res = new HashMap<String, String>();

			if (SendMail.sendMail(s.getEmail(), subject, content)) {
				if (shareDao.save(s) != null) {
					res.put("result", "success");
				} else {
					res.put("result", "error");
					res.put("message", "Server got error! code: -500");
				}
			} else {
				res.put("result", "error");
				res.put("message", "Send mail got error! code: -1");
			}
			resp.getWriter().print(new JSONObject(res));
		} else {
			resp.sendError(404, "You need login!");
		}

	}
}
