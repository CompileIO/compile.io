package compile_io.controllers;

import java.io.File;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import compile_io.docker.*;
import compile_io.mongo.models.Code;
import compile_io.mongo.repositories.CodeRepository;
import compile_io.storage.StorageFileNotFoundException;
import compile_io.storage.StorageService;
import java.time.*;

@RestController
public class CodeController{
	
	@Autowired 
	public CodeRepository codeRepository;

	private final StorageService storageService;
	private String fileName;
  private final int MAX_FILE_SIZE = 50000000;

	@Autowired
	public CodeController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	public class Dto {
		public MultipartFile file;
	}
	
	
	@PostMapping("/{courseName}/{homeworkName}/uploadTest")
	@ResponseBody
	public String[] inputCodeforUser(
									  @RequestParam("username") String userName,
									  @RequestParam("file") File file,
									  @RequestParam(value = "type") String type,
									  @RequestParam(value = "runTime") String runTime,
									  @RequestParam(value = "class") String givenCourse,
									  RedirectAttributes redirectAttributes) {
		
//		storageService.store(file);                
//		fileName = file.getName();
//		
//		
//		String workingDir = System.getProperty("user.dir") + "/upload-dir/" + fileName;
//		workingDir = workingDir.substring(2);
//		System.out.println("Working Directory = " + workingDir);
		
		
		int runTimeNum = Integer.parseInt(runTime);
//		Date submissionTime = new Date(0);
		
		
		LocalTime submissionTime = LocalTime.now();
		Code newCode = new Code(type, runTimeNum, fileName, submissionTime);
		codeRepository.save(newCode);
		
    	// Docker stuff
//		File fileToUpload = new File(workingDir);
		String result = runCompiler(file, type, runTimeNum);
		String[] temp2 = {result};
		return temp2;
	}
	
	public String runCompiler(File fileToUpload, String language, int timeLimit) {
		try {
			BuilderFactory builderFactory = new BuilderFactory();
			AbstractBuilder builder = builderFactory.getBuilder(language, fileToUpload);
			IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
			builder.createDockerfile(builder.getDockerfileData());
			builder.buildContainer();
			return runner.run(timeLimit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
