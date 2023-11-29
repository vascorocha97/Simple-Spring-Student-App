package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student vasco = new Student("Vasco", "vasco@gmail.com", LocalDate.of(1997,10,13));
            Student ines = new Student("Inês", "ines@gmail.com", LocalDate.of(2003,6,3));
            repository.saveAll(List.of(vasco,ines));
        };
    }
}
