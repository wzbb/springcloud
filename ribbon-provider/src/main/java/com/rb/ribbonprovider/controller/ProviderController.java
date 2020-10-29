package com.rb.ribbonprovider.controller;

import com.rb.ribbonprovider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rb on 2020/10/28.
 */
@RestController
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping("/index")
    public String index(){
        return providerService.getProvider();
    }
}
