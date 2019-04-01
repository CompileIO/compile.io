package compile_io.mongo.repositories;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import compile_io.mongo.models.Professor;

public interface ProfessorRepository extends MongoRepository<Professor, String>{
	List<Professor> findByName(String name, Sort sort);
	List<Professor> findByuserName(String userName, Sort sort);
	
	
	
	
}
