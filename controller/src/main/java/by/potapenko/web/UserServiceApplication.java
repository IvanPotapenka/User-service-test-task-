package by.potapenko.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "by.potapenko")
@EnableJpaRepositories(basePackages = "by.potapenko.model.repository")
@EntityScan(basePackages = "by.potapenko")
public class UserServiceApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(UserServiceApplication.class, args);
    }
}
