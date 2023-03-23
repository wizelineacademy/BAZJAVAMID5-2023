package com.wizeline.maven.learningjava;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class LearningJavaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LearningJavaApplication.class, args);
	}

}
