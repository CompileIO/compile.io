package compile_io.mongo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Code;

public interface CodeRepository extends MongoRepository<Code, String> {
	List<Code> findByassignmentId(String assignmentId, Sort sort);

}
