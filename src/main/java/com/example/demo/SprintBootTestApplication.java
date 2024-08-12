package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Przed uruchomieniem projektu należy uruchomić bazę danych MySQL z obrazu Dockera loving_swartz
 */
@SpringBootApplication
public class SprintBootTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootTestApplication.class, args);
	}

}
