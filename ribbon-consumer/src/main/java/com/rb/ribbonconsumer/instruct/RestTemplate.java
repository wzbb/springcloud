package com.rb.ribbonconsumer.instruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rb on 2020/10/28.
 */
@Component
public class RestTemplate {

    org.springframework.web.client.RestTemplate restTemplate;

    public RestTemplate(){
        restTemplate = new org.springframework.web.client.RestTemplate();
    }
}
