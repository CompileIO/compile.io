package compile_io.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import compile_io.docker.AbstractBuilder;
import compile_io.docker.BuilderFactory;
import compile_io.docker.CommandExecuter;
import compile_io.docker.DockerRunner;
import compile_io.docker.IDockerRunner;
import compile_io.mongo.models.Professor;
import compile_io.mongo.repositories.ProfessorRepository;

@RestController
public class ProfessorController{
	@Autowired 
	public ProfessorRepository repository;
	
	@GetMapping("/test")
	public String runCompiler(String username, File fileToUpload, String language, int timeLimit) {
		List<File> studentFiles = new ArrayList<>();
		List<File> ProfessorFiles = new ArrayList<>();
		studentFiles.add(fileToUpload);
		ProfessorFiles.add(fileToUpload);
		try {
			BuilderFactory builderFactory = new BuilderFactory();
			AbstractBuilder builder = builderFactory.getBuilder(language, studentFiles, ProfessorFiles);
			IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
			builder.createDockerfile(builder.getDockerfileData());
			builder.buildContainer();
			
			
			Professor newProfessor = new Professor();
//			
//			newProfessor.inputTest(username, language, timeLimit);
//			repository.save(newProfessor);
//			
			return runner.run(timeLimit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
