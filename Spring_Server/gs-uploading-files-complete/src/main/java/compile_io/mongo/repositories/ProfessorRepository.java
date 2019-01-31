package compile_io.mongo.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Professor;



public interface ProfessorRepository extends MongoRepository<Professor, String>{

	 public Professor findByFirstName(String firstName);
	 public List<Professor> findByLastName(String lastName);
	
	
	
	
}
