package compile_io.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {
	
	@GetMapping("/{className}")
	public String[] getAssignments(@PathVariable String className) {
		String[] temp = { "Hwk1", "Hwk2", "Hwk3", "Hwk4" };
		return temp;
	}
}
