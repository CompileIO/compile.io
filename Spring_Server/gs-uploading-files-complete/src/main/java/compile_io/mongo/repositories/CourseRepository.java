package compile_io.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Course;

public interface CourseRepository extends MongoRepository<Course, String> {

}
