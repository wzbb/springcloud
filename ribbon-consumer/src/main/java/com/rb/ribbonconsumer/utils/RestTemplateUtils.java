package com.rb.ribbonconsumer.utils;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rb on 2020/10/28.
 */
@Component
public class RestTemplateUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Bean
    @LoadBalanced
    public static RestTemplate getRestTemplate(){
        return restTemplate;
    }
}
