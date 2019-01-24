package compile_io;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import compile_io.mongo.User;
import compile_io.mongo.UserRepository;
import compile_io.storage.StorageProperties;
import compile_io.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new User("Sam", "Pastoriza"));
		repository.save(new User("James", "Edwards"));
		repository.save(new User("Donald", "Sisco"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User user : repository.findAll()) {
			System.out.println(user);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("User found with findByFirstName('Sam'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Sam"));

		System.out.println("Users found with findByLastName('Pastoriza'):");
		System.out.println("--------------------------------");
		for (User user : repository.findByLastName("Pastoriza")) {
			System.out.println(user);
		}
	}
}
