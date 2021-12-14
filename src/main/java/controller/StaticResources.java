package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/resources/*" }, loadOnStartup = 7)
public class StaticResources extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("static");
//		System.out.println("/"+req.getRequestURI().replace(req.getContextPath().concat("/"), "").trim());
		
		req.getRequestDispatcher("/static/css/modal.css").forward(req, resp);
	}
	
}

