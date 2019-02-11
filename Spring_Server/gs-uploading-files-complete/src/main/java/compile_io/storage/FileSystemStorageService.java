package compile_io.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileSystemStorageService implements StorageService {

    private Path rootLocation;
    public StorageProperties properties;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
    	this.properties = properties;
        this.rootLocation = Paths.get(properties.getLocation());
    }

	@Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
    
    public void storeAddPath(MultipartFile file, String addedFolderPathName) {
    	
    	if(addedFolderPathName == "student") {
    		//Windows
    		this.properties.setLocation("upload-dir\\student-files");
    		
    		//Ubuntu
//    		this.properties.setLocation("upload-dir/student-files");
    	}
    	else if(addedFolderPathName == "professor") {
    		//Windows
    		this.properties.setLocation("upload-dir\\professor-files");
    		
    		//Ubuntu
//    		this.properties.setLocation("upload-dir/professor-files");
    	}
    	else {
    		this.properties.setLocation("upload-dir");
    	}
    	this.rootLocation = Paths.get(properties.getLocation());
    	
    	 String filename = StringUtils.cleanPath(file.getOriginalFilename());
    	 System.out.println("INSIDE STOREADDPATH properties: " + this.properties.getLocation());
    	 System.out.println("INSIDE STOREADDPATH: " + this.rootLocation.toString());
         try {
             if (file.isEmpty()) {
                 throw new StorageException("Failed to store empty file " + filename);
             }
             if (filename.contains("..")) {
                 // This is a security check
                 throw new StorageException(
                         "Cannot store file with relative path outside current directory "
                                 + filename);
             }
             try (InputStream inputStream = file.getInputStream()) {
                 Files.copy(inputStream, this.rootLocation.resolve(filename),
                     StandardCopyOption.REPLACE_EXISTING);
             }
         }
         catch (IOException e) {
             throw new StorageException("Failed to store file " + filename, e);
         }
         this.properties.setLocation("upload-dir");
    }
    
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                .filter(path -> !path.equals(this.rootLocation))
                .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    
    public void cleanDirectory() {
    	File dir = rootLocation.toFile();
        for (File file: dir.listFiles()) {
            if(file.getName().equals("build.gradle")) {
                //do nothing
            } else {
                //delete file
                file.delete();
            }

        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            //Windows
            Files.createDirectories(Paths.get(rootLocation + "\\student-files"));
            Files.createDirectories(Paths.get(rootLocation + "\\professor-files"));
            
            //Ubuntu
//            Files.createDirectories(Paths.get(rootLocation + "/student-files"));
//            Files.createDirectories(Paths.get(rootLocation + "/professor-files"));
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
