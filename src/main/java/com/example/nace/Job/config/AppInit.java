package com.example.nace.Job.config;

import com.example.nace.Job.services.NmcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInit {

    @Bean
    public CommandLineRunner initialize(@Autowired NmcaService service) {
      return args -> System.out.println("System started!");
    }
}
