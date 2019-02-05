package compile_io.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.UnitTest;

public interface UnitTestRepository extends MongoRepository<UnitTest, String>{

}
