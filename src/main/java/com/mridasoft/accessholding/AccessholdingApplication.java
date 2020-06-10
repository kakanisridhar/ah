package com.mridasoft.accessholding;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AccessholdingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AccessholdingApplication.class, args);
		CamelSpringBootApplicationController applicationController = applicationContext
				.getBean(CamelSpringBootApplicationController.class);
		applicationController.run();
	}

}
