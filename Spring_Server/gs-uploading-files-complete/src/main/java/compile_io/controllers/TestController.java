package compile_io.controllers;

import java.io.File;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import compile_io.docker.*;

@RestController
public class TestController implements Controller {
	
	
//	@CrossOrigin(origins = frontendVm, allowCredentials = "true")
//	@GetMapping("/test")
//	// @RequestMapping(method = RequestMethod.GET)
//	public String[] inputTestsforUser(@RequestParam("file") MultipartFile file, 
//							 @RequestParam("type") String type,
//							 @RequestParam("runTime") String runTime,
//							 RedirectAttributes redirectAttributes) {
//
//		int runTimeNum = Integer.parseInt(runTime);
//
//		String workingDir = System.getProperty("user.dir") + "/upload-dir/" + file;
//		workingDir = workingDir.substring(2);
//		System.out.println("Working Directory = " + workingDir);
//
//    	// Docker stuff
//		File fileToUpload = new File(workingDir);
//		String result = runCompiler(fileToUpload, type, runTimeNum);
//		String[] temp2 = {result};
//		return temp2;
//	}
	
	
}
