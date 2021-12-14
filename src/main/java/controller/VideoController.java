package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VideoDao;
import model.Video;

@WebServlet(urlPatterns = "/film/*", loadOnStartup = 6)
public class VideoController extends HttpServlet {
	private VideoDao videoDao;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		videoDao = new VideoDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer url = req.getRequestURL();
		String id = url.substring(url.lastIndexOf("/") + 1).trim();

		System.out.println(req.getServletPath());
		if (!id.isEmpty()) {
			Long videoId = null;
			try {
				videoId = Long.valueOf(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (videoId != null) {
				Video video = videoDao.findByID(videoId);
				if (video != null) {
//					List<Video> list = video.getCategores().get(0).getVideos();
//					req.setAttribute("videoList", list.subList(0, list.size() > 10 ? 10 : list.size()));

					req.setAttribute("video", video);
					req.setAttribute("pageTitle",
							"Film " + video.getTitle() == null ? video.getTitleJp() : video.getTitle());
					req.getRequestDispatcher("/views/video_infor.jsp").forward(req, resp);
					return;
				}
			}
		}

	}
}
