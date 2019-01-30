package compile_io.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import compile_io.docker.*;
import compile_io.mongo.repositories.CodeRepository;
import compile_io.storage.StorageFileNotFoundException;
import compile_io.storage.StorageService;

@RestController
public class CodeController{
	
	@Autowired 
	public CodeRepository repository;

	private final StorageService storageService;
	private String fileName;
  private final int MAX_FILE_SIZE = 50000000;

	@Autowired
	public CodeController(StorageService storageService) {
		this.storageService = storageService;
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
