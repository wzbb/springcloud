package com.rb.feign.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rb on 2020/10/28.
 */
@FeignClient(value = "ribbon-consumer")
public interface GetFeign {

    @GetMapping("index")
    public String getFeign();
}
