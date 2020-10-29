package com.rb.feign.service;

import com.rb.feign.rpc.GetFeign;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rb on 2020/10/28.
 */
@Service
public class FeignService {

    @Autowired
    GetFeign getFeign;

    public String getFeign(){
        return getFeign.getFeign();
    }
}
