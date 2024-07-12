package todolist_grupo3.controllers;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CustomApplication {
    public static void main (String[] args) {
        SpringApplication app = new SpringApplication(CustomApplication.class);
        app.setDefaultProperties(Collections
        .singletonMap("spring.datasource.url", "jdbc:mysql://localhost:3306/todolist"));
        app.run(args);
    }
}
