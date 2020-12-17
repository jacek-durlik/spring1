package pl.anril.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.anril.controller")
@EnableAutoConfiguration
public class CurrencyexchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyexchangeServiceApplication.class, args);
	}

}
