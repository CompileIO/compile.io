package compile_io.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	@GetMapping("/courses")
	public String[] getClasses() {
		String[] temp = { "CSSE120", "CSSE220", "CSSE230", "CSSE241" };
		return temp;
	}

}
