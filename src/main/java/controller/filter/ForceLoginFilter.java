package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebFilter(urlPatterns = { "/11admin/*", "/11user/*", "/myFavourites/*" })
public class ForceLoginFilter implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("hereFillter");

		User u = (User) req.getSession().getAttribute("user_Loged");
		System.out.println(u);
//		System.out.println("context:");
//		u = (User)req.getServletContext().getAttribute("user_Loged");
		
		if (u == null) {
			resp.setContentType("text/html");
			resp.getWriter().print(
					"You need login to use this feature! click <a href='http://localhost:8080//SOF3011_ASSIGNMENT/login'>here</a> to login:");
		} else
			chain.doFilter(req, resp);
	}
}
