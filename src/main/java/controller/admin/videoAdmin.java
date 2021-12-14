package controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;

import DAO.CategoryDao;
import DAO.VideoDao;
import model.Category;
import model.Chars;
import model.Staff;
import model.Studio;
import model.Tag;
import model.User;
import model.Video;

@WebServlet(urlPatterns = { "/admin", "/admin/video/*", "/admin/video/new", "/admin/video/edit",
		"/admin/video/delete" }, loadOnStartup = 5)
public class videoAdmin extends HttpServlet {
	private CategoryDao categoryDao;
	private VideoDao videoDao;

	private static Map<Integer, List<Video>> videos = new HashMap<Integer, List<Video>>();
	private static Integer total;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		categoryDao = new CategoryDao();
		videoDao = new VideoDao();
		total = videoDao.findAllPage(15);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = req.getServletPath();
		List<Category> catList = categoryDao.getAll();
		
		switch (path) {
		case "/admin/video/new": {
			req.setAttribute("pageTitle", "Add new video");
			req.setAttribute("catList", catList);
			req.getRequestDispatcher("/views/admin/video_new.jsp").forward(req, resp);
			break;
		}
		case "/admin/video/edit":
			String id = req.getParameter("id");
			Long videoId = null;
			try {
				videoId = Long.valueOf(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (videoId == null) {
				resp.getWriter().print("you missed id?");
			} else {
				Video video = videoDao.findByID(videoId);
				if (video == null) {
					resp.getWriter().print("cannot find video!");
				} else {
					catList.removeAll(video.getCategores());

					req.setAttribute("pageTitle", "Edit video!");
					req.setAttribute("catList", catList);
					req.setAttribute("video", video);
					req.getRequestDispatcher("/views/admin/video_edit.jsp").forward(req, resp);
				}
			}
			break;
		case "/admin/video/delete": {
			deleteVideo(req, resp);
			break;
		}
		default:
			req.setAttribute("pageTitle", "Videos");
			req.setAttribute("catList", catList);
			adminDashboardVideo(req, resp);
			break;
		}

	}

	public void adminDashboardVideo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String page = req.getParameter("page");
		Integer pageNumber = 0;

		if (page == null) {
			pageNumber = 0;
		} else {
			try {
				System.out.println("a->" + page);
				pageNumber = Integer.valueOf(page);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		List<Video> list = videos.get(pageNumber);
		if (list == null) {
			list = videoDao.findAllByPage(pageNumber, 15);
//			videos.put(pageNumber, list);
		}

		req.setAttribute("pageTitle", "Admin Dashboard");
		req.setAttribute("page", pageNumber);
		req.setAttribute("total", total);
		req.setAttribute("videoList", list);
		req.getRequestDispatcher("/views/admin/videos.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case "/admin/video/new": {
			saveVideo(req, resp);
			break;
		}
		case "/admin/video/edit":
			saveVideo(req, resp);
			break;
		case "/admin/video/delete":
			deleteVideo(req, resp);
			break;
		default:
			break;
		}
	}

	public void saveVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, req.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String addedStaff = req.getParameter("addedStaff");
		String cat = req.getParameter("cats");
		String addedStudio = req.getParameter("addedStudio");
		String addedTag = req.getParameter("addedTag");
		String addedChar = req.getParameter("addedChar");

		String[] emptyList = new String[0];
		String[] staffs = addedStaff == null ? emptyList : addedStaff.split(",");
		String[] cats = cat == null ? null : cat.split(",");
		String[] studios = addedStudio == null ? emptyList : addedStudio.split(",");
		String[] tags = addedTag == null ? emptyList : addedTag.split(",");
		String[] chars = addedChar == null ? emptyList : addedChar.split(",");

		Set<Staff> listStaff = new HashSet<Staff>();
		Set<Category> listCat = new HashSet<Category>();
		Set<Studio> listStudio = new HashSet<Studio>();
		Set<Tag> listTag = new HashSet<Tag>();
		Set<Chars> listChar = new HashSet<Chars>();

		for (String c : staffs) {
			Long s = null;
			try {
				s = Long.valueOf(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (s != null) {
				listStaff.add(new Staff(s, null, null));
			}
		}

		for (String c : cats) {
			Long s = null;
			try {
				s = Long.valueOf(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (s != null) {
				listCat.add(new Category(s, null, null));
			}
		}

		for (String c : studios) {
			Long s = null;
			try {
				s = Long.valueOf(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (s != null) {
				listStudio.add(new Studio(s, null));
			}
		}

		for (String c : tags) {
			Long s = null;
			try {
				s = Long.valueOf(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (s != null) {
				listTag.add(new Tag(s, null));
			}
		}

		for (String c : chars) {
			Long s = null;
			try {
				s = Long.valueOf(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (s != null) {
				listChar.add(new Chars(s, null));
			}
		}

		String newStaff = req.getParameter("staff");
		String newStudio = req.getParameter("studio");
		String newTag = req.getParameter("tag");
		String newChar = req.getParameter("char");

		if (newStaff != null) {
			String[] Nstaff = newStaff.split(",");
			for (String s : Nstaff) {
				if (!s.isEmpty()) {
					listStaff.add(new Staff(null, s.trim(), null));
				}
			}
		}

		if (newStudio != null) {
			String[] Nstudio = newStudio.split(",");
			for (String s : Nstudio) {
				if (!s.isEmpty()) {
					listStudio.add(new Studio(null, s.trim()));
				}
			}
		}

		if (newTag != null) {
			String[] Ntag = newTag.split(",");
			for (String s : Ntag) {
				if (!s.isEmpty()) {
					listTag.add(new Tag(null, s.trim()));
				}
			}
		}

		if (newChar != null) {
			String[] Nchar = newChar.split(",");
			for (String s : Nchar) {
				if (!s.isEmpty()) {
					listChar.add(new Chars(null, s.trim()));
				}
			}
		}

		video.setCategores(listCat);
		video.setStaffs(listStaff);
		video.setUserId(((User) req.getSession().getAttribute("user_Loged")).getId());
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		video.setDateUpdate(currentTime);
		if (video.getId() == null)
			video.setDateCreate(currentTime);

		System.out.println("Staffs->"+listStaff);
		System.out.println("Cats->"+listCat);
		System.out.println("Studio->"+listStudio);
		System.out.println("Tags->"+listTag);
		System.out.println("Chars->"+listChar);
		
//		おにくだいすき! ゼウシくん（3期）「すてきなハンバーグ」 - 1734

		Video videoUpdate = null;
		videoUpdate = videoDao.save(video);

		if (videoUpdate == null) {
			req.setAttribute("result", "error");
			req.setAttribute("message", (video.getId() == null ? "Add new" : "Update") + " video failed!");
			req.setAttribute("video", video);

			if (video.getId() == null) {
				req.getRequestDispatcher("/views/admin/video_new.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("/views/admin/video_edit.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("result", "success");
			req.setAttribute("message", (video.getId() == null ? "Add new" : "Update") + " video successfully!");
			
			List<Category> catList = categoryDao.getAll();
			if(video.getId() == null) {
				catList.removeAll(videoUpdate.getCategores());
			}
			req.setAttribute("catList", catList);

			if (video.getId() == null) {
				req.getRequestDispatcher("/views/admin/video_new.jsp").forward(req, resp);
			} else {
				req.setAttribute("video", videoUpdate);
				req.getRequestDispatcher("/views/admin/video_edit.jsp").forward(req, resp);
			}
		}
	};

	public void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null) {
			// for error
		} else {
			Long videoId = null;
			try {
				videoId = Long.valueOf(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (videoId == null) {
				// for error
			} else {
				Video video = videoDao.findByID(videoId);
				if (video == null) {
					// for error
				} else {
					// delete successfull
					videoDao.delete(video);
					req.setAttribute("actionDelete", "success");
					req.setAttribute("notice", "Delete successfully");
					adminDashboardVideo(req, resp);
//					resp.sendRedirect("http://localhost:8080/SOF3011_ASSIGNMENT/admin/video/");
				}
			}
		}
	}

}
