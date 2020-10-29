package com.rb.feign.controller;

import com.rb.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rb on 2020/10/28.
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @GetMapping("/feign/index")
    public String index(){
        return "feign-cunsumer/" + feignService.getFeign();
    }
}
