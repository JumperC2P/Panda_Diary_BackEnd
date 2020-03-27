package rmit.sepm.PandaDiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan
public class PandaDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandaDiaryApplication.class, args);
	}

}
