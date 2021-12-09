package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDao;
import DAO.VideoDao;
import model.User;

@WebServlet(urlPatterns = { "/user", "/user/changepassword", "/user/edit_profile" }, loadOnStartup = 3)
public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");
		if (u == null) {
			resp.getWriter().print("You don't have permission to visit this site!");
		} else {

			String requestUrl = req.getServletPath();
			String actionType = null;
			req.removeAttribute("result");

			switch (requestUrl) {
			case "/user/changepassword":
				req.setAttribute("pageTitle", "Change password");
				actionType = "changepassword";
				break;
			case "/user/edit_profile":
				req.setAttribute("pageTitle", "Edit profile");
				actionType = "edit_profile";
				break;
			default:
				req.setAttribute("pageTitle", "User");
				break;
			}

			System.out.println(requestUrl);
			req.setAttribute("actionType", actionType);
			req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user_Loged");
		if (u == null) {
			resp.getWriter().print("You don't have permission to visit this site!");
		} else {
			String requestUrl = req.getServletPath();
			switch (requestUrl) {
			case "/user/changepassword":
				changePassword(req, resp, u);
				break;
			case "/user/edit_profile":
				updateProfile(req, resp, u);
				break;
			default:
				resp.sendError(404, "miss action?");
				break;
			}
		}
	}

	public void changePassword(HttpServletRequest req, HttpServletResponse resp, User user)
			throws ServletException, IOException {
		User u = new User();
		try {
			BeanUtils.populate(u, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("update password");
		System.out.println(u);

		if (u.getPassword().equals(user.getPassword())) {
			u.setUsername(user.getUsername());
			u.setPassword(req.getParameter("newPassword"));
			u.setId(user.getId());
			u.setEmail(user.getEmail());
			u.setAdmin(user.getAdmin());
			u.setFullname(user.getFullname());
			u.setMyVideos(user.getMyVideos());
			u.setDateCreate(user.getDateCreate());
			u.setDateUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

			if (UserDao.save(u) == null) {
				req.setAttribute("result", "error");
				req.setAttribute("message", "Change failed! code: -1");
			} else {
				req.getSession().setAttribute("user_Loged", u);
				req.setAttribute("result", "success");
				req.setAttribute("message", "Change successfully!");
			}
		} else {
			req.setAttribute("result", "error");
			req.setAttribute("message", "Old password incorrect!");
		}
		req.setAttribute("actionType", "changepassword");
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
	}

	public void updateProfile(HttpServletRequest req, HttpServletResponse resp, User user)
			throws ServletException, IOException {
		User u = new User();
		try {
			BeanUtils.populate(u, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("update profile");
		System.out.println(u);
		User searchUser = UserDao.findByUser(user);
		if (searchUser != null && searchUser.getId() != user.getId()) {// error
			req.setAttribute("result", "error");
			req.setAttribute("message", "Email has exist!");
			req.setAttribute("user", u);

		} else {
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setId(user.getId());
			u.setAdmin(user.getAdmin());
			u.setMyVideos(user.getMyVideos());
			u.setDateCreate(user.getDateCreate());
			u.setDateUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

			if (UserDao.save(u) == null) {
				req.setAttribute("result", "error");
				req.setAttribute("user", u);
				req.setAttribute("message", "Update failed! code: -1");
			} else {
				req.getSession().setAttribute("user_Loged", u);
				req.setAttribute("result", "success");
				req.setAttribute("message", "update success!");
			}
		}
		req.setAttribute("actionType", "edit_profile");
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);

	}
}
