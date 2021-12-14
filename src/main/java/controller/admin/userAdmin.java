package controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.VideoDao;
import DAO.UserDao;
import model.PasswordEncoder;
import model.User;
import model.Video;

@WebServlet(urlPatterns = { "/admin/user", "/admin/user/edit", "/admin/user/new",
		"/admin/user/delete" }, loadOnStartup = 5)
public class userAdmin extends HttpServlet {
	private UserDao userDao;
	private static Map<Integer, List<User>> users = new HashMap<Integer, List<User>>();
	private static Integer total;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDao = new UserDao();
		total = userDao.findAllPage(Long.valueOf(4));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		req.setAttribute("userList", userDao.findByPage(0, 2));
//		req.setAttribute("total", userDao.getAllPage(Long.valueOf(2)));

		String path = req.getServletPath();
		switch (path) {
		case "/admin/user/new":
			req.setAttribute("pageTitle", "Add new user");
			req.getRequestDispatcher("/views/admin/user_new.jsp").forward(req, resp);
			break;
		case "/admin/user/edit":
			String id = req.getParameter("id");
			Long userId = null;
			try {
				userId = Long.valueOf(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (userId == null) {
				resp.getWriter().print("you missed id?");
			} else {
				User user = userDao.findByID(userId);
				if (user == null) {
					resp.getWriter().print("cannot find user!");
				} else {
					req.setAttribute("pageTitle", "Edit profile");
					req.setAttribute("user", user);
					req.getRequestDispatcher("/views/admin/user_edit.jsp").forward(req, resp);
				}
			}
			break;
		case "/admin/user/delete":
			deleteUser(req, resp);
			break;
		default:
			req.setAttribute("pageTitle", "Users");
			homeUserAdmin(req, resp);
			break;
		}

	}

	public void homeUserAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		Integer pageNumber = 0;

		if (page == null) {
			pageNumber = 0;
		} else {
			try {
				pageNumber = Integer.valueOf(page);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		List<User> list = users.get(pageNumber);
		if (list == null) {
			list = userDao.findAllByPage(pageNumber, 4);
//			users.put(pageNumber, list);
		}

		req.setAttribute("page", pageNumber);
		req.setAttribute("total", total);
		req.setAttribute("userList", list);
		req.getRequestDispatcher("/views/admin/users.jsp").forward(req, resp);
	}

	public void saveUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, req.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User userUpdate = null;
		User searchUser = userDao.findByUser(user);
//		System.out.println("#id->" + (!user.getId().equals(searchUser.getId())));

		if (searchUser != null && (!user.getId().equals(searchUser.getId()))) { // check email and username conflict
																				// others
			req.setAttribute("result", "error");
			req.setAttribute("user", user);

			if (user.getId() == 0) {
				if (user.getUsername().equalsIgnoreCase(searchUser.getUsername())) {
					req.setAttribute("message", "Username has exist");
				} else if (user.getEmail().equalsIgnoreCase(searchUser.getEmail())) {
					req.setAttribute("message", "Email has exist");
					System.out.println("here id 0");
				}
				req.getRequestDispatcher("/views/admin/user_new.jsp").forward(req, resp);
			} else {
				if (user.getEmail().equalsIgnoreCase(searchUser.getEmail())) {
					req.setAttribute("message", "Email has exist");
				}
				req.getRequestDispatcher("/views/admin/user_edit.jsp").forward(req, resp);
			}
		} else {
			if (user.getId() == 0) {
				user.setPassword(PasswordEncoder.getInstance().encode(user.getPassword()));
			}
			user.setDateUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			userUpdate = userDao.save(user);

			if (userUpdate == null) {
				req.setAttribute("result", "error");
				req.setAttribute("message", (user.getId() == 0 ? "Add new" : "Update") + " user failed!");
				req.setAttribute("user", user);
				if (user.getId() == 0) {
					req.getRequestDispatcher("/views/admin/user_new.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("/views/admin/user_edit.jsp").forward(req, resp);
				}

			} else {
				req.setAttribute("result", "success");
				if (user.getId() == 0) {
					req.setAttribute("message", "Add new user successfully!");
					req.getRequestDispatcher("/views/admin/user_new.jsp").forward(req, resp);
				} else {
					req.setAttribute("user", userUpdate);
					req.setAttribute("message", "Update new user successfully!");
					req.getRequestDispatcher("/views/admin/user_edit.jsp").forward(req, resp);
				}

			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		switch (path) {
		case "/admin/user/new":
			saveUser(req, resp);
			break;
		case "/admin/user/edit":
			saveUser(req, resp);
			break;
		case "/admin/user/delete":
			break;
		default:
			homeUserAdmin(req, resp);
			break;
		}

	}

	public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		System.out.println(req.getParameter("username"));
		try {
			BeanUtils.populate(user, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user);
		req.getRequestDispatcher("/views/admin/user_new.jsp").forward(req, resp);

		if (user == null) {
			resp.getWriter().print("One wrong occur but don't worry!");
		} else {
//			if (userDao.save(user) != null) {
//				req.setAttribute("result", "success");
//				req.setAttribute("message", "Add new successfully!");
//				req.getRequestDispatcher("/views/admin/user_new.jsp").forward(req, resp);
//			} else {
//				resp.getWriter().print("One wrong occur but don't worry! Code: -1");
//			}
		}
	}

	public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("id");
		if (id == null || id.isEmpty()) {
			resp.getWriter().print("You missed id?");
		} else {
			Long userId = null;
			try {
				userId.valueOf(id);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (userId == null) {
				resp.getWriter().print("One wrong occur but don't worry!");
			} else {
				User user = new User();
				try {
					BeanUtils.populate(user, req.getParameterMap());
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (user == null) {
					resp.getWriter().print("One wrong occur but don't worry!");
				} else {
					User oldUser = userDao.findByID(Long.valueOf(id));
					user.setUsername(oldUser.getUsername());
					user.setId(oldUser.getId());
					if (userDao.save(user) != null) {
						req.setAttribute("user", user.getUsername());
						req.setAttribute("result", "success");
						req.setAttribute("message", "Change successfully!");
						req.getRequestDispatcher("/views/admin/user_edit.jsp").forward(req, resp);
					} else {
						resp.getWriter().print("One wrong occur but don't worry! Code: -1");
					}

				}
			}
		}

	}

	public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null) {
			// for error
		} else {
			Long userId = null;
			try {
				userId = Long.valueOf(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (userId == null) {
				// for error
			} else {
				User user = userDao.findByID(userId);
				if (user == null) {
					// for error
				} else {
					// delete successfull
					userDao.delete(user);
					req.setAttribute("actionDelete", "success");
					req.setAttribute("notice", "Delete successfully");
					homeUserAdmin(req, resp);
//					resp.sendRedirect("http://localhost:8080/SOF3011_ASSIGNMENT/admin/video/");
				}
			}
		}
	}
}
