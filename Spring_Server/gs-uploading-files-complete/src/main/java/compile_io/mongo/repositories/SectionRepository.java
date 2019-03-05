package compile_io.mongo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import compile_io.mongo.models.Section;


public interface SectionRepository extends MongoRepository<Section, String> {
	List<Section> findByInstructor(String instructor, Sort sort);

}
