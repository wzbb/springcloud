package com.rb.ribbonprovider.controller;

import com.rb.ribbonprovider.service.ProviderService;
import com.rb.util.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rb on 2020/10/28.
 */
@RestController
public class ProviderController {

    @Autowired
    ProviderService providerService;
    @Autowired
    RabbitSender rabbitSender;

    @GetMapping("/index")
    public String index(){
        return providerService.getProvider();
    }
    @GetMapping("/sendRabbitMessage")
    public void sendRabbitMessage() throws Exception{
        String content = "testsendRabbitMessage";
        Map<String, Object> pro = new HashMap<>();
        pro.put("name", "ruanbin");
        rabbitSender.send(content, pro);
    }
}
