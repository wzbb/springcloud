package com.rb.ribbonprovider.service;

import org.springframework.stereotype.Service;

/**
 * Created by rb on 2020/10/28.
 */
@Service
public class ProviderService {

    public String getProvider(){
        return "ribbon-provider";
    }
}
