package x.y.z;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFile {
	public static final String UPLOAD_PATH = "/Users/kangdonhee/tmpDir/";
	String id;
	private String contentType;
	private int contentLength;
	private String fileName;
	private boolean isImageFile;
	
	public ImageFile (String id, String contentType, int contentLength, String fileName) {
		this.id = id;
		this.contentLength = contentLength;
		this.contentLength = contentLength;
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsImageFile() {
		Object obj = null;
			try {
				obj = ImageIO.read(new File(UPLOAD_PATH + this.fileName));
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		return obj != null;
	}

	public void setIsImageFile(boolean isImageFile) {
		this.isImageFile = isImageFile;
	}
}
