package course.springdata.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.any;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2 //Enables web interface for testing
public class SwaggerConfig {
    // Docket is the configuration of swagger
    @Bean
    public Docket api(){
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())// Expose all our endpoints on all created paths
                .paths(PathSelectors.any())// All paths will be exposed
                .build()
                .pathMapping("/"); // On which path to expose the api
    }
}
