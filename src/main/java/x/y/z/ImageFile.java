package x.y.z;

public class ImageFile {
	public static final String UPLOAD_PATH = "/Users/kangdonhee/tmpDir/";
	String id;
	private String contentType;
	private int contentLength;
	private String fileName;
	
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
	
	
}
