package com.cloud.springcloudservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class Springcloudservice2Application {

	@Autowired
	private ConnectDemo connectDemo;

	public static void main(String[] args) {

		SpringApplication.run(Springcloudservice2Application.class, args);
	}

	@Value("${message2:Hello default2}")
	private String message;

	@RequestMapping("/message2")
	String getMessage() {
		return this.message;
	}

	@GetMapping(value="/datafromservice1")
	String getDataFromService1(){
		return connectDemo.getMessage();
	}
}

@FeignClient("springcloudservice1")
interface ConnectDemo{

	@GetMapping(value="/message")
	String getMessage();

}