package com.microservice.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;



@EnableFeignClients // se habilita el cliente en el microservicio
@EnableDiscoveryClient // se registra en el servidor de eureka
@SpringBootApplication
public class MicroserviceCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCourseApplication.class, args);
	}

}

