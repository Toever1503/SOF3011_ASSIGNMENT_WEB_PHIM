package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@WebServlet(urlPatterns = { "/" }, loadOnStartup = 10)
public class HomeController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(req.getRequestURI());
		System.out.println(req.getPathInfo());
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
