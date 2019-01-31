package compile_io.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Test;

public interface TestRepository extends MongoRepository<Test, String> {

}
