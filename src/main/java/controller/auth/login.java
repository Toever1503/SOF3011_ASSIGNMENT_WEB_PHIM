package controller.auth;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDao;
import model.User;
import utils.XCookie;

@WebServlet(urlPatterns = { "/login", "/signout" }, loadOnStartup = 0)
public class login extends HttpServlet {
	private UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDao = new UserDao();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println((User) req.getSession().getAttribute("user_Loged"));

		if (req.getRequestURI().contains("signout")) {
			System.out.println("logout");
			HttpSession session = req.getSession();
			User u = (User) session.getAttribute("user_Loged");
			if (u != null) {
				XCookie.add(resp, "user_Loged", "0", 0);
				req.getSession().removeAttribute("user_Loged");
				resp.getWriter().print("success");
			} else {
				resp.getWriter().print("error");
			}
		} else {
			System.out.println("login");
			String remmeber = XCookie.get(req, "user_remmeber", null);
			if (remmeber != null && remmeber != "0") {
				req.setAttribute("user", userDao.findByID(Long.valueOf(remmeber)));
			}
			req.setAttribute("pageTitle", "Login");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		User u = userDao.findByUser(user);
		if (u == null || !u.getPassword().equals(user.getPassword())) {

			req.setAttribute("result", "error");
			req.setAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		} else {
			if (req.getParameter("remember") != null) {
				XCookie.add(resp, "user_remmeber", u.getId().toString(), 600);
			}
			req.getServletContext().setAttribute("user_Loged", u);
			req.getSession().setAttribute("user_Loged", u);
			System.out.println(req.getSession().getId());
			System.out.println((User) req.getSession().getAttribute("user_Loged"));
			req.setAttribute("result", "success");
			req.setAttribute("message", "Đăng nhập thành công, bạn sẽ được di chuyển về trang chủ!");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}

	}
}
