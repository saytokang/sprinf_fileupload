package x.y.z;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("imageView")
public class ImageView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ImageFile imageFile = (ImageFile) model.get("imageFile");
		res.setContentLength(imageFile.getContentLength());
		res.setContentType(imageFile.getContentType());
		String path = ImageFile.UPLOAD_PATH + imageFile.getFileName();
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		write(res, bytes);
	}

	private void write(HttpServletResponse res, byte[] bytes) throws IOException {
		OutputStream out = res.getOutputStream();
		out.write(bytes);
		out.flush();
	}


}
