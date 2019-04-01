package compile_io.mongo.repositories;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Student;

public interface StudentRepository extends MongoRepository<Student, String>{
	List<Student> findByName(String name, Sort sort);
	List<Student> findByuserName(String userName, Sort sort);
	
	
	
	
}
