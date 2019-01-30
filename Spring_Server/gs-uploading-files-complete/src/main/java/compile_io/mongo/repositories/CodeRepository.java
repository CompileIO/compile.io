package compile_io.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Code;

public interface CodeRepository extends MongoRepository<Code, String> {

}
