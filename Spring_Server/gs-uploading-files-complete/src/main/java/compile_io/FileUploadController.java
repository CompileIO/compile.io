package compile_io;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import compile_io.docker.AbstractCompiler;
import compile_io.docker.CompilerFactory;
import compile_io.storage.StorageFileNotFoundException;
import compile_io.storage.StorageService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
//@RequestMapping(value = "/api/upload")
public class FileUploadController {

	private final StorageService storageService;
	private String fileName;
//	private final static String frontendVm = "http://137.112.104.111:4200";
   private final static String frontendVm = "http://localhost:4200";
//   private final static String frontendVm = process.env.FrontendapiUrlLocal;
  private final int MAX_FILE_SIZE = 50000000;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	
	
	@CrossOrigin(origins = frontendVm, allowCredentials = "true")
	@GetMapping("/run")
	// @RequestMapping(method = RequestMethod.GET)
	public String[] runDocker() {
		String workingDir = System.getProperty("user.dir") + "/upload-dir/" + fileName;
		System.out.println("Working Directory = " + workingDir);

    	// Docker stuff
			File fileToUpload = new File(workingDir);
		runCompiler(fileToUpload, "python", 60);
		String[] temp2 = {"running"};
		return temp2;
	}


  @CrossOrigin(origins = frontendVm, allowCredentials = "true")
	@GetMapping("/{className}")
	// @RequestMapping(method = RequestMethod.GET)
	public String[] getHomeworks(@PathVariable String className) {
		String[] temp = { "Hwk1", "Hwk2", "Hwk3", "Hwk4" };
		return temp;
	}

  @CrossOrigin(origins = frontendVm, allowCredentials = "true")
	@GetMapping("/{className}/{homework}")
	// @RequestMapping(method = RequestMethod.GET)
	public String[] getResults(@PathVariable String className, @PathVariable String homework) {
		String[] temp = {"done!"};
		return temp;
	}
	
	
	@CrossOrigin(origins = frontendVm, allowCredentials = "true")
	@GetMapping("/classes")
	// @RequestMapping(method = RequestMethod.GET)
	public String[] getClasses() {
		String[] temp = { "CSSE120", "CSSE220", "CSSE230", "CSSE241" };
		return temp;
	}

	/*@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "uploadForm";
	}*/

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@CrossOrigin(origins = frontendVm, allowCredentials = "true")
//    @RequestMapping(method = RequestMethod.POST)
	@PostMapping("/")
	public String[] handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    
    if (file.getSize() > MAX_FILE_SIZE) {
      String[] temp = {"File: " + file.getOriginalFilename() + " is too large!"};
      return temp;
    } else {
		  storageService.store(file);
		  fileName = file.getOriginalFilename();
		  redirectAttributes.addFlashAttribute("message",
				  "You successfully uploaded " + file.getOriginalFilename() + "!");
      String[] temp = {"You successfully uploaded " + file.getOriginalFilename() + "!"};
      return temp;
    }
	}
	
	public void runCompiler(File fileToUpload, String language, int timeLimit) {
		CompilerFactory compilerFactory = new CompilerFactory();
		AbstractCompiler compiler = compilerFactory.getCompiler(language, fileToUpload);
		compiler.createDockerfile(compiler.getDockerfileData());
    compiler.buildContainer();
		compiler.run(timeLimit);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
