package compile_io.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@Order(2)
@Component
@ConfigurationProperties("server")
public class ServerProperties{
    
    private List<String> paths = new ArrayList<String>();

	public List<String> getPaths() {
		return this.paths;
	}
    
    public List<String> setPath(List<String> paths) {
        return this.paths = paths;
    }
    
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      for(int i = 0; i < this.paths.size(); i ++) {
//    	  System.out.println("\n\n\n\n\n\n -------- \n"
//      			+ "THIS IS THE ADDRESS:    " + this.paths.get(i)
//      			+ "\n\n\n\n---------------------");
    	  config.addAllowedOrigin(this.paths.get(i));
      }
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      source.registerCorsConfiguration("/**", config);
      
      FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
      bean.setOrder(0);
      return bean;
    }
}