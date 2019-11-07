package com.trilogyed.vinlookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VinLookupApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinLookupApplication.class, args);
	}

}
