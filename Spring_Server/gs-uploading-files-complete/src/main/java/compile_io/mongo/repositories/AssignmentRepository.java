package compile_io.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Assignment;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {
}
