package controller.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebFilter(urlPatterns = {"/admin/*"})
public class ForceAdmin implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("ForceAdmin->");
		User u = (User) req.getSession().getAttribute("user_Loged");
		System.out.println(u);
		if(u!= null && u.getAdmin()==1) {
			chain.doFilter(req, resp);
		}
		else resp.getWriter().print("You don't have permission to visit this site!");
	}
}
