package com.rb.ribbonconsumer.service;

import com.rb.ribbonconsumer.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rb on 2020/10/28.
 */
@Service
public class ConsumerService {

    @Autowired
    RestTemplateUtils restTemplateUtils;

    public String getConsumer(){

        return "ribbon-consumer/" + restTemplateUtils.getRestTemplate().getForObject("http://ribbon-provider/index", String.class);
    }
}
