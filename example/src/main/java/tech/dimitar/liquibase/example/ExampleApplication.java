package tech.dimitar.liquibase.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;



@SpringBootApplication(exclude = {
		LiquibaseAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})

//@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

}
