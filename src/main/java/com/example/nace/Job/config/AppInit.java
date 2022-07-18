package com.example.nace.Job.config;

import com.example.nace.Job.services.NmcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppInit {

    @Bean
    public CommandLineRunner initialize(@Autowired NmcaService service) {
      return args -> System.out.println("System started!");
    }
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/nace/*"))
                .apis(RequestHandlerSelectors.basePackage("com.example.nace.Job.controller"))
                .build();
    }

}
