package com.saqibayub.twentytwoendpoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.saqibayub.twentytwoendpoints.config.DomainConfig;

@EnableConfigurationProperties(DomainConfig.class)
@SpringBootApplication
public class TwentyTwoEndPointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwentyTwoEndPointsApplication.class, args);
	}
}
