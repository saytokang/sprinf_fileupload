package x.y.z;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
//		byte[] bytes = Files.readAllBytes(Paths.get(path)); // jdk1.7 이상 
		byte[] bytes = FileUtils.readFileToByteArray(new File(path)); // jdk1.6 이하 
		write(res, bytes);
	}

	private void write(HttpServletResponse res, byte[] bytes) throws IOException {
		OutputStream out = res.getOutputStream();
		out.write(bytes);
		out.flush();
	}


}
