package ru.yaharuott.servhack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ServhackApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Krasnoyarsk"));
		SpringApplication.run(ServhackApplication.class, args);
	}

}
