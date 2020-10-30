package com.rb.ribbonprovider.service;

import org.springframework.stereotype.Service;

/**
 * Created by rb on 2020/10/28.
 */
@Service
public class ProviderService {

    public String getProvider()
    {
        //String temp = "吉隆坡-厦门";

        return "ribbon-provider";
    }
}
