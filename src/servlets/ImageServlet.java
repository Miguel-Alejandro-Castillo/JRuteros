package servlets;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import model.Foto;
import utils.Factory;

@WebServlet(name = "ImageServlet", urlPatterns = { "/ImageServlet/*" })
public class ImageServlet extends HttpServlet {

	private static String URL_REPOSITORY = "E:/images";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String fotoId = request.getParameter("fotoId");

		if (StringUtils.isEmpty(fotoId)) {
			// No record found, redirect to default image.
			response.sendRedirect(request.getContextPath() + "/images/noimage.jpg");
			return;
			// response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			// return;
		}

		Long id = Long.parseLong(fotoId.trim());

		Foto foto = Factory.daoFoto().buscarPorId(id);

		if (foto == null) {
			// No record found, redirect to default image.
			response.sendRedirect(request.getContextPath() + "/images/noimage.jpg");
			return;
			// response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			// return;
		}

		// trump.jpg, putin.png
		String imageFileName = foto.getNombre();

		// image/jpg
		// image/png
		String contentType = this.getServletContext().getMimeType(imageFileName);
		response.setHeader("Content-Type", contentType);

		response.setHeader("Content-Length", String.valueOf(foto.getSize()));

		response.setHeader("Content-Disposition", "inline; filename=\"" + imageFileName + "\"");

		File targetFile = new File(
				URL_REPOSITORY + File.separator + foto.getUuid() + "." + FilenameUtils.getExtension(foto.getNombre()));

		response.reset();
		FileInputStream fin = null;
		byte[] value = null;
		// create FileInputStream object

		try {
			value = new byte[foto.getSize().intValue()];
			fin = new FileInputStream(targetFile);
			fin.read(value);
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			close(fin);
		}
		if (value != null)
			// Write image data to Response.
			response.getOutputStream().write(value);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
    // Helpers (can be refactored to public utility class) ----------------------------------------
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
