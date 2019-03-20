package compile_io.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
	
    void init();

    void store(MultipartFile file);
    
    void storeAddPath(MultipartFile file, String studentOrProfessor, String className, String assignmentName, String userName);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();


}
