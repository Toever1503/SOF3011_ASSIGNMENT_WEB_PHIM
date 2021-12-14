package controller.auth;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDao;
import model.User;

@WebServlet(urlPatterns = { "/register" })
public class register extends HttpServlet {
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDao = new UserDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("register");
		req.setAttribute("pageTitle", "Register");
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
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
		if (user == null) {
			req.setAttribute("result", "false");
			req.setAttribute("message", "Đăng ký thất bại! Code: -1");
			req.setAttribute("user", user);
		} else {
			User u = userDao.findByUser(user);
			if (u != null) {
				req.setAttribute("user", user);
				req.setAttribute("result", "false");
				if (u.getFullname().equalsIgnoreCase(user.getUsername()))
					req.setAttribute("message", "Tài khoản đã tồn tại!");
				else if (u.getEmail().equalsIgnoreCase(user.getEmail()))
					req.setAttribute("message", "Email đã liên kết với 1 tài khoản đã tồn tại!");
			} else {
				user.setAdmin(Byte.valueOf("0"));
				user = userDao.save(user);
				if (user == null) {
					req.setAttribute("result", "false");
					req.setAttribute("message", "Đăng ký thất bại! Code: -1");
					req.setAttribute("user", user);
				} else {
					req.setAttribute("result", "success");
					req.setAttribute("message", "Đăng ký thành công, bạn hiện có thể đăng nhập ngay bây giờ ");
				}
			}
		}
		System.out.println(user);
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}
}
