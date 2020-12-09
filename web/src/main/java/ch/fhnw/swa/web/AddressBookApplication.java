package ch.fhnw.swa.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ch.fhnw.swa"})
@EntityScan(basePackages = "ch.fhnw.swa")
@ComponentScan(basePackages = "ch.fhnw.swa")
public class AddressBookApplication {

    public static void main(String[] args){
        SpringApplication.run(AddressBookApplication.class);
    }

}
