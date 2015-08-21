package x.y.z;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	private Map<String, ImageFile> imageFileMap;
	
	public ImageService() {
		imageFileMap = new HashMap<String, ImageFile>();
	}
	
	public ImageFile getImage(String id) {
		
		return (ImageFile) imageFileMap.get(id);
	}
	
	public ImageFile save(MultipartFile file) {
		ImageFile imageFile = null;
		String uuid = UUID.randomUUID().toString();
		try {
			String saveFileName = saveToFile(file, uuid);
			imageFile = new ImageFile(uuid, file.getContentType(), (int)file.getSize(), saveFileName);
			imageFileMap.put(uuid, imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imageFile;
	}

	private String saveToFile(MultipartFile file, String uuid) throws IOException {
		String originalFileName = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		String saveFileName = new StringBuilder(uuid).append(".").append(FilenameUtils.getExtension(originalFileName)).toString();
		String savePath = ImageFile.UPLOAD_PATH + saveFileName;
		IOUtils.write(bytes, new FileOutputStream(savePath));
		return saveFileName;
	}

	public ImageFile get(String imageId) {
		return imageFileMap.get(imageId);
	}

	public ImageFile[] saveFiles(MultipartFile[] files) {
		ImageFile[] imageFiles = new ImageFile[files.length];
		for (int i = 0; i < imageFiles.length; i++) {
			imageFiles[i] = save(files[i]);
		}
		return imageFiles;
	}
}
