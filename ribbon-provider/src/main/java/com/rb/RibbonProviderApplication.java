package com.rb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Slf4j
//@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
@EnableEurekaClient
public class RibbonProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonProviderApplication.class, args);
	}


	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
