package compile_io.mongo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.util.Streamable;

import compile_io.mongo.models.Assignment;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {

//	Streamable<Order> findByName(String name);
	List<Assignment> findBycourseName(String courseName);
}
