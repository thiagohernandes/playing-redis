package com.playing.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PlayingRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayingRedisApplication.class, args);
	}

}
