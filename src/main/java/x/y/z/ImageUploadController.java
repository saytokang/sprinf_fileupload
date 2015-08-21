package x.y.z;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {
	
	@Resource(name="imageView") 
	ImageView imageView;
	
	@Autowired ImageService imageService;
	
	@RequestMapping("/upload")
	private String uploadView() {
		return "upload";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String submit( @RequestParam("imageFile") MultipartFile file,
			ModelMap model) {
		ImageFile saveFile = imageService.save(file);
		model.addAttribute("imageFile", saveFile);
		return "uploadComplete";
	}
	
	@RequestMapping("/image/{imageId}")
	private ImageView getImage(@PathVariable String imageId, ModelMap modelMap) {
		ImageFile imageFile = imageService.get(imageId);
		
		modelMap.put("imageFile", imageFile);
		
		return imageView;
	}
	
	@RequestMapping("/uploads")
	private String uploadsView() {
		return "uploads";
	}
	
	@RequestMapping(value="/uploads", method=RequestMethod.POST)
	public String submits( @RequestParam("imageFile") MultipartFile[] files,
			ModelMap model) {
		ImageFile[] saveFiles = imageService.saveFiles(files);
		model.addAttribute("imageFiles", saveFiles);
		return "uploadsComplete";
	}
}
