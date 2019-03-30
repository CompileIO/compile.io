package compile_io.mongo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
}
