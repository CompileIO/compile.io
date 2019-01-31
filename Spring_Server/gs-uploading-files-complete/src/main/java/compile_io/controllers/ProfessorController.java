package compile_io.controllers;

import java.io.File;

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
		try {
			BuilderFactory builderFactory = new BuilderFactory();
			AbstractBuilder builder = builderFactory.getBuilder(language, fileToUpload);
			IDockerRunner runner = new DockerRunner(builder, new CommandExecuter());
			builder.createDockerfile(builder.getDockerfileData());
			builder.buildContainer();
			
			
//			Professor newProfessor = new Professor();
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
	
	@GetMapping("/asdfasdfasdf")
	public String inputTest () {
		return null;
		
	}
	

//		repository.deleteAll();
//
//		// save a couple of customers
//		repository.save(new User("Sam", "Pastoriza"));
//		repository.save(new User("James", "Edwards"));
//		repository.save(new User("Donald", "Sisco"));
//
//		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (User user : repository.findAll()) {
//			System.out.println(user);
//		}
//		System.out.println();
//
//		// fetch an individual customer
//		System.out.println("User found with findByFirstName('Sam'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByFirstName("Sam"));
//
//		System.out.println("Users found with findByLastName('Pastoriza'):");
//		System.out.println("--------------------------------");
//		for (User user : repository.findByLastName("Pastoriza")) {
//			System.out.println(user);
//		}
//	}

}
