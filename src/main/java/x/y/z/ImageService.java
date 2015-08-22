package x.y.z;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
		String newFileName = new StringBuilder(uuid).append(".").append(FilenameUtils.getExtension(originalFileName)).toString();
		String saveFilePath = ImageFile.UPLOAD_PATH + newFileName;
		file.transferTo(new File(saveFilePath));
//		byte[] bytes = file.getBytes();
//		IOUtils.write(bytes, new FileOutputStream(savePath));
		return newFileName;
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
