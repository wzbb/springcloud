package com.rb.ribbonconsumer.controller;

import com.rb.ribbonconsumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rb on 2020/10/28.
 */
@RestController
@RequestMapping("/ribbonconsumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;


    @GetMapping("/index")
    public String getConsumer(){

        return consumerService.getConsumer();
    }
}
