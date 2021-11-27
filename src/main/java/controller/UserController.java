package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/user", "/user/login", "/user/changepassword", "/edit_profile",
		"/logout" }, loadOnStartup = 3)
public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestUrl = req.getServletPath();
		String actionType = null;

		switch (requestUrl) {
		case "/user":
			break;
		case "/user/login":
			actionType = "login";
			break;
		case "/logout":
			actionType = "logout";
			break;
		case "/user/changepassword":
			actionType = "changepassword";
			break;
		case "/edit_profile":
			actionType = "edit_profile";
			break;
		default:
			break;
		}

		System.out.println(requestUrl);
		req.setAttribute("actionType", actionType);
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
