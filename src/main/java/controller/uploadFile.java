package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;

@MultipartConfig
@WebServlet(urlPatterns = { "/uploadFile" }, loadOnStartup = 2)
public class uploadFile extends HttpServlet {
	private Date date;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = req.getParameter("action");
		if (action != null) {
			this.date = Calendar.getInstance().getTime();
			switch (action) {
			case "uploadImage":
				uploadImage(req, resp);
				break;
			default:
				break;
			}
		}

	}

	public void uploadImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getServletContext().getRealPath("/").concat(date.getYear() + "/" + date.getMonth() + "/");
		List<String> fileUploaded = new ArrayList<String>();
		File directory = new File(path);
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}

		req.getParts().forEach(file -> {
			String contentType = file.getContentType();

			if (contentType != null) {
				String fileName = file.getSubmittedFileName();
				contentType = fileName.substring(fileName.indexOf('.'));
				fileName = fileName.replace(contentType, "");

				System.out.println(contentType);
				System.out.println(fileName);

				int i = 0;
				String fileNew = fileName;
				while (new File(path + fileNew + contentType).isFile()) {
					fileNew = fileName + i++;
				}
				String fileUpload = path.concat(fileNew + contentType);
				try {
					file.write(fileUpload);
					fileUploaded.add(fileUpload.replace(getServletContext().getRealPath("/"), ""));
					System.out.println(fileUpload);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("upload error" + e);
				}
			}
		});
		Map<String, Object> res = new HashedMap();
		if (fileUploaded.size() == 0) {
			res.put("result", "error");
			res.put("message", "Cannot upload image file");
		} else {
			res.put("result", "success");
			res.put("data", fileUploaded);
		}
		resp.getWriter().print(new JSONObject(res));
	}

	public void uploadVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	public static void main(String[] args) {
		System.out.println(new File("C:\\Users\\haunv\\eclipse-workspace\\.metadata\\.bak_3.log").isFile());
	}

}
