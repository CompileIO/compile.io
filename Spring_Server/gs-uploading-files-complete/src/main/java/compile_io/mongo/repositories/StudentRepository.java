package compile_io.mongo.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Student;

public interface StudentRepository extends MongoRepository<Student, String>{

//	 public Student findByFirstName(String firstName);
//	 public List<Student> findByLastName(String lastName);
	
	
	
	
}
