package compile_io;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import compile_io.storage.StorageProperties;
import compile_io.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		multipart.setMaxUploadSize(3 * 1024 * 1024);
		return multipart;
	}

	@Bean
	@Order(0)
	public MultipartFilter multipartFilter() {
		MultipartFilter multipartFilter = new MultipartFilter();
		multipartFilter.setMultipartResolverBeanName("multipartReso‌​lver");
		return multipartFilter;
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			
			storageService.init();
		};
	}

}
