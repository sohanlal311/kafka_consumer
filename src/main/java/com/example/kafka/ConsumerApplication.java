package com.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding(Binding.class)
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	IntegrationFlow integrationFlow(Binding c) {
		return IntegrationFlows.from(c.inputBinding()).handle(String.class, (payload, headers) -> {
			System.out.println("new message " + payload);
			return null;
		}).get();
	}

}

interface Binding {

	String INPUT = "input";

	@Input(INPUT)
	SubscribableChannel inputBinding();

}