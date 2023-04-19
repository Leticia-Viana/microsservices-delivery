package com.ada.microsservicestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicrosserviceStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosserviceStorageApplication.class, args);
	}

}
